package src.domainUI_Controller;

import java.util.Date;

import src.services.GroupService;
import src.services.TripService;
import src.domain.InvalidPasswordException;
import src.domain.Profile;
import src.services.ProfileService;

public class UIController {
	
	private GroupService groupService;
	private ProfileService profileService;
	private TripService tripService;	
	private Profile loggedUser; //Cambiar a ProfileUI
	
	public UIController(){
		this.groupService = new GroupService();
		this.profileService = new ProfileService();
		this.tripService = new TripService();
		//Poner repos
	}
	
	public boolean createUser(String usrName, String name, String surname, Date brthDay, boolean sex, String pass, String city, String email){
		return this.profileService.newUser(usrName, name, surname, brthDay, sex, pass, city, email);
	}
	
	public void deleteMyUser(){
		this.profileService.delete(this.loggedUser);
	}
	
	public ProfileUI getMyProfileUI(){
		return this.profileService.getProfileUI(this.loggedUser);
	}
	
	/*
	public ProfileUI getOtherProfileUI(){
		return this.profileService.getProfileUI(profile)
	}*/
	
	public Boolean logIn(String usrName, String password) throws InvalidPasswordException{
		if(this.profileService.logIn(usrName, password)){
			this.loggedUser = profileService.getLoggedProfile(usrName);
			return true;
		}
		return false;
	}
	
	//Agregar Metodos de UI Profile
	
	
	
	
}
