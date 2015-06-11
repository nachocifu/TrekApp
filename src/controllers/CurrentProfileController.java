package controllers;

import domain.ControllerNotLoadedException;
import domain.InvalidPasswordException;
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

    public void deleteFriend(ProfileController friend) throws SessionNotActiveException, ControllerNotLoadedException{
        this.validateEnvironment();
        this.validateController(friend);
        this.obj.deleteFriend(friend.getObject());
    }

    public void addFriend(ProfileController friend) throws SessionNotActiveException, ControllerNotLoadedException{
        this.validateEnvironment();
        this.validateController(friend);
        this.obj.addFriend(friend.getObject());
    }

    public void blockUser(ProfileController user) throws SessionNotActiveException, ControllerNotLoadedException{
        this.validateEnvironment();
        this.validateController(user);
        this.obj.blockUser(user.getObject());
    }

    public void unBlockUser(ProfileController user) throws SessionNotActiveException, ControllerNotLoadedException{
        this.validateEnvironment();
        this.validateController(user);
        this.obj.unBlockedUsr(user.getObject());
    }

    public void changePass(String oldPass, String newPass) throws InvalidPasswordException {
        this.obj.changePass(oldPass, newPass);
    }

    public void joinGroup(GroupController group) throws SessionNotActiveException, ControllerNotLoadedException{
        this.validateEnvironment();
        this.validateController(group);
        this.obj.joinGroup(group.getObject());
    }

    public void leaveGroup(GroupController group) throws SessionNotActiveException, ControllerNotLoadedException{
        this.validateEnvironment();
        this.validateController(group);
        this.obj.leaveGroup(group.getObject());
    }

    public void joinTrip(TripController trip) throws SessionNotActiveException, ControllerNotLoadedException{
        this.validateEnvironment();
        this.validateController(trip);
        this.obj.joinTrip(trip.getObject());
    }

    public void leaveTrip(TripController trip) throws SessionNotActiveException, ControllerNotLoadedException{
        this.validateEnvironment();
        this.validateController(trip);
        this.obj.leaveTrip(trip.getObject());
    }
}
