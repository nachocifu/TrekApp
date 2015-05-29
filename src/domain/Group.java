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
	
	public void addMember(Profile user){
		this.members.add(user);
	}
	
	public void deleteMember(Profile user){
		this.members.remove(user);
	}
	
	public void addGroupTrip(Trip trip){
		this.groupTrips.add(trip);
	}
	
	public void deleteGroupTrip(Trip trip){
		this.groupTrips.remove(trip);
	}
	
	public void addPost(Profile user, Message msg){
		this.wall.put(msg, user);
	}
	
	public void deletePost(String msgId){
		this.wall.remove(msgId);
	}
	
	public void setCosts(Double costs){
		this.costs=costs;
	}
	
	public Double getCosts(){
		return this.costs;
	}


}

