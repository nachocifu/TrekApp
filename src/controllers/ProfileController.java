package controllers;

import repository.AbstractRepository;
import repository.UserRepository;
import domain.Profile;
import domain.Session;
import domain.SessionNotActiveException;

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

    /* #### TODOS LOS METODOS EN ESTE SON GETTERS,,,NO HAY SETTERS#######*/
    /* TODOS los metodos, al principio lo primero que hacen es validar que la session este abierta.*/


}
