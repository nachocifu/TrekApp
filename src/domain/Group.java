package domain;

import java.util.Collection;
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
     * Hashset containing the trips of the group Trips
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

    public Group(String groupName, Profile admin){
        this.groupName=groupName;
        this.admin=admin;
        this.members=new HashSet<Profile>();
        this.groupTrips=new HashSet<Trip>();
        this.wall=new HashMap<Message, Profile>();
        this.costs=null;
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
        this.members.add(user);
        user.joinGroup(this);
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
}

