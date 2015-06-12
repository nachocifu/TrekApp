package domain;

import java.util.Collection;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;

import com.j256.ormlite.field.DataType;
import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

@DatabaseTable(tableName = "Group")
public class Group {


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
    @DatabaseField( foreign = true )
    private Profile admin;

    /**
     * HashSet containing the users of the groups members
     */
    @DatabaseField(dataType = DataType.SERIALIZABLE)
    private HashSet<Profile> members;

    /**
     * HashSet containing the trips of the group Trips
     */
    @DatabaseField(dataType = DataType.SERIALIZABLE)
    private HashSet<Trip> groupTrips;

    /**
     *key: msgId, value:usrId of user that posted the message
     */
    @DatabaseField(dataType = DataType.SERIALIZABLE)
    private HashMap<Message, Profile> wall;

    /**
     * a value representing the costs that are to be shared equally between
     * all members of the travel group
     */
    @DatabaseField
    private Double costs;
    
    /**
     * 0 if he has been rejected and 1 if is waiting for acceptance
     */
    private HashMap<Profile, Integer> memberRequests;
    
    @DatabaseField
    private Integer filter1_edad;
    
    @DatabaseField
    private String filter2_ciudad;
    
    @DatabaseField
    private Integer maxGroupSize;

    public Group(String groupName, Profile admin){
        this.groupName=groupName;
        this.admin=admin;
        this.members=new HashSet<Profile>();
        this.groupTrips=new HashSet<Trip>();
        this.wall=new HashMap<Message, Profile>();
        this.costs=null;
        this.memberRequests = new HashMap<Profile, Integer>();
        this.maxGroupSize = null;   
        this.filter1_edad = null;
        this.filter2_ciudad = null;
    }

    public Group(){
    }

    public Collection<Profile> getMembers(){
        return this.members;
    }

    public String getGroupName(){
        return this.groupName;
    }

    public void setGroupName(String groupName){
        this.groupName=groupName;
    }

    public Profile getAdminUser(){
        return this.admin;
    }

    public void setAdminUser(Profile admin){
        this.admin=admin;
    }

    /**
     * LoggedUser will add a member into the group, this will only be possible if the
     * loggedUser is the admin of the group
     * @param user to be added to the Group
     * @throws InvalidPermissionException
     */
    public void addMember(Profile user){
    	if(this.maxGroupSize > this.groupSize()){
    		 this.members.add(user);
    	     user.joinGroup(this);
    	}
    }
    
    /**
     * Accepts a member of the request list if he hasn´t been rejected
     * @param newMember
     */
    public void acceptMember(Profile newMember){
    	if(memberRequests.containsKey(newMember) && memberRequests.get(newMember) != 0 && this.maxGroupSize > this.groupSize()){
    		memberRequests.remove(newMember);
    		members.add(newMember);
    		newMember.joinGroup(this);
    		this.maxGroupSize += 1;
    	}
    }
    
    //VER COMO ARREGLAR LO DE DATE
    /**
     * Adds a member request to be accepted or not using the filters
     * @param possibleMember
     */
    public void addMemberRequest(Profile possibleMember){
    	if(possibleMember.getBirthDay().getYear().equals(this.filter1_edad) && possibleMember.getCity().equals(this.filter2_ciudad))
    	memberRequests.put(possibleMember, 0);
    }

    /**
     * LoggedUser will delete a member of the group, this will only be possible if the
     * logged user is the Group admin
     * @param user that will be deleted
     * @throws InvalidPermissionException
     */
    public void deleteMember(Profile user) throws IllegalArgumentException{
        if(!members.contains(user))
            throw new IllegalArgumentException("the user that is trying to be deleted does not belong to the group");
        this.members.remove(user);
        this.maxGroupSize -= 1;
    }

    /**
     * Any member of the group can add a Group trip
     * @param trip
     */
    public void addGroupTrip(Trip trip){
        this.groupTrips.add(trip);
    }

    /**
     * A groupTrip can only be deleted if the loggedUser is the Group admin
     * @param trip
     */
    public void deleteGroupTrip(Trip trip){
        this.groupTrips.remove(trip);
    }

    /**
     * @param user posting the message on the wall
     * @param msg being posted
     */
    public void addPost(Profile user, Message msg){
        this.wall.put(msg, user);
    }

    /**
     * Only the group admin or the writer of the message can delete the message
     * @param msgId
     */
    public void deletePost(Message msg) throws IllegalArgumentException, InvalidPermissionException{
        if(!this.wall.containsKey(msg))
            throw new IllegalArgumentException("the message does not exists");
        this.wall.remove(msg);
    }

    /**
     * @param costs of the travel group
     */
    public void setCosts(Double costs){
        this.costs=costs;
    }

    /**
     * @param newCost that will be added to the travel costs
     */
    public void addCost(Double newCost){
        this.costs=this.costs+newCost;
    }

    /**
     * @return costs of the travel group
     */
    public Double getCosts(){
        return this.costs;
    }

    /**
     * @return the costs divided the total amount of members, this way you get the
     * cost of each individual member
     */
    public Double getCostsPerMember(){
        return costs/members.size();
    }

    /**
     * @return the amount of group members
     */
    public Integer groupSize(){
        return this.members.size();
    }

	public Integer getMaxGroupSize() {
		return maxGroupSize;
	}

	public void setMaxGroupSize(Integer maxGroupSize) {
		this.maxGroupSize = maxGroupSize;
	}

	public HashMap<Profile, Integer> getMemberRequests() {
		return memberRequests;
	}

	public Integer getFilter1_edad() {
		return filter1_edad;
	}

	public void setFilter1_edad(Integer filter1_edad) {
		this.filter1_edad = filter1_edad;
	}

	public String getFilter2_ciudad() {
		return filter2_ciudad;
	}

	public void setFilter2_ciudad(String filter2_ciudad) {
		this.filter2_ciudad = filter2_ciudad;
	}
	
	
    
    
}

