package domain;

import java.lang.annotation.Target;
import java.util.Collection;
import java.util.Date;
import java.util.HashSet;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

/**
 * A users profile in the model. Its content is never modified 
 * because it is generated from an existing user. 
 * Modifications are done on the user itself.
 */
@DatabaseTable(tableName="User")
public class Profile {
	
	/** the users username in the system */
	private final String usrName;
	
	/** the users id */
	@DatabaseField( id = true)
	private final Integer usrId;
	
	/** the users name */
	@DatabaseField
	private final String name;
	
	/** the users surname */
	@DatabaseField
	private final String surname;
	
	/**the users sex, true= female, false= male*/
	@DatabaseField
	private final boolean sex;
	
	/** the users brthDay */
	@DatabaseField
	private  final Date brthDay;
	
	/** the users average rating*/
	@DatabaseField
	private Double rating; 
	
	/**the users friends*/
	@DatabaseField
	private Collection<Integer> friends;
	
	/**the users blocked users*/
	@DatabaseField
	private Collection<Integer> blockedUsr;
	
	/**the users past trips*/
	@DatabaseField
	private Collection<Integer> trips;
	
	/**the users reviews*/
	@DatabaseField
	private Collection<Integer> rev;
	
	/**the groups the user belongs to*/
	@DatabaseField
	private Collection<Integer> groups;
	
	@DatabaseField
	private String password;
	
	/**
	 * @param usrName
	 * @param name
	 * @param surname
	 * @param usrId
	 * @param profileId
	 * @param brthDay
	 * @param sex
	 */
	public Profile(String usrName, String name, String surname, Integer usrId, Date brthDay, boolean sex, String password){
		this.usrName = usrName;
		this.usrId = usrId;
		this.name = name;
		this.surname = surname;
		this.brthDay=brthDay;
		this.sex=sex;
		this.friends= new HashSet<Integer>();
		this.blockedUsr= new HashSet<Integer>();
		this.trips= new HashSet<Integer>();
		this.rev = new HashSet<Integer>();
		this.groups=new HashSet<Integer>();
		this.password=password;
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
	public Profile(String usrName, String name, String surname, Date brthDay, boolean sex, String password){
		this(usrName, name, surname, null, brthDay, sex, password);
	}

	
	/**
	 * @return the usrName
	 */
	public String getUsrName() {
		return usrName;
	}
	
	/**
	 * @return the usrId
	 */
	public Integer getUsrId() {
		return usrId;
	}

	/**
	 * @return the name
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
	
	public boolean getSex()
	{
		return this.sex;
	}
	
	/**
	 * @return
	 */
	public Date getBirthDay()
	{
		return this.brthDay;
	}

	public Double getRating()
	{
		return this.rating;
	}
	
	public void setRating(Double rating)
	{
		this.rating=rating;
	}
	
	public Collection<Integer> getFriends()
	{
		return this.friends;
	}
	
	public void setFriends(Collection<Integer> friends)
	{
		this.friends=friends;
	}
	
	public Collection<Integer> getBlockedUsrs()
	{
		return this.blockedUsr;
	}
	
	public void setBlockedUsrs(Collection<Integer> blockedusrs)
	{
		this.blockedUsr=blockedusrs;
	}
	
	public Collection<Integer> getTrips()
	{
		return this.trips;
	}
	
	public void setTrips(Collection<Integer> trips)
	{
		this.trips=trips;
	}
	
	public Collection<Integer> getReviews()
	{
		return this.rev;
	}
	
	public void setReviews(Collection<Integer> revs)
	{
		this.rev=revs;
	}
	
	public Collection<Integer> getGroups()
	{
		return this.groups;
	}
	
	public void setGroups(Collection<Integer> groups)
	{
		this.groups=groups;
	}
	
	public void addFriend(Integer usrId)
	{
		this.friends.add(usrId);
	}
	
	public void deleteFriend(Integer usrId){
		this.friends.remove(usrId);
	}
	
	public void addBlockedUsr(Integer usrId){
		this.blockedUsr.add(usrId);
	}
	
	public void deleteBlockedUsr(Integer usrId){
		this.blockedUsr.remove(usrId);
	}
	
	public void addTrip(Integer tripId){
		this.trips.add(tripId);
	}
	
	public void addReview(Integer revId){
		this.rev.add(revId);
	}
	
	public void addGroup(Integer groupId){
		this.groups.add(groupId);
	}
	
	public void deleteGroup(Integer groupId){
		this.groups.remove(groupId);
	}
	
	public String getPassword(){
		return this.password;
	}
	
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
