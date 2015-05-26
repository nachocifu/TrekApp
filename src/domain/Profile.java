package domain;

import java.util.Collection;
import java.util.Date;
import java.util.HashSet;

import com.j256.ormlite.field.DatabaseField;

/**
 * A users profile in the model. Its content is never modified 
 * because it is generated from an existing user. 
 * Modifications are done on the user itself.
 */
public class Profile {
	
	/** the users username in the system */
	private final String usrName;
	
	/** the users id */
	private final Integer usrId;
	
	/** the profile id*/
	private final Integer profileId;
	
	/** the users name */
	private final String name;
	
	/** the users surname */
	private final String surname;
	
	/**the users sex, true= female, false= male*/
	private boolean sex;
	
	/** the users brthDay */
	private  Date brthDay;
	
	/** the users average rating*/
	private Double rating; 
	
	/**the users friends*/
	private Collection<Profile> friends;
	
	/**the users blocked users*/
	private Collection<Profile> blockedUsr;
	
	/**the users past trips*/
	private Collection<Trip> trips;
	
	/**the users reviews*/
	private Collection<Review> rev;
	
	/**the groups the user belongs to*/
	private Collection<Group> groups;
	
	/**
	 * @param usrName
	 * @param name
	 * @param surname
	 * @param usrId
	 * @param profileId
	 * @param brthDay
	 * @param sex
	 */
	public Profile(String usrName, String name, String surname, Integer usrId, Integer profileId, Date brthDay, boolean sex){
		this.usrName = usrName;
		this.usrId = usrId;
		this.name = name;
		this.surname = surname;
		this.brthDay=brthDay;
		this.profileId=profileId;
		this.sex=sex;
		this.friends= new HashSet<Profile>();
		this.blockedUsr= new HashSet<Profile>();
		this.trips= new HashSet<Trip>();
		this.rev = new HashSet<Review>();
		this.groups=new HashSet<Group>();
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
	
	/**
	 * @return
	 */
	public Date getBirthDay()
	{
		return this.brthDay;
	}

	
	
	
	@Override
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