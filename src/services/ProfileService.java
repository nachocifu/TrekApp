package services;

import java.util.Collection;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;

import repository.AbstractRepository;
import domain.Profile;

public class ProfileService implements ServiceInterface<Profile>{
	
	private AbstractRepository<Profile> repo;
	
	public ProfileService(AbstractRepository<Profile> repo){
		this.repo=repo;
	}
	
	public HashMap<String, Object> getById(Integer usrId){
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
	
	/**validar todooo*/
	public void newUser(String usrName, String name, String surname, Date brthDay, boolean sex, String pass){
		Profile user= new Profile(usrName, name, surname, brthDay, sex, pass);
		repo.add(user);
	}
	
	/**preguntar!!!!*/
	public void delete(Integer usrId){
		repo.remove(usrId);
	}
	
	public void addFriend(Integer usrIdUser, Integer usrIdFriend){
		Profile user= repo.getById(usrIdUser);
		user.addFriend(usrIdFriend);
		repo.update(user);
	}
	
	public boolean changePass(Integer usrId, String oldPass, String newPass) throws InvalidPasswordException{
		Profile user=repo.getById(usrId);
		if(!user.getPassword().equals(oldPass))
			throw new InvalidPasswordException("Password is not valid");
		user.setPassword(newPass);
		repo.update(user);
		return true;
	}
	
	/**agregar ubicacion en el perfil ese del hashmap y age*/
	public HashSet<HashMap<String, Object>> searchBy(String searchTxt){
		HashSet<HashMap<String,Object>> payload= new HashSet<HashMap<String, Object>>();
		
		for(Profile user: repo.searchBy(searchTxt)){
			HashMap<String, Object> userHash= new HashMap<String, Object>();
			userHash.put("username", user.getName());
			userHash.put("name", user.getName());
			userHash.put("surname", user.getSurname());
			payload.add(userHash);
		}
		return payload;
	}

	public boolean logIn(Integer usrId, String pass) throws InvalidPasswordException{
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
	public int getAge(Integer usrId) {
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
	

}
