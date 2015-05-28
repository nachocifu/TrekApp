package domain;

import java.util.HashMap;
import java.util.HashSet;

import com.j256.ormlite.field.DatabaseField;

public class Group {
	
	
	@DatabaseField
	private String groupName;
	
	@DatabaseField
	/**
	 * 	Final, does not change
	 */
	private String groupId;
	
	/**
	 * The admin's userId that will be used to validate permissions
	 */
	@DatabaseField
	private String adminUserId;
	
	/**
	 * HashSet containing the userId's of the groups members
	 */
	@DatabaseField
	private HashSet<String> members;
	
	/**
	 * Hashset containing the tripIds of the group Trips
	 */
	@DatabaseField
	private HashSet<String> groupTrips; 
	
	
	/**
	 *key: msgId, value:usrId of user that posted the message
	 */
	private HashMap<String, String> posts;
	
	//private double avlbPlaces;
	
	private Double costs;
	
	public Group(String groupName, String groupId, String adminUserId){
		this.groupName=groupName;
		this.groupId=groupId;
		this.adminUserId=adminUserId;
		this.members=new HashSet<String>();
		this.groupTrips=new HashSet<String>();
		this.posts=new HashMap<String, String>();
		this.costs=null;
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
	
	public String getAdminUserId(){
		return this.adminUserId;
	}
	
	public void setAdminUserId(String admin){
		this.adminUserId=admin;
	}
	
	public void addMember(String userId){
		this.members.add(userId);
	}
	
	public void deleteMember(String userId){
		this.members.remove(userId);
	}
	
	public void addGroupTrip(String tripId){
		this.groupTrips.add(tripId);
	}
	
	public void deleteGroupTrip(String tripId){
		this.groupTrips.remove(tripId);
	}
	
	public void addPost(String usrId, String msgId){
		this.posts.put(usrId, msgId);
	}
	
	public void deletePost(String msgId){
		this.posts.remove(msgId);
	}
	
	public void setCosts(Double costs){
		this.costs=costs;
	}
	
	public Double getCosts(){
		return this.costs;
	}
	

}

