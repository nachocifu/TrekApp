package repository;

import java.util.HashSet;

import domain.UserNameAlreadyExistsException;

/**
 *
 * To be extended by all repositorys.
 * Operates between the data base and the services.
 * Provides file access to the services logic.
 * @author nacho
 *
 */
public abstract class AbstractRepository<T>{
	/**
	 * Repository
	 */
	protected HashSet<T> repository;
	
    public AbstractRepository(){
    	this.repository = new HashSet<T>();
    }

    /**
     * Adds the object. Returns if it succeeds.
     * @param obj to save on system
     * @return status if success is true or false
     * @throws UserNameAlreadyExistsException
     */
    public boolean add(T obj){
        if(!this.repository.contains(obj)){
        	this.repository.add(obj);
        	return true;
        }
        return false;
    }
    
    /**
     * Removes the object referenced by id.
     * If it doesn't exist does nothing.
     * @param id
     */
    public boolean delete(T obj){
    	if(!this.repository.contains(obj)){
        	return false;
        }
    	this.repository.remove(obj);
        return true;
    }
    
    public void update(T obj){
    	if(this.repository.contains(obj)){
    		this.repository.add(obj);
    	}
    }
}
