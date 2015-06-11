package controllers;

import domain.Session;
import domain.SessionNotActiveException;
import repository.GroupRepository;
import repository.TripRepository;
import repository.UserRepository;

public class CurrentProfileController extends ProfileController {

    public CurrentProfileController(UserRepository profileRepo,
            TripRepository tripRepo, GroupRepository groupRepo) {
        super(profileRepo, tripRepo, groupRepo);
    }


    /**
     * Sets the profile of the current user
     * @throws SessionNotActiveException
     * @throws IllegalArgumentException
     */
    public Boolean load() throws SessionNotActiveException{
        return this.load(Session.getInstance().getUserName());
    }

    /**
     * Sets the profile of the username.
     * If not the logged user throws exception.
     * @param username The username to load
     * @throws SessionNotActiveException
     * @throws IllegalArgumentException
     */
    public Boolean load(String username) throws SessionNotActiveException{
        if(!Session.getInstance().isActive())
            throw new SessionNotActiveException("ERROR || You must log in before operating.");
        if(!Session.getInstance().getUserName().equals(username))
            throw new IllegalArgumentException("ERROR || You can only load your own profile.");

        return this.setObj( username);
    }

    /**
     * Sets the Profile to be controlled by this controller.
     * Because Every session has only one current profile, desregard ID.
     * @param objId the profile id
     * @return success If load was successful
     * @throws SessionNotActiveException
     */
    public Boolean load(Integer objId) throws SessionNotActiveException{
        return this.load();
    }
}
