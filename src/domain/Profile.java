package domain;

import java.util.Collection;
import java.util.Date;
import java.util.HashSet;

import com.j256.ormlite.field.DataType;
import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

/**
 * A users profile in the model. Its content is never modified 
 * because it is generated from an existing user. 
 * Modifications are done on the user itself.
 */
@DatabaseTable(tableName="Profile")
public class Profile {
	
	/** the users username in the system */
	@DatabaseField
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
	private Coordenates checkIn;
	
	/**the users friends*/
	@DatabaseField(dataType = DataType.SERIALIZABLE)
	private HashSet<Profile> friends;
	
	/**the users blocked users*/
	@DatabaseField(dataType = DataType.SERIALIZABLE)
	private HashSet<Profile> blockedUsr;
	
	/**the users past trips*/
	@DatabaseField(dataType = DataType.SERIALIZABLE)
	private HashSet<Profile> trips;
	
	/**the users reviews*/
	@DatabaseField(dataType = DataType.SERIALIZABLE)
	private HashSet<Profile> rev;
	
	
	//podria hacer que adentro tengo un hashmap con boolean,string que el boolean
	//indique si es admin o no
	/**the groups the user belongs to*/
	@DatabaseField(dataType = DataType.SERIALIZABLE)
	private HashSet<Profile> groups;
	
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
		this.trips= new HashSet<Profile>();
		this.rev = new HashSet<Profile>();
		this.groups=new HashSet<Profile>();
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
		return this.rating;
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
	public Coordenates getCheckIn(){
		return this.checkIn;
	}
	
	/**
	 * @param location where the user has checked-in
	 */
	public void setCoordeantes(Coordenates location){
		this.checkIn=location;
	}
	
	/**aca getters y setters no los documento porque probablemente no los terminemos usando*/
	
	public Collection<Profile> getFriends(){
		return this.friends;
	}
	
	public void setFriends(HashSet<Profile> friends){
		this.friends=friends;
	}
	
	public Collection<String> getBlockedUsrs(){
		return this.blockedUsr;
	}
	
	public void setBlockedUsrs(HashSet<String> blockedusrs){
		this.blockedUsr=blockedusrs;
	}
	
	public Collection<String> getTrips(){
		return this.trips;
	}
	
	public void setTrips(HashSet<String> trips){
		this.trips=trips;
	}
	
	public Collection<String> getReviews(){
		return this.rev;
	}
	
	public void setReviews(HashSet<String> revs){
		this.rev=revs;
	}
	
	public Collection<String> getGroups(){
		return this.groups;
	}
	
	public void setGroups(HashSet<String> groups){
		this.groups=groups;
	}
	
	
	
	/**
	 * @param usrId of the users new friend
	 */
	public void addFriend(Profile usrId){
		this.friends.add(usrId);
	}
	
	/**
	 * @param usrId of the user that will be removed from friends list
	 */
	public void deleteFriend(String usrId){
		this.friends.remove(usrId);
	}
	
	/**
	 * @param usrId of user that will be blocked
	 */
	public void addBlockedUsr(String usrId){
		this.blockedUsr.add(usrId);
	}
	
	/**
	 * @param usrId of the user that should be unblocked
	 */
	public void deleteBlockedUsr(String usrId){
		this.blockedUsr.remove(usrId);
	}
	
	/**
	 * @param tripId that will be added to the users trips
	 */
	public void addTrip(String tripId){
		this.trips.add(tripId);
	}
	
	/**
	 * @param revId that will be added to the users reviews
	 */
	public void addReview(String revId){
		this.rev.add(revId);
	}
	
	/**
	 * @param groupId of group that the user will be added to
	 */
	public void addGroup(String groupId){
		this.groups.add(groupId);
	}
	
	/**
	 * @param groupId of the group the user is leaving
	 */
	public void deleteGroup(String groupId){
		this.groups.remove(groupId);
	}
	
	/**
	 * @return users password
	 */
	public String getPassword(){
		return this.password;
	}
	
	/**
	 * @param password that will be changed
	 * all validations will be done on the ProfileService
	 */
	public void setPassword(String password){
		this.password=password;
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
