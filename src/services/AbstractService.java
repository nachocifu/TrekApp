package services;

import repository.AbstractRepository;

/**
 * To be extended by all services.
 * Operates between the api and the repositorys.
 * Does the validations and logic
 * @author nacho
 *
 */
public abstract class AbstractService<T> {

	private AbstractRepository<T> repo;
	
	public AbstractService(AbstractRepository<T> repo)
	{
		this.repo=repo;
	}
}
