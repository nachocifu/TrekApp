package controllers;

import java.util.Collection;
import java.util.HashSet;

import domain.ControllerNotLoadedException;
import domain.Profile;
import domain.Session;
import domain.SessionNotActiveException;
import repository.AbstractRepository;
import repository.GroupRepository;
import repository.TripRepository;
import repository.UserRepository;

public class AbstractController<T> {

    /* The object to be controlled*/
    protected T obj;

    /*The repositories to be accessed by any controller*/
    protected UserRepository profileRepo;
    protected TripRepository tripRepo;
    protected GroupRepository groupRepo;


    public AbstractController(UserRepository profileRepo, TripRepository tripRepo, GroupRepository groupRepo){
        if(profileRepo == null || tripRepo == null || groupRepo == null)
            throw new IllegalArgumentException();

        this.profileRepo = profileRepo;
        this.tripRepo = tripRepo;
        this.groupRepo = groupRepo;
    }

    /**
     * Check for environment irregularities.
     *
     * @throws SessionNotActiveException
     * @throws ControllerNotLoadedException
     */
    protected void validateEnvironment() throws SessionNotActiveException, ControllerNotLoadedException{
        if(!Session.getInstance().isActive())
            throw new SessionNotActiveException("ERROR || You must log in before operating.");
        if( this.obj == null )
            throw new ControllerNotLoadedException("ERROR || Cannot operate on unloaded controller.");
    }

    protected HashSet<ProfileController> generateListOfProfileControllers(Collection<Profile> profiles) throws SessionNotActiveException{
        HashSet<ProfileController> response = new  HashSet<ProfileController>();
        Application app = Application.getInstance();
        ProfileController controller;

        for(Profile each: profiles){
            if(each.getUsrName().equals(Session.getInstance().getUserName()))
                controller = app.getCurrentProfileController();
            else
                controller = app.getProfileController();

            controller.load(each.getUsrName());
            response.add(controller);
        }

        return response;
    }


}