package services;

import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;

import repository.AbstractRepository;
import domain.Coordenates;
import domain.Profile;

public class ProfileService{
	
	private AbstractRepository<Profile> repo;
	
	//preguntar
	private HashMap<String, HashSet<String>> blcked;
	
	/**
	 * @param user repository
	 */
	public ProfileService(AbstractRepository<Profile> repo){
		this.repo=repo;
		this.blcked=new HashMap<String, HashSet<String>>();
	}
	
/**
 	* @return HashMap<String, Object>, The key will be a string representing what the value actually is, and the value will be an Object, the HashMap will contain
 * all the characteristics relevant to a users profile	
 */
	public HashMap<String, Object> getById(String usrId){
		HashMap<String, Object> profileHash=  new HashMap<String, Object>();
		Profile profile= this.repo.getById(usrId);
		profileHash.put("username", profile.getUsrName());
		profileHash.put("name", profile.getName());
		profileHash.put("surname", profile.getSurname());
		profileHash.put("sex", profile.getSex());
		profileHash.put("birthday", profile.getBirthDay());
		profileHash.put("rating", profile.getRating());
		profileHash.put("friends", profile.getFriends());
		//profileHash.put("blockedusers", profile.getBlockedUsrs());
		profileHash.put("groups", profile.getGroups());
		profileHash.put("trips", profile.getTrips());
		profileHash.put("reviews", profile.getReviews());
		profileHash.put("age", this.getAge(usrId));
		
		return profileHash;
	}
	
	/**
	 * Add a new user to the database
	 * @param usrName
	 * @param name
	 * @param surname
	 * @param brthDay, the users age should be between 18 and 85
	 * @param sex
	 * @param pass, should have at least 5 letters/digits
	 * @throws IllegalArgumentException when the password of birthday are not valid
	 */
	@SuppressWarnings("deprecation")
	public boolean newUser(String usrName, String name, String surname, Date brthDay, boolean sex, String pass, String city, String email)throws IllegalArgumentException{
		if(brthDay.getYear() < 1930 || brthDay.getYear() > 1997)
			throw new IllegalArgumentException("Year is not valid, insert a valid year");
		if(pass.length() <5)
			throw new IllegalArgumentException("The password must contain at least 5 letters/digits");
		
		Profile user= new Profile(usrName, name, surname, brthDay, sex, pass, city, email);
		return repo.add(user);
			
		
	}
	
	/**
	 * @param usrId of the user to be deleted from the database
	 */
	public void delete(String usrId){
		repo.remove(usrId);
	}
	
	/**
	 * @param usrIdUser1 user1 will be adding user2 to its friends list
	 * @param usrIdUser2 user2 will be adding user1 to its friends list
	 */
	public void addFriend(String usrIdUser1, String usrIdUser2){
		Profile user1= repo.getById(usrIdUser1);
		Profile user2= repo.getById(usrIdUser2);
		user1.addFriend(usrIdUser2);
		user2.addFriend(usrIdUser1);
		this.repo.update(user1);
		this.repo.update(user2);
	}

	/**
	 * @param usrIdUser1 deleted from User2's friends
	 * @param usrIdUser2 deleted from User1's friends
	 */
	public void deleteFriend(String usrIdUser1, String usrIdUser2){
		Profile user1=repo.getById(usrIdUser1);
		Profile user2=repo.getById(usrIdUser2);
		user1.deleteFriend(usrIdUser2);
		user2.deleteFriend(usrIdUser1);
		this.repo.update(user1);
		this.repo.update(user2);
	}
	
	/**ver si tengo que agregar una variable en profile que contenga quien lo bloqueo a ese usuario asi al a hora de mostrar los usuarios sabe cuales y no mostrar
	 * @param usrId that will be blocking blckdUser
	 * @param blckdUsr will be removed from usrId's friend list
	 */
	public void blockUser(String usrId, String blckdUsr){
		Profile user=repo.getById(usrId);
		user.deleteFriend(blckdUsr);
		user.addBlockedUsr(blckdUsr);
		Profile blcked=repo.getById(blckdUsr);
		blcked.deleteFriend(usrId);
		repo.update(user);
		repo.update(blcked);
		
	}
	
	/**
	 * @param usrId will unblock blckdUsr if it is blocked
	 * @param blckdUsr
	 */
	public void unBlockUser(String usrId, String blckdUsr){
		Profile user=repo.getById(usrId);
		user.deleteBlockedUsr(blckdUsr);
		this.repo.update(user);
	}
	
	//si tengo que agregar un trip tengo que ahcer una funcion en el TripService qeu agregue
	//al user al trip y otra aca que lo agregue al trip al user o puedo acceder a la repo
	//de tripService a traves de ProfileService?
	/**
	 * @param usrId 
	 * @param tripId
	 */
	public void addTrip(String usrId, String tripId){
		Profile user=repo.getById(usrId);
		user.addTrip(tripId);
		repo.update(user);
	}
	
	/**
	 * @param usrId
	 * @param groupId
	 */
	public void addGroup(String usrId, String groupId){
		Profile user=repo.getById(usrId);
		user.addGroup(groupId);
		this.repo.update(user);
	}
	
	/**
	 * @param usrId user that will change its password
	 * @param oldPass used to validate
	 * @param newPass that will be established for the user
	 * @return
	 * @throws InvalidPasswordException if the oldpass is not valid
	 */
	public boolean changePass(String usrId, String oldPass, String newPass) throws InvalidPasswordException{
		Profile user=repo.getById(usrId);
		if(!user.getPassword().equals(oldPass))
			throw new InvalidPasswordException("Password is not valid");
		user.setPassword(newPass);
		repo.update(user);
		return true;
	}
	
	/**
	 * @param searchTxt that will be used to search the database, it can be part of the username, name, surname, email, city, etc
	 * @return a limited view of the profiles that validate the search text
	 */
	public HashSet<HashMap<String, Object>> searchBy(String searchTxt){
		HashSet<HashMap<String,Object>> payload= new HashSet<HashMap<String, Object>>();
		
		for(Profile user: repo.searchBy(searchTxt)){
			HashMap<String, Object> userHash= new HashMap<String, Object>();
			userHash.put("username", user.getName());
			userHash.put("name", user.getName());
			userHash.put("surname", user.getSurname());
			userHash.put("city", user.getCity());
			payload.add(userHash);
		}
		return payload;
	}

	/**
	 * @param usrId
	 * @param pass
	 * @return true when the login is succesfull
	 * @throws InvalidPasswordException when the password does not coincide with the users password
	 */
	public boolean logIn(String usrId, String pass) throws InvalidPasswordException{
		Profile user=repo.getById(usrId);
		if(!user.getPassword().equals(pass))
			throw new InvalidPasswordException("Password or username not valid");
		return true;
	}

	/**
	 * revisar 
	 * @return the age, it compares todays date with the day of birth of the user
	 */
	@SuppressWarnings("deprecation")
	public int getAge(String usrId) {
		Profile user= repo.getById(usrId);
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
	
	public void checkIn(String usrId, Double x, Double y){
		Coordenates coor=new Coordenates(x,y);
		Profile user=repo.getById(usrId);
		user.setCoordeantes(coor);
		repo.update(user);
	}

}
