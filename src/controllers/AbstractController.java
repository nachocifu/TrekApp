package controllers;

import repositorySQL.AbstractRepository;
import domain.ControllerNotLoadedException;
import domain.SessionNotActiveException;

public abstract class AbstractController<T> {

    /* The object to be controlled*/
    protected T obj;

    /*The repositories to be accessed by the controller*/
    protected AbstractRepository<T> repository;


    public AbstractController(AbstractRepository<T> repo){
        if(repo == null)
            throw new IllegalArgumentException();
        repository = repo;
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

    /**
     * @param controller that is validated
     * @throws IllegalArgumentException when the controller is null
     * @throws IllegalArgumentException when the controller is not loaded
     */
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

    /**
     * 
     * @return object
     */
    protected T getObject(){
        return this.obj;
    }

    /**
     * update the repository
     */
    protected void saveChanges(){
        this.repository.update(this.obj);
    }
}