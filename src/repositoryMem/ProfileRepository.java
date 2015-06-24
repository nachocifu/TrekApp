package repositoryMem;

import domain.Profile;
import domain.UserNameDoesNotExist;

public class ProfileRepository extends AbstractRepository<Profile> {


    public ProfileRepository(String pathToDataBase,Class reposClass) {
       
    }

    /**
     * Query if username and password match to any users.
     * NOTE: count(*) is not used do to limited knowledge on OrmLite
     * @param userName
     * @param passWord
     * @return boolean If username and password exist and match.
     */
    public boolean validateCredentials(String username, String password) {
    	for (Profile profile : this.repository) {
    		if(profile.getUsrName().trim().equals(username.trim()) && profile.comparePass(password.trim())){
    			return true;
    		}		
		}
    	return false;
    }

    /**
     * Query for profile by Username
     * @param userName The username of the profile
     * @return response The Profile or null if no results
     * @throws UserNameDoesNotExist 
     */
    public Profile getById(String username){	
    	for (Profile profile : this.repository) {
    		if(profile.getUsrName().trim().equals(username.trim())){
    			return profile;
    		}
    	}
    	return null;
    }
}
