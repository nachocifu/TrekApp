package repositoryMem;

import java.sql.Date;

import domain.Profile;
import domain.UserNameDoesNotExist;

public class ProfileRepository extends AbstractRepository<Profile> {


    @SuppressWarnings("deprecation")
    public ProfileRepository() {
        Profile p = new Profile("naty2", "Ignacio", "Cifu", new Date(7, 5, 1994), true, "agua", "Baires", "naty.navas2@gmail.com");
        add(new Profile("naty", "Ignacio", "Cifu", new Date(7, 5, 1994), true, "agua", "Baires", "naty.navas2@gmail.com"));
        add(p);
        add(new Profile("naty3", "Ignacio", "Cifu", new Date(7, 5, 1994), true, "agua", "Baires", "naty.navas2@gmail.com"));
        add(new Profile("naty5", "Ignacio", "Cifu", new Date(7, 5, 1994), true, "agua", "Baires", "naty.navas2@gmail.com"));
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
