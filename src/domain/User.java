package domain;

import java.util.Collection;
import java.util.Date;
import java.util.HashSet;

import services.invalidPasswordException;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

/**
 * The representation of a user in the model
 * @author nati
 *@deprecated
 */

@Deprecated
public class User {
	
	/** the users profile, containing name, surname, birthdate, usrId and usrName*/
	@DatabaseField
	private Profile user;
	
	/** the users password */ 
	@DatabaseField
	private String password;
	
	/**the users friends*/
	private Collection<Profile> friends;
	
	/**the users blocked users*/
	private Collection<Profile> blockedUsr;
	
	/** Empty constructor for ORM persistence*/
	public User(){
	}
	
	public User(String name,  String surname, String userName,  String password,  
					Integer userId,  Date brthDay){
		
		this.user=new Profile(userName, name, surname, userId, brthDay);
		this.password=password;
		this.friends= new HashSet<Profile>();
		this.blockedUsr= new HashSet<Profile>();
	}

	/**
	 * @return the name
	 */
	public String getName() {
		return user.getName();
	}

	/**
	 * @return the surname
	 */
	public String getSurname() {
		return this.user.getSurname();
	}

	/**
	 * @return the password
	 */
	public String getPassword() {
		return this.password;
	}

	/**
	 * @return the userName
	 */
	public String getUserName() {
		return this.user.getUsrName();
	}

	/**
	 * @return the age, it compares todays date with the day of birth of the user
	 */
	@SuppressWarnings("deprecation")
	public int getAge() {
		Date dt = new Date();
		Date aux= user.getBirthDay();
		if(dt.getMonth() < aux.getMonth())
			return dt.getYear()-aux.getYear();
		if(dt.getMonth() > aux.getMonth())
			return dt.getYear()-aux.getYear() +1;
		if(dt.getDay() < dt.getDay())
			return dt.getYear() - aux.getYear();
		return dt.getYear() - aux.getYear() +1;
	}
	
	/**
	 * @return a boolean value indicating if the password is correspondent to the usrName
	 */
	public boolean checkPass(String pass)
	{
		if(this.password.equals(pass))
			return true;
		return false;
	}
	
	/**
	 * @param old password used to validate and check permissions to change pass
	 * @param new password
	 * @throws invalidPasswordException
	 */
	public void changePass(String oldpass, String newpass) throws invalidPasswordException
	{
		if(!oldpass.equals(this.password))
			throw new invalidPasswordException("The password is not valid");
		this.password=newpass;
	}

	/**
	 * @param friend that will be added to the users friend list
	 */
	public void addFriend(Profile user)
	{
		friends.add(user);
	}
	
	/**
	 * @param user that will be blocked, if the user exists in the friends list it is removed
	 */
	public void blockUser(Profile user)
	{
		if(friends.contains(user))
			friends.remove(user);

		blockedUsr.add(user);
	}
	
	/**
	 * @param user that it should unblock, unblocks it if it is present, doesnt do anything if it isnt
	 */
	public void unBlockUser(Profile user)
	{
		this.blockedUsr.remove(user);
	}
	
	/**
	 * returns a String with the username
	 */
	public String toString()
	{
		return user.getUsrName();
	}

	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ ((blockedUsr == null) ? 0 : blockedUsr.hashCode());
		result = prime * result + ((friends == null) ? 0 : friends.hashCode());
		result = prime * result
				+ ((password == null) ? 0 : password.hashCode());
		result = prime * result + ((user == null) ? 0 : user.hashCode());
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
		User other = (User) obj;
		if (blockedUsr == null) {
			if (other.blockedUsr != null)
				return false;
		} else if (!blockedUsr.equals(other.blockedUsr))
			return false;
		if (friends == null) {
			if (other.friends != null)
				return false;
		} else if (!friends.equals(other.friends))
			return false;
		if (password == null) {
			if (other.password != null)
				return false;
		} else if (!password.equals(other.password))
			return false;
		if (user == null) {
			if (other.user != null)
				return false;
		} else if (!user.equals(other.user))
			return false;
		return true;
	}
}


