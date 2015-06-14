package controllers;

import domain.ControllerNotLoadedException;
import domain.InvalidPasswordException;
import domain.SessionNotActiveException;
import repository.ProfileRepository;

public class CurrentProfileController extends ProfileController {

    public CurrentProfileController(ProfileRepository profileRepo) {
        super(profileRepo);
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
        saveChanges();
    }

    public void addFriend(ProfileController friend) throws SessionNotActiveException, ControllerNotLoadedException{
        this.validateEnvironment();
        this.validateController(friend);
        this.obj.addFriend(friend.getObject());
        saveChanges();
    }

    public void blockUser(ProfileController user) throws SessionNotActiveException, ControllerNotLoadedException{
        this.validateEnvironment();
        this.validateController(user);
        this.obj.blockUser(user.getObject());
        saveChanges();
    }

    public void unBlockUser(ProfileController user) throws SessionNotActiveException, ControllerNotLoadedException{
        this.validateEnvironment();
        this.validateController(user);
        this.obj.unBlockedUsr(user.getObject());
        saveChanges();
    }

    public void changePass(String oldPass, String newPass) throws InvalidPasswordException {
        this.obj.changePass(oldPass, newPass);
        saveChanges();
    }

    public void joinGroup(GroupController group) throws SessionNotActiveException, ControllerNotLoadedException{
        this.validateEnvironment();
        this.validateController(group);
        this.obj.joinGroup(group.getObject());
        saveChanges();
    }

    public void leaveGroup(GroupController group) throws SessionNotActiveException, ControllerNotLoadedException{
        this.validateEnvironment();
        this.validateController(group);
        this.obj.leaveGroup(group.getObject());
        saveChanges();
    }
    
    /**
     * Accepts a friend from the friend request list
     * @param newMember
     * @throws SessionNotActiveException
     * @throws ControllerNotLoadedException
     */
    public void acceptFriend(ProfileController newFriend) throws SessionNotActiveException, ControllerNotLoadedException{
        this.validateEnvironment();
        this.validateController(newFriend);
        this.obj.acceptFriend(newFriend.getObject());
        saveChanges();
        newFriend.saveChanges();
    }
    
    /**
     * Rejects a friend request
     * @param profileController
     * @throws SessionNotActiveException
     * @throws ControllerNotLoadedException
     */
    public void rejectAFriendRequest(ProfileController profileRejected) throws SessionNotActiveException, ControllerNotLoadedException{
    	this.validateEnvironment();
    	this.validateController(profileRejected);
    	this.obj.rejectAFriendRequest(profileRejected.getObject());
    	saveChanges();  	
    }
}
