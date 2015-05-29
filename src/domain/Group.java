package domain;

import java.util.HashMap;
import java.util.HashSet;

import com.j256.ormlite.field.DataType;
import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

@DatabaseTable(tableName = "Group")
public class Group {

    /** The name of the Group */
    @DatabaseField
    private String groupName;


    /** The id of the group.*/
    @DatabaseField(generatedId = true)
    private Integer groupId;

    /**
     * The admin's userId that will be used to validate permissions
     */
    @DatabaseField
    private String adminUserId;

    /**
     * HashSet containing the userId's of the groups members
     */
    @DatabaseField(dataType = DataType.SERIALIZABLE, foreign =true)
    private HashSet<Profile> members;

    /**
     * Hashset containing the tripIds of the group Trips
     */
    @DatabaseField(dataType = DataType.SERIALIZABLE, foreign = true)
    private HashSet<Trip> groupTrips;


    /**
     *key: msgId, value:usrId of user that posted the message
     */
    @DatabaseField(dataType = DataType.SERIALIZABLE)
    private HashMap<Message, Profile> posts;

    //private double avlbPlaces;

    private Double costs;

    /** Empty contructor for ORM persistance*/
    public Group(){

    }

    public Group(String groupName, String adminUserId){
        this.groupName=groupName;
        this.adminUserId=adminUserId;
        this.members=new HashSet<Profile>();
        this.groupTrips=new HashSet<Trip>();
        this.posts=new HashMap<Message, Profile>();
        this.costs=null;
    }

    public String getGroupName(){
        return this.groupName;
    }

    public void setGroupName(String groupName){
        this.groupName=groupName;
    }

    public Integer getGroupId(){
        return this.groupId;
    }

    public String getAdminUserId(){
        return this.adminUserId;
    }

    public void setAdminUserId(String admin){
        this.adminUserId=admin;
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
        this.posts.put(msg, user);
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

