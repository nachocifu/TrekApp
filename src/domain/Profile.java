package domain;

import java.util.Collection;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;

import com.j256.ormlite.dao.ForeignCollection;
import com.j256.ormlite.field.DataType;
import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.field.ForeignCollectionField;
import com.j256.ormlite.table.DatabaseTable;

import domain.Coordinates;
import domain.Group;
import domain.Review;
import domain.Trip;

/**
 * A users profile in the model. Its content is never modified
 * because it is generated from an existing user.
 * Modifications are done on the user itself.
 */
@DatabaseTable(tableName="Profile")
public class Profile {

    /*Profile Attributes*/

    /** the users username in the system */
    @DatabaseField( unique = true)
    private String usrName = null;

    /** the users id */

    @DatabaseField( generatedId = true)
    private Integer usrId = null;

    /** the users name */
    @DatabaseField
    private  String name = null;

    /** the users surname */
    @DatabaseField
    private  String surname = null;

    /**the users sex, true= female, false= male*/
    @DatabaseField
    private  boolean sex = false;

    /** the users brthDay */
    @DatabaseField
    private Date brthDay = null;

    /** the users average rating*/
    @DatabaseField
    private Double rating = null;

    /**the users city*/
    @DatabaseField
    private String city = null;

    /**the users email*/
    @DatabaseField
    private String email = null;

    /**
     *the user will be able to checkIn in a specific location, it will save the last
     *location where the user checked-in
     */
    private Coordinates checkIn = null;

    /**the users friends*/
    @ForeignCollectionField
    private HashSet<Profile> friends;

    /**the users blocked users*/
    @ForeignCollectionField
    private HashSet<Profile> blockedUsr;

    /**the users past trips*/
    @ForeignCollectionField
    private HashSet<Trip> trips;

    /**the users reviews*/
    @ForeignCollectionField
    private HashSet<Review> reviews;

    /**the groups the user belongs to*/
    @ForeignCollectionField
    private HashSet<Group> groups;

    /**
     * REJECTED if he has been rejected and WAITING if is waiting for acceptance
     */
    private HashMap<Profile, RequestStatus> friendRequests;

    @DatabaseField
    private String password = null;

    /*Profile Constructors*/

    /** Empty constructor for ORM persistence*/
    public Profile(){

    }

    /**
     * @param usrName
     * @param name
     * @param surname
     * @param usrId
     * @param profileId
     * @param brthDay
     * @param sex
     */
    public Profile(String usrName, String name, String surname, Integer usrId, Date brthDay, boolean sex, String password, String city, String email){
        this.usrName = usrName;
        this.usrId = usrId;
        this.name = name;
        this.surname = surname;
        this.brthDay=brthDay;
        this.sex=sex;
        this.checkIn=null;
        this.friends= new HashSet<Profile>();
        this.blockedUsr= new HashSet<Profile>();
        this.trips = new HashSet<Trip>();
        this.reviews = new HashSet<Review>();
        this.groups = new HashSet<Group>();
        this.friendRequests = new HashMap<Profile, RequestStatus>();
        this.password = password;
        this.city = city;
        this.email = email;
    }

    /**
     * @param usrName
     * @param name
     * @param surname
     * @param usrId
     * @param profileId
     * @param brthDay
     * @param sex
     */
    public Profile(String usrName, String name, String surname, Date brthDay, boolean sex, String password, String city, String email){
        this(usrName, name, surname, null, brthDay, sex, password, city, email);
    }

    /*Profile Methods*/

    /**
     * Accepts a friend of the friend request list if he has not been rejected
     * @param newMember
     */
    public void acceptFriend(Profile newFriend){
        if (!friendRequests.containsKey(newFriend)){
            throw new IllegalArgumentException("The user does not belong to the users requesting a to be friends with this profile");
        }else if(!(friendRequests.get(newFriend) == RequestStatus.WAITING)){
            throw new IllegalArgumentException("The user has been rejected and cannot be accespted as a friend");
        }
        friendRequests.remove(newFriend);
        friends.add(newFriend);
        newFriend.getFriends().add(this);
    }

    /**
     * Adds a friend request to be accepted or not
     * @param possibleMember
     * @throws InvalidPermissionException
     */
    public void addFriendRequest(Profile possibleFriend) throws InvalidPermissionException{
        if(friendRequests.containsKey(possibleFriend)){
            throw new IllegalArgumentException("The user already belongs to the users requesting to be friends with this profile");
        }else if(blockedUsr.contains(possibleFriend)){
            throw new IllegalArgumentException("The user is blocked and cannot send a friend request");
        }
        friendRequests.put(possibleFriend, RequestStatus.WAITING);
    }

    /**
     * Adds a friend request to be accepted or not
     * @param possibleMember
     * @throws InvalidPermissionException
     */
    public void sendFriendRequest(Profile possibleFriend) throws InvalidPermissionException{
        possibleFriend.addFriendRequest(this);
    }

    /**
     * Rejects a member of the request list
     * @param rejectedProfile
     */
    public void rejectAFriendRequest(Profile rejectedProfile){
        if(!friendRequests.containsKey(rejectedProfile)){
            throw new IllegalArgumentException("The user does not belong to the users requesting to be friends with this profile");
        }else if(!(friendRequests.get(rejectedProfile) == RequestStatus.REJECTED)){
            throw new IllegalArgumentException("The user has already been rejected");
        }
        friendRequests.put(rejectedProfile, RequestStatus.REJECTED);
    }

    /**
     * @return the user username
     */
    public String getUsrName() {
        return usrName;
    }

    /**
     * @return the user usrId
     */
    public Integer getUsrId() {
        return usrId;
    }

    /**
     * @return the user name
     */
    public String getName() {
        return name;
    }

    /**
     * @return the surname
     */
    public String getSurname() {
        return surname;
    }

    /**
     * @return the user sex
     */
    public boolean getSex(){
        return this.sex;
    }

    /**
     * @return the user birthDay
     */
    public Date getBirthDay(){
        return this.brthDay;
    }

    /**
     * @return the user rating
     */
    public Double getRating(){
        return this.rating;
    }

    /**
     * @param rating that will be updated on the users profile, should be automatically updated each time a review is added
     */
    public void setRating(Double rating){
        if(rating == null){
            throw new IllegalArgumentException("The rating is null");
        }
        this.rating=rating;
    }

    /**
     * @return the users actual city
     */
    public String getCity(){
        return this.city;
    }

    /**
     * @param city
     */
    public void setCity(String city){
        if(rating == null || city.trim().isEmpty()){
            throw new IllegalArgumentException("The rating is either null or empty");
        }
        this.city=city;
    }

    /**
     * @return the users email
     */
    public String getEmail(){
        return this.email;
    }

    /**
     * @param email
     */
    public void setEmail(String email){
        if(rating == null || city.trim().isEmpty()){
            throw new IllegalArgumentException("The e-mail is either null or empty");
        }
        this.email=email;
    }

    /**
     * @return the last location where the user has checked in
     */
    public Coordinates getCheckIn(){
        return this.checkIn;
    }

    /**
     * @param location where the user has checked-in
     */
    public void checkIn(Coordinates location){
        this.checkIn=location;
    }

    /**
     * @param x coordinate from the users location
     * @param y coordinate from the users location
     * @deprecated ESTO AL FINAL SE USA O LO PODEMOS VOLAR???
     */
    public void checkIn(Double x, Double y){
        Coordinates coor = new Coordinates(x,y);
        this.checkIn = coor;
    }

    public Collection<Profile> getFriends(){
        return this.friends;
    }

    public Collection<Profile> getBlockedUsrs(){
        return this.blockedUsr;
    }

    public Collection<Trip> getTrips(){
        return this.trips;
    }

    public Collection<Review> getReviews(){
        return this.reviews;
    }

    public Collection<Group> getGroups(){
        return this.groups;
    }

    /**
     * @param rev that will be added to the users reviews
     * the users rating will be automatically updated when addReview is invoked
     */
    public void addReview(Profile sender, String msg, Integer rating){
        if(msg == null || msg.trim().isEmpty()){
            throw new IllegalArgumentException("The msg is either null or empty");
        }else if(rating == null
                || rating < 0
                || rating > 5){
            throw new IllegalArgumentException("The rating is either null or not between 0 and 5");
        }
        Review rev = new Review(this, sender, msg, rating);
        this.reviews.add(rev);
        updateRating(rating);
    }

    /**
     * Update the rating when adding a new review
     * @param rating
     */
    private void updateRating(Integer rating){
        Double rat = this.rating;
        Integer size = this.reviews.size();
        rat += rating;
        rat /= size;
        this.rating = rat;
    }

    /**
     * @param group of group that the user will be added to
     */
    public void joinGroup(Group group){
        if(groups.contains(group))
            throw new IllegalArgumentException("The user already belongs to that group");
        this.groups.add(group);
    }

    /**
     * @param group of the group the user is leaving
     */
    public void leaveGroup(Group group){
        if(!groups.contains(group))
            throw new IllegalArgumentException("The user does not belong to that group");
        this.groups.remove(group);
    }

    /**
     * @return a boolean value indicating if the password is correct, this way the password remains private and is never
     * shared with a different object
     * @deprecated REALMENTE NECESITAMOS ESTO? DEJEMOSLO POR LAS DUDAS
     */
    public boolean cmpPassword(String pass){
        if(this.password.equals(pass))
            return true;
        return false;
    }

    /**
     * @param oldPass used to validate
     * @param newPass that will be established for the user
     * @return
     * @throws InvalidPasswordException if the oldpass is not valid
     */
    public void changePass(String oldPass, String newPass) throws InvalidPasswordException{
        if(!this.password.equals(oldPass))
            throw new InvalidPasswordException("The old password does not match");
        if(newPass == null || newPass.trim().isEmpty()){
            throw new InvalidPasswordException("The new password is either null or empty");
        }
        this.password = newPass;
    }

    /**
     * @param blckdUsr will be removed from usrId's friend list if it contains it
     * and will be added to the blocked user
     */
    public void blockUser(Profile blckdUser){
        if(blockedUsr.contains(blckdUser))
            throw new IllegalArgumentException("The user is already blocked");
        this.friends.remove(blckdUser);
        this.blockedUsr.add(blckdUser);
    }

    /**
     * @param usrId of the user that should be unblocked
     */
    public void unBlockedUsr(Profile usr){
        if(!blockedUsr.contains(usr))
            throw new IllegalArgumentException("The user is already unblocked");
        this.blockedUsr.remove(usr);
    }

    /**
     * @param friend that will be removed from the users friend list
     * this will also be deleted from friend's list
     */
    public void deleteFriend(Profile friend){
        if(friends.contains(friend))
            throw new IllegalArgumentException("The user is not a friend");
        this.friends.remove(friend);
        friend.getFriends().remove(this);
    }

    /**
     * @return user friend requests
     */
    public HashMap<Profile, RequestStatus>getFriendRequests(){
        return this.friendRequests;
    }

    /**
     * NO VA
     * @return the age, it compares todays date with the day of birth of the user
     */
    @SuppressWarnings("deprecation")
    public int getAge(){
        Date dt = new Date();
        Date aux= this.getBirthDay();
        if(dt.getMonth() < aux.getMonth())
            return dt.getYear()-aux.getYear();
        if(dt.getMonth() > aux.getMonth())
            return dt.getYear()-aux.getYear() +1;
        if(dt.getDay() < dt.getDay())
            return dt.getYear() - aux.getYear();
        return dt.getYear() - aux.getYear() +1;
    }

    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((brthDay == null) ? 0 : brthDay.hashCode());
        result = prime * result + ((name == null) ? 0 : name.hashCode());
        result = prime * result + ((surname == null) ? 0 : surname.hashCode());
        result = prime * result + ((usrId == null) ? 0 : usrId.hashCode());
        result = prime * result + ((usrName == null) ? 0 : usrName.hashCode());
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        Profile other = (Profile) obj;
        if (brthDay == null) {
            if (other.brthDay != null)
                return false;
        } else if (!brthDay.equals(other.brthDay))
            return false;
        if (name == null) {
            if (other.name != null)
                return false;
        } else if (!name.equals(other.name))
            return false;
        if (surname == null) {
            if (other.surname != null)
                return false;
        } else if (!surname.equals(other.surname))
            return false;
        if (usrId == null) {
            if (other.usrId != null)
                return false;
        } else if (!usrId.equals(other.usrId))
            return false;
        if (usrName == null) {
            if (other.usrName != null)
                return false;
        } else if (!usrName.equals(other.usrName))
            return false;
        return true;
    }
}
