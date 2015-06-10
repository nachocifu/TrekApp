package controllers;

import java.util.Collection;
import java.util.Date;
import java.util.HashSet;

import repository.AbstractRepository;
import repository.UserRepository;
import domain.ControllerNotLoadedException;
import domain.Profile;
import domain.Review;
import domain.Session;
import domain.SessionNotActiveException;
import domainUI_Controller.GroupUI;
import domainUI_Controller.PastTripUI;

public class ProfileController extends AbstractController<Profile> {

    /* The profile to be controlled*/
    private Profile profile;

    /*The profile repository to request the profile*/
    private UserRepository profileRepo;

    public ProfileController(UserRepository repo) {
        this.profileRepo =  repo;
    }

    /**
     * Sets the profile to be controlled by this controller.
     * @param username The username of the user
     * @return success If load was successful
     * @throws SessionNotActiveException
     */
    public Boolean load(String username) throws SessionNotActiveException{
        if(!Session.getInstance().isActive())
            throw new SessionNotActiveException("ERROR || You must log in before operating.");

        return this.setProfile( username);
    }

    /**
     * Generates the request to server and loads response.
     * @param username The username of the user
     * @return boolean To signal success
     */
    protected Boolean setProfile(String username){
        Profile response = this.profileRepo.getById(username);

        if(response != null){
            profile = response;
            return true;
        }
        else
            return false;
    }

    /**
     * Check that
     * @throws SessionNotActiveException
     * @throws ControllerNotLoadedException
     */
    private void validateEnvironment() throws SessionNotActiveException, ControllerNotLoadedException{
        if(!Session.getInstance().isActive())
            throw new SessionNotActiveException("ERROR || You must log in before operating.");
        if( this.profile == null )
            throw new ControllerNotLoadedException("ERROR || Cannot operate on unloaded controller.");
    }


    /* #### TODOS LOS METODOS EN ESTE SON GETTERS,,,NO HAY SETTERS#######*/
    /* TODOS los metodos, al principio lo primero que hacen es validar que la session este abierta.*/
    public String getUsername(){

        return this.profile.getUsrName();
    }

    public String getUserName() throws SessionNotActiveException{
        if(!Session.getInstance().isActive())
            throw new SessionNotActiveException("ERROR || You must log in before operating.");
        return this.profile.getName();
    }

    public String getSurname() throws SessionNotActiveException{
        if(!Session.getInstance().isActive())
            throw new SessionNotActiveException("ERROR || You must log in before operating.");
        return this.profile.getSurname();
    }

    public Boolean getSex() throws SessionNotActiveException{
        if(!Session.getInstance().isActive())
            throw new SessionNotActiveException("ERROR || You must log in before operating.");
        return this.profile.getSex();
    }

    public Date getBirthday() throws SessionNotActiveException{
        if(!Session.getInstance().isActive())
            throw new SessionNotActiveException("ERROR || You must log in before operating.");
        return this.profile.getBirthDay();
    }

    public Double getRating() throws SessionNotActiveException{
        if(!Session.getInstance().isActive())
            throw new SessionNotActiveException("ERROR || You must log in before operating.");
        return this.profile.getRating();
    }

    public String getCity() throws SessionNotActiveException{
        if(!Session.getInstance().isActive())
            throw new SessionNotActiveException("ERROR || You must log in before operating.");
        return this.getCity();
    }

    public String getMail() throws SessionNotActiveException{
        if(!Session.getInstance().isActive())
            throw new SessionNotActiveException("ERROR || You must log in before operating.");
        return this.profile.getEmail();
    }

    public HashSet<ProfileController> getFriends() throws SessionNotActiveException{
        if(!Session.getInstance().isActive())
            throw new SessionNotActiveException("ERROR || You must log in before operating.");

        HashSet<ProfileController> response = new HashSet<ProfileController>();
        Application app = Application.getInstance();
        ProfileController controller;
        for(Profile each: profile.getFriends()){
            if(each.getUsrName().equals(Session.getInstance().getUserName()))
                controller = app.getCurrentProfileController();
            else
                controller = app.getProfileController();

            controller.load(each.getUsrName());
            response.add(controller);
        }
        return	response;
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
        return this.profile.getReviews();
    }


}
