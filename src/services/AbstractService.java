package services;

/**
 * To be extended by all services.
 * Operates between the api and the repositorys.
 * Does the validations and logic
 * @author nacho
 *
 */
public abstract class AbstractService<T> {
	
	public abstract T getById(Integer whoIs, Integer id);
	
	public abstract T save(Integer whoIs, T obj);
	
	
	
}
