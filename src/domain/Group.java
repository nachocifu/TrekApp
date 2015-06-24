package domain;

import java.util.Calendar;
import java.util.Collection;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;

import com.j256.ormlite.field.DataType;
import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;


/**
 *
 * A Group object
 *
 */
@DatabaseTable(tableName = "Group")
public class Group {

    /*Group Attributes*/

    /**
     * The group name
     */
    @DatabaseField
    private String groupName;

    /**
     * 	Final, does not change
     */
    @DatabaseField( generatedId = true)
    private Integer groupId;

    /**
     * The admin's user that will be used to validate permissions
     */
    private Profile admin;

    /**
     * HashSet containing the users of the group
     */
    private HashSet<Profile> members;

    /**
     * HashMap, the key contains a member of the group and the value the members left to review
     */
    private HashMap<Profile, HashSet<Profile>> missingReviewsToMake;

    /**
     * HashSet containing the trips of the group Trips
     */
    private Trip groupTrip;

    /**
     * REJECTED if he has been rejected and WAITING if is waiting for acceptance
     */
    private HashMap<Profile, RequestStatus> memberRequests;

    @DatabaseField
    private Integer filterAge;

    @DatabaseField
    private String filterCity;

    @DatabaseField
    private Integer maxGroupSize;

    /*Group Constructors*/

    public Group(String groupName, Profile admin, Integer maxGroupSize, Integer filterAge, String filterCity){
    	this.admin = admin;
        this.groupName=groupName;
        this.members=new HashSet<Profile>();
        this.memberRequests = new HashMap<Profile, RequestStatus>();
        this.maxGroupSize = maxGroupSize;
        this.filterAge = filterAge;
        this.filterCity = filterCity;
        this.members.add(admin);
    }

    public Group(){
    }

    /*Group Methods*/

    /**
     * @return Returns a Collection with the Group members
     */
    public Collection<Profile> getMembers(){
        return this.members;
    }

    /**
     * @return Returns the Group name
     */
    public String getGroupName(){
        return this.groupName;
    }

    /**
     * Sets a new Group name but before validates if it is valid
     * @param groupName
     */
    public void setGroupName(String groupName){
        if(groupName == null || groupName.trim().isEmpty())
            throw new IllegalArgumentException("The new group name is either null or empty");
        this.groupName = groupName;
    }

    /**
     * @return Returns the Group administrator
     */
    public Profile getAdminUser(){
        return this.admin;
    }

    /**
     * Sets a new Group administrator that has to belong to the Group (only one administrator per Group)
     * @param admin
     */
    public void setAdminUser(Profile newAdmin){
        if(!this.members.contains(newAdmin))
            throw new IllegalArgumentException("ERROR || The new admin does not belong to this group");
        this.admin = newAdmin;
    }

    /**
     * Sends a review to a member of the group
     * @param loggedUser
     * @param member
     * @param msg
     * @param rating
     * @throws TripNotClosedException
     */
    public void sendReviewToAMember(Profile loggedUser, Profile member, String msg, Integer rating) throws TripNotClosedException{
        if(!(this.groupTrip.getTripStatus() == TripStatus.CLOSED)){
             throw new TripNotClosedException("Cannot send a review because the Trip is not Closed yet");
        }else if(!this.members.contains(loggedUser)){
             throw new IllegalArgumentException("Cannot send a review because you did not belong to this group");
         }else if(!this.members.contains(member)){
             throw new IllegalArgumentException("Cannot sent a review to that user because it does not belong to this group");
         }else if(this.missingReviewsToMake.get(loggedUser).contains(member)){
             throw new IllegalArgumentException("That member already received a review from this user");
         }
         member.addReview(loggedUser, msg, rating);
         missingReviewsToMake.get(loggedUser).remove(member);
    }

    /**
     *
     * @return a hashmap, the key is the Profile of the user who still has to make a review and the value
     * is a HashSet containing the Profiles of the users the user has still not reviewed
     */
    public HashMap<Profile, HashSet<Profile>> getMissingReviewsToMake() {
        return missingReviewsToMake;
    }

    /**
     *
     */
    public void updateMissingReviews(){
        for (Profile member : members) {
            HashSet<Profile> membersToReview = new HashSet<Profile>(members);
            membersToReview.remove(member);
            this.missingReviewsToMake.put(member, membersToReview);
        }
    }

    /**
     * @param member
     * @return A HashSet containing the Profiles that members is yet to review
     * @throws IllegalArgumentException when the member is not part of the group
     */
    public HashSet<Profile> getMissingReviewsToMakeByAMember(Profile member){
        if(!members.contains(member)){
            throw new IllegalArgumentException("Cannot get the missing reviews because you do not belong to this group");
        }
        return missingReviewsToMake.get(member);
    }

    /**
     * Adds a new member if there is space in the Group, if the user does not already belong to the Group and if the user is not the admin
     * @param user to be added to the Group
     * @throws InvalidPermissionException
     */
    public void addMember(Profile newMember) throws InvalidPermissionException{
        if(newMember.equals(this.admin)){
            throw new IllegalArgumentException("ERROR || Cannot add yourself");
        }else if(members.contains(newMember)){
            throw new IllegalArgumentException("ERROR || User already in group");
        }else if (this.maxGroupSize <= this.groupSize()){
            throw new InvalidPermissionException("ERROR || No more space to add a user");
        }
        this.members.add(newMember);
        newMember.joinGroup(this);
    }

    /**
     * Accepts a member of the request list if he has not been rejected
     * @param newMember
     */
    public void acceptMember(Profile newMember){
        if(!memberRequests.containsKey(newMember)){
            throw new IllegalArgumentException("The user does not belong to the users requesting a place in the group");
        }else if(!(memberRequests.get(newMember) == RequestStatus.WAITING)){
            throw new IllegalArgumentException("The user has been rejected and cannot be accespted into the group");
        }else if(!(this.maxGroupSize > this.groupSize())){
            throw new IllegalArgumentException("There is no more space to add a new member into the group");
        }
        memberRequests.remove(newMember);
        members.add(newMember);
        newMember.joinGroup(this);
    }

    /**
     * Rejects a member of the request list
     * @param rejectedProfile
     */
    public void rejectAMemberRequest(Profile rejectedProfile){
        if(!memberRequests.containsKey(rejectedProfile)){
            throw new IllegalArgumentException("The user does not belong to the users requesting a place in the group");
        }else if(!(memberRequests.get(rejectedProfile) == RequestStatus.REJECTED)){
            throw new IllegalArgumentException("The user has already been rejected");
        }
        memberRequests.put(rejectedProfile, RequestStatus.REJECTED);
    }

    /**
     * Adds a member request to be accepted or not using the filters
     * @param possibleMember
     * @throws InvalidPermissionException
     */
    @SuppressWarnings("deprecation")
    public void addMemberRequest(Profile possibleMember) throws InvalidPermissionException{
        Date filterAgeAsDate = new Date();
        filterAgeAsDate.setYear(Calendar.YEAR - filterAge);

        if(memberRequests.containsKey(possibleMember)){
            throw new IllegalArgumentException("The user already belongs to the users requesting a place in the group");
        }else if(!(possibleMember.getBirthDay().before(filterAgeAsDate) && possibleMember.getCity().equals(this.filterCity))){
            throw new InvalidPermissionException("The user does not match the requirements for this group");
        }
        memberRequests.put(possibleMember, RequestStatus.WAITING);
    }

   /**
    * Permanently deletes a member of this Group, if that member is the admin, a new admin is set, if that member is the last one, the group is deleted.
    * @param member
    * @return If that member is yourself and you are the only one in the Group, returns True (useful to delete Group if it is saved).
    * @throws IllegalArgumentException
    */
    public Boolean deleteMember(Profile member) throws IllegalArgumentException{
        if(!this.members.contains(member)){
            throw new IllegalArgumentException("The user that you are trying to delete does not belong to the group");
        }else if(member.equals(this.admin) || groupSize() == 1){
            member.leaveGroup(this);
            return true;
        }else{
            this.members.remove(member);
            this.admin = this.members.iterator().next();
        }
        return false;
    }

    /**
     * Any member of the group can add a Group trip (only one trip per Group)
     * @param trip
     * @throws InvalidPermissionException
     */
    public void addGroupTrip(Trip trip) throws InvalidPermissionException{
        if(!(this.groupTrip == null)){
            throw new InvalidPermissionException("Cannot add a trip because there is already one");
        }
        this.groupTrip = trip;
    }

    /**
     * A groupTrip can only be deleted if the loggedUser is the Group admin
     * @param trip
     */
    public void deleteGroupTrip(Trip trip){
        this.groupTrip = null;
    }

    /**
     * @return costs of the travel group
     */
    public Double getCosts(){
        return this.groupTrip.getEstimateCost();
    }

    /**
     * @return the costs divided the total amount of members, this way you get the
     * cost of each individual member
     */
    public Double getCostsPerMember(){
        return this.groupTrip.getEstimateCost()/members.size();
    }

    /**
     * @return the amount of group members
     */
    public Integer groupSize(){
        return this.members.size();
    }

    /**
     * @return Returns the maximum group size
     */
    public Integer getMaxGroupSize() {
        return maxGroupSize;
    }

    /**
     * Sets the maximum group size validating it
     * @param maxGroupSize
     */
    public void setMaxGroupSize(Integer maxGroupSize) {
        if(maxGroupSize == null ||maxGroupSize < 1)
            throw new IllegalArgumentException("The size is either null or not greater than 0");
        this.maxGroupSize = maxGroupSize;
    }

    /**
     * @return Returns a HashSet with the requests to enter the Group
     */
    public HashMap<Profile, RequestStatus> getMemberRequests() {
        return memberRequests;
    }

    /**
     * @return Returns the maximum age required to be member of the Group
     */
    public Integer getFilter1_age() {
        return filterAge;
    }

    /**
     * Sets the maximum age required to be member of the Group
     * @param filter
     */
    public void setFilterAge(Integer filter) {
        if(filter == null || (filter <= 0 && filter >= 120)){
            throw new IllegalArgumentException("The max age is not between 1 and 119");
        }
        this.filterAge = filter;
    }

    /**
     * @return Returns the city (the city in Profile) required to be a member of the Group
     */
    public String getFilterCity() {
        return filterCity;
    }

    /**
     * Sets the city required to be a member of the Group
     * @param filter
     */
    public void setFilterCity(String filter) {
        if(filter == null || filter.trim().isEmpty()){
            throw new IllegalArgumentException("The city is either null or empty");
        }
        this.filterCity = filter;
    }

    /**
     * @return Returns the Trip associated to the Group
     */
    public Trip getGroupTrip(){
        return this.groupTrip;
    }

    public Integer getId() {
        return groupId;
    }

    /**
     * Method to load list of members when loading object from database.
     * @param list
     */
    public void setMembers(HashSet<Profile> list){
        this.members = list;
    }

    public void setMissingReviewsToMake(
            HashMap<Profile, HashSet<Profile>> missingReviewsToMake) {
        this.missingReviewsToMake = missingReviewsToMake;
    }

    public void setRequests(HashMap<Profile, RequestStatus> requests) {
        this.memberRequests = requests;
    }
}
