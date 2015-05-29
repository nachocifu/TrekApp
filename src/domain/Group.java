package domain;

import java.util.HashMap;
import java.util.HashSet;

import com.j256.ormlite.field.DataType;
import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

@DatabaseTable(tableName = "Group")
public class Group {

	
	@DatabaseField
	private String groupName;
	
	@DatabaseField
	/**
	 * 	Final, does not change
	 */
	private String groupId;
	
	/**
	 * The admin's user that will be used to validate permissions
	 */
	@DatabaseField
	private Profile admin;
	
	/**
	 * HashSet containing the users of the groups members
	 */
	@DatabaseField
	private HashSet<Profile> members;
	
	/**
	 * Hashset containing the trips of the group Trips
	 */
	@DatabaseField
	private HashSet<Trip> groupTrips;
	
	/**
	 *key: msgId, value:usrId of user that posted the message
	 */
	private HashMap<Message, Profile> wall;
	
	/**
	 * a value representing the costs that are to be shared equally between
	 * all members of the travel group
	 */
	private Double costs;
	
	public Group(String groupName, String groupId, Profile admin){
		this.groupName=groupName;
		this.groupId=groupId;
		this.admin=admin;
		this.members=new HashSet<Profile>();
		this.groupTrips=new HashSet<Trip>();
		this.wall=new HashMap<Message, Profile>();
		this.costs=null;
	}

	public Group(String groupName, Profile admin){
		this.admin=admin;
		this.groupName=groupName;
	}
	
	public Group(){
	}
	
	public String getGroupName(){
		return this.groupName;
	}
	
	public void setGroupName(String groupName){
		this.groupName=groupName;
	}
	
	public String getGroupId(){
		return this.groupId;
	}
	
	public Profile getAdminUser(){
		return this.admin;
	}
	
	public void setAdminUserId(Profile admin){
		this.admin=admin;
	}
	
	/**
	 * LoggedUser will add a member into the group, this will only be possible if the 
	 * loggedUser is the admin of the group
	 * @param user to be added to the Group
	 * @throws InvalidPermissionException
	 */
	public void addMember(Profile user) throws InvalidPermissionException{
		if(LoggedUser.getInstance().equals(admin))
			throw new InvalidPermissionException("the logged user has to be the group admin to add new members into the group");
		this.members.add(user);
		user.addGroup(this);
	}
	
	/**
	 * LoggedUser will delete a member of the group, this will only be possible if the
	 * logged user is the Group admin
	 * @param user that will be deleted
	 * @throws InvalidPermissionException
	 */
	public void deleteMember(Profile user) throws InvalidPermissionException, IllegalArgumentException{
		if(!members.contains(user))
			throw new IllegalArgumentException("the user that is trying to be deleted does not belong to the group");
		if(LoggedUser.getInstance().equals(admin))
			throw new InvalidPermissionException("the logged user has to be the group admin to add new members into the group");
		this.members.remove(user);
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
	public void deleteGroupTrip(Trip trip) throws InvalidPermissionException{
		if(!LoggedUser.getInstance().equals(admin))
			throw new InvalidPermissionException("Only the group admin can delete a Group Trip");
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
		if(!LoggedUser.getInstance().equals(admin) && !this.wall.get(msg).equals(LoggedUser.getInstance()))
			throw new InvalidPermissionException("the only users with permission to delete a wall post is the group admin or the writer of the post");

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
}

