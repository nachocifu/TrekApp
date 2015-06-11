package controllers;

import java.util.Collection;
import java.util.Date;
import java.util.HashSet;

import repository.AbstractRepository;
import repository.GroupRepository;
import repository.TripRepository;
import repository.UserRepository;
import domain.ControllerNotLoadedException;
import domain.Profile;
import domain.Review;
import domain.Session;
import domain.SessionNotActiveException;
import domainUI_Controller.GroupUI;
import domainUI_Controller.PastTripUI;

public class ProfileController extends AbstractController<Profile> {

    public ProfileController(UserRepository profileRepo,
            TripRepository tripRepo, GroupRepository groupRepo) {
        super(profileRepo, tripRepo, groupRepo);
    }

    /**
     * Sets the Profile to be controlled by this controller.
     * @param profile the object id
     * @return success If load was successful
     * @throws SessionNotActiveException
     */
    public Boolean load(String username) throws SessionNotActiveException{
        if(!Session.getInstance().isActive())
            throw new SessionNotActiveException("ERROR || You must log in before operating.");

        return this.setObj( username );
    }

    protected Boolean setObj(Integer objId) throws SessionNotActiveException{
        Profile response = this.profileRepo.getById(objId);

        if(response != null){
            obj = response;
            return true;
        }
        else
            return false;
    }

    /**
     * Generates the request to server and loads response.
     * @param username The username of the user
     * @return boolean To signal success
     */
    protected Boolean setObj(String username){
        Profile response = this.profileRepo.getById(username);

        if(response != null){
            obj = response;
            return true;
        }
        else
            return false;
    }

    /* #### TODOS LOS METODOS EN ESTE SON GETTERS,,,NO HAY SETTERS#######*/
    /* TODOS los metodos, al principio lo primero que hacen es validar que la session este abierta.*/
    public String getUsername() throws SessionNotActiveException, ControllerNotLoadedException{
        this.validateEnvironment();
        return this.obj.getUsrName();
    }

    public String getUserName() throws SessionNotActiveException, ControllerNotLoadedException{
        this.validateEnvironment();
        return this.obj.getName();
    }

    public String getSurname() throws SessionNotActiveException, ControllerNotLoadedException{
        this.validateEnvironment();
        return this.obj.getSurname();
    }

    public Boolean getSex() throws SessionNotActiveException, ControllerNotLoadedException{
        this.validateEnvironment();
        return this.obj.getSex();
    }

    public Date getBirthday() throws SessionNotActiveException, ControllerNotLoadedException{
        this.validateEnvironment();
        return this.obj.getBirthDay();
    }

    public Double getRating() throws SessionNotActiveException, ControllerNotLoadedException{
        this.validateEnvironment();
        return this.obj.getRating();
    }

    public String getCity() throws SessionNotActiveException, ControllerNotLoadedException{
        this.validateEnvironment();
        return this.getCity();
    }

    public String getMail() throws SessionNotActiveException, ControllerNotLoadedException{
        this.validateEnvironment();
        return this.obj.getEmail();
    }

    public HashSet<ProfileController> getFriends() throws SessionNotActiveException, ControllerNotLoadedException{
        this.validateEnvironment();
        return this.generateListOfProfileControllers(obj.getFriends());
    }

    public Collection<GroupUI> getGroups(){
        if(!Session.getInstance().isActive())
            throw new SessionNotActiveException("ERROR || You must log in before operating.");
        return this.groupService.getProfileGroupsUI(profile);
    }

    public Collection<PastTripUI> getPastTripsUI(){
        if(!Session.getInstance().isActive())
            throw new SessionNotActiveException("ERROR || You must log in before operating.");
        return this.tripService.getProfilePastTripsUI(profile);
    }

    public Collection<Review> getReviews() throws SessionNotActiveException{
        if(!Session.getInstance().isActive())
            throw new SessionNotActiveException("ERROR || You must log in before operating.");
        return this.obj.getReviews();
    }


}
