package domain;

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
    private Trip groupTrip;

    /**
     *key: msgId, value:usrId of user that posted the message
     */
    @DatabaseField(dataType = DataType.SERIALIZABLE)
    private HashMap<Message, Profile> wall;
    
    /**
     * 0 if he has been rejected and 1 if is waiting for acceptance
     */
    @DatabaseField(dataType = DataType.SERIALIZABLE)
    private HashMap<Profile, RequestStatus> memberRequests;
    
    @DatabaseField
    private Integer filterAge;
    
    @DatabaseField
    private String filterCity;
    
    @DatabaseField
    private Integer maxGroupSize;

    /*Group Constructors*/
    
    public Group(String groupName, Profile admin, Integer maxGroupSize, Integer filterAge, String filterCity){
        this.groupName=groupName;
        this.admin=admin;
        this.members=new HashSet<Profile>();
        this.wall=new HashMap<Message, Profile>();
        this.memberRequests = new HashMap<Profile, RequestStatus>();
        this.maxGroupSize = maxGroupSize;   
        this.filterAge = filterAge;
        this.filterCity = filterCity;
        admin.joinGroup(this);
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
     * Sets a new Group administrator (only one administrator per Group)
     * @param admin
     */
    public void setAdminUser(Profile admin){
        this.admin = admin;
    }

    /**
     * Adds a new member if there is space in the Group
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
     * Accepts a member of the request list if he has not been rejected
     * @param newMember
     */
    public void acceptMember(Profile newMember){
    	if(memberRequests.containsKey(newMember) && memberRequests.get(newMember) == RequestStatus.WAITING && this.maxGroupSize > this.groupSize()){
    		memberRequests.remove(newMember);
    		members.add(newMember);
    		newMember.joinGroup(this);
    		this.maxGroupSize += 1;
    	}
    }
    
    //VER COMO ARREGLAR LO DE DATE!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!
    /**
     * Adds a member request to be accepted or not using the filters
     * @param possibleMember
     */
    public void addMemberRequest(Profile possibleMember){
    	if(possibleMember.getBirthDay().equals(this.filterAge) && possibleMember.getCity().equals(this.filterCity))
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
        } else if(member.equals(this.admin) || groupSize() == 1){
        	member.leaveGroup(this);
        	return true;
        } else{
            this.members.remove(member);
            this.admin = this.members.iterator().next();
        }
        return false;
    }

    /**
     * Any member of the group can add a Group trip
     * @param trip
     * @throws InvalidPermissionException 
     */
    public void addGroupTrip(Profile user, Trip trip) throws InvalidPermissionException{
    	if(!this.members.contains(user))
    		throw new InvalidPermissionException("Cannot add a trip because user is not a member of this group");
    	else if(this.groupTrip == null){
    		this.groupTrip = trip;
    	}
    }

    /**
     * A groupTrip can only be deleted if the loggedUser is the Group admin
     * @param trip
     */
    public void deleteGroupTrip(Trip trip){
        this.groupTrip = null;
    }

    /**
     * @param user posting the message on the wall
     * @param msg being posted
     * @throws InvalidPermissionException 
     */
    public void addPost(Profile user, Message msg) throws InvalidPermissionException{
    	if(!this.members.contains(user)){
    		throw new InvalidPermissionException("Cannot post because user is not a member of this group");
    	}else if(msg == null || msg.getText().trim().isEmpty()){
    		throw new IllegalArgumentException("Cannot post because he message is either null or empty");
    	}
    	this.wall.put(msg, user);

    	
    }

    /**
     * Deletes a post in the Group
     * @param msgId
     */
    public void deletePost(Message msg) throws IllegalArgumentException, InvalidPermissionException{
    	if(!this.wall.containsKey(msg))
    		throw new IllegalArgumentException("The message does not exist");
        this.wall.remove(msg);
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

	public Integer getMaxGroupSize() {
		return maxGroupSize;
	}

	public void setMaxGroupSize(Integer maxGroupSize) {
		this.maxGroupSize = maxGroupSize;
	}

	public HashMap<Profile, RequestStatus> getMemberRequests() {
		return memberRequests;
	}

	public Integer getFilter1_age() {
		return filterAge;
	}

	public void setFilterAge(Integer filter) {
		if(filter == null || (filter <= 0 && filter >= 120)){
			throw new IllegalArgumentException("The max age is not between 1 and 119");
		}
		this.filterAge = filter;
	}

	public String getFilterCity() {
		return filterCity;
	}

	public void setFilterCity(String filter) {
		if(filter == null || filter.trim().isEmpty()){
			throw new IllegalArgumentException("The city is either null or empty");
		}
		this.filterCity = filter;
	}
	
	public Trip getGroupTrip(){
		return this.groupTrip;
	}
	
	
    
    
}

