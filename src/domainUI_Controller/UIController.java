package domainUI_Controller;

import java.util.Date;

import controllers.GroupService;
import controllers.ProfileService;
import controllers.TripService;
import domain.InvalidPasswordException;
import domain.Profile;
@Deprecated
public class UIController {

    private GroupService groupService;
    private ProfileService profileService;
    private TripService tripService;
    private ProfileUI loggedUser; //Cambiar a ProfileUI
@Deprecated
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
        return this.loggedUser;
    }

    /*
    public ProfileUI getOtherProfileUI(){
        return this.profileService.getProfileUI(profile)
    }*/

    public Boolean logIn(String usrName, String password) throws InvalidPasswordException{
        if(this.profileService.logIn(usrName, password)){
            this.loggedUser = profileService.getLoggedProfileUI(usrName);
            return true;
        }
        return false;
    }
}
