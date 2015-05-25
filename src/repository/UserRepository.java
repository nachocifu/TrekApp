package repository;


import domain.Profile;
import domain.User;

public class UserRepository extends AbstractRepository<User> {

	/** the class of the objects this repository handles */
	private final Class<Profile> profileClass = null;
	
	public UserRepository(String pathToDataBase,Class reposClass) {
		super(pathToDataBase, reposClass);
		// TODO Auto-generated constructor stub
	}
}
