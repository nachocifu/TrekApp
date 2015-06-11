package controllers;

import java.util.Collection;
import java.util.HashSet;

import repository.AbstractRepository;
import repository.GroupRepository;
import repository.TripRepository;
import repository.UserRepository;
import domain.ControllerNotLoadedException;
import domain.Session;
import domain.SessionNotActiveException;

public abstract class AbstractController<T> {

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
     * Get the actualRepository
     * @return
     */
    protected abstract AbstractRepository<T> getRepository();

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

    protected void validateController(AbstractController<?> controller){
        if(controller == null)
            throw new IllegalArgumentException("ERROR || Illegal parameter.");
        if(controller.getObject() == null)
            throw new IllegalArgumentException("ERROR || Cannot operate with not loaded controller.");
    }

    /**
     * Sets the Object to be controlled by this controller.
     * Only available for other Controllers
     * @param obj the object to be controlled
     * @return success If load was successful
     * @throws SessionNotActiveException
     */
    protected void load(T obj) throws SessionNotActiveException{
        if(!Session.getInstance().isActive())
            throw new SessionNotActiveException("ERROR || You must log in before operating.");
        if(obj == null)
            throw new IllegalArgumentException("ERROR || Illegal operation");
        this.obj = obj;

    }

    protected T getObject(){
        return this.obj;
    }

    protected void saveChanges(){
        this.repo.update(algo)
    }
}