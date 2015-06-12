package domain;

import java.util.Collection;
import java.util.Date;
import java.util.HashSet;

import com.j256.ormlite.field.DataType;
import com.j256.ormlite.field.DatabaseField;
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
    private Coordinates checkIn;

    /**the users friends*/
    @DatabaseField(dataType = DataType.SERIALIZABLE)
    private HashSet<Profile> friends;

    /**the users blocked users*/
    @DatabaseField(dataType = DataType.SERIALIZABLE)
    private HashSet<Profile> blockedUsr;

    /**the users past trips*/
    @DatabaseField(dataType = DataType.SERIALIZABLE)
    private HashSet<Trip> trips;

    /**the users reviews*/
    @DatabaseField(dataType = DataType.SERIALIZABLE)
    private HashSet<Review> reviews;

    /**the groups the user belongs to*/
    @DatabaseField(dataType = DataType.SERIALIZABLE)
    private HashSet<Group> groups;

    @DatabaseField
    private String password = null;

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
        this.trips= new HashSet<Trip>();
        this.reviews = new HashSet<Review>();
        this.groups=new HashSet<Group>();
        this.password=password;
        this.city=city;
        this.email=email;
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


    /**
     * @return the users usrName
     */
    public String getUsrName() {
        return usrName;
    }

    /**
     * @return the users usrId
     */
    public Integer getUsrId() {
        return usrId;
    }

    /**
     * @return the users name
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
     * @return the users sex
     */
    public boolean getSex(){
        return this.sex;
    }

    /**
     * @return the users BirthDay
     */
    public Date getBirthDay(){
        return this.brthDay;
    }

    /**
     * @return the users rating
     */
    public Double getRating(){
    	Double rating1 = 0.0;
    	for (Review review : reviews) {
			rating1 += review.getRating();
		}
    	if(reviews.size() != 0)
    		rating1 = rating/reviews.size();
        return rating1;
    }

    /**
     * @param rating that will be updated on the users profile, should be automatically updated each time a review is added
     */
    public void setRating(Double rating){
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
        Coordinates coor=new Coordinates(x,y);
        this.checkIn=coor;
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
     * @param tripId that will be added to the users trips
     */
    public void joinTrip(Trip trip){
        this.trips.add(trip);
    }

    /**
     * @param tripId that will be deleted from the users trips
     */
    public void leaveTrip(Trip trip){
        this.trips.remove(trip);
    }

    /**
     * @param rev that will be added to the users reviews
     * the users rating will be automatically updated when addReview is invoked
     */
    public void addReview(Profile rec, Profile send, String msg, Integer rating){
    	Review rev = new Review(rec, send, msg, rating);
        this.reviews.add(rev);
        Double rat = this.rating;
        Integer size = this.reviews.size();
        rat += rev.getRating();
        rat /= size;
        this.rating = rat;
    }

    /**
     * Will be used after two users have made a trip together, only a user that has shared a trip with the user can make a review
     * on the user
     * @param user the review is being sent to
     * @param rating, numerical value between 1 and 5 representing the users rating in the review
     * @param comment short comment describing the users performance
     * @throws IllegalArgumentException
     */
    /*
    public void sendReview(Profile user, Integer rating, String comment) throws IllegalArgumentException{
        if(rating<1 || rating>5)
            throw new IllegalArgumentException("The rating has to be between 1 and 5");

        Review rev=new Review(user, this, comment, rating);
        user.addReview(user);
    }
    */
    

    /**
     * @param group of group that the user will be added to
     */
    public void joinGroup(Group group){
        this.groups.add(group);
    }

    /**
     * @param group of the group the user is leaving
     */
    public void leaveGroup(Group group){
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
            throw new InvalidPasswordException("Password is not valid");
        this.password=newPass;
    }

    /**
     * @param blckdUsr will be removed from usrId's friend list if it contains it
     * and will be added to the blocked user
     */
    public void blockUser(Profile blckdUser){
        this.friends.remove(blckdUser);
        this.blockedUsr.add(blckdUser);
    }

    /**
     * @param usrId of the user that should be unblocked
     */
    public void unBlockedUsr(Profile usr){
        this.blockedUsr.remove(usr);
    }

    /**
     * @param friend that will be added to the users friend list,
     * this will be added to friend's list as well
     */
    public void addFriend(Profile friend){
        this.friends.add(friend);
        friend.addFriend(this);
    }

    /**
     * @param friend that will be removed from the users friend list
     * this will also be deleted from friend's list
     */
    public void deleteFriend(Profile friend){
        this.friends.remove(friend);
        friend.addFriend(this);
    }

    /**
     * revisar
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

//
//    /**
//     * @param group the post will be added to
//     * @param msg that will be added to the group
//     * @throws IllegalArgumentException when the user does not belong to the requested group
//     */
//    public void postInGroup(Group group, String msg) throws IllegalArgumentException{
//        if(!this.groups.contains(group))
//            throw new IllegalArgumentException("the user does not belong to the requested group");
//        Message post=new Message(msg);
//        group.addPost(this, post);
//    }

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
