package controllers;

import repository.AbstractRepository;
import domain.ControllerNotLoadedException;
import domain.SessionNotActiveException;


public class CollectionController{
	
	protected ProfileRepository profileRepos;
	protected GroupRepository repository;
	protected TripRepository repository;


    public AbstractController(AbstractRepository<T> repo){
        if(repo == null)
            throw new IllegalArgumentException();
        repository = repo;
    }
	
	protected void validateEnvironment() throws SessionNotActiveException, ControllerNotLoadedException{
        if(!Session.getInstance().isActive())
            throw new SessionNotActiveException("ERROR || You must log in before operating.");
	}

}
