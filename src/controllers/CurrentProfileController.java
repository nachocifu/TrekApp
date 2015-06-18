package controllers;

import java.util.HashMap;

import domain.ControllerNotLoadedException;
import domain.InvalidPasswordException;
import domain.InvalidPermissionException;
import domain.Profile;
import domain.RequestStatus;
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

    /**
     * Deletes a friends from the users friends list
     * @param friend to be removed
     * @throws SessionNotActiveException
     * @throws ControllerNotLoadedException
     */
    public void deleteFriend(ProfileController friend) throws SessionNotActiveException, ControllerNotLoadedException{
        this.validateEnvironment();
        this.validateController(friend);
        this.obj.deleteFriend(friend.getObject());
        saveChanges();
    }
    
    /**
     * Adds a friend request to the profile
     * @param possibleMember
     * @throws SessionNotActiveException
     * @throws ControllerNotLoadedException
     * @throws InvalidPermissionException 
     */
    public void sendMemberRequest(ProfileController possibleFriend) throws SessionNotActiveException, ControllerNotLoadedException, InvalidPermissionException{
    	this.validateEnvironment();
        this.validateController(possibleFriend);
        this.obj.addFriendRequest(possibleFriend.getObject());
        saveChanges();
    }
    

    /**
     * Adds a friend to the users friends list 
     * @param friend username to be added to the list
     * @throws SessionNotActiveException
     * @throws ControllerNotLoadedException
     * @throws InvalidPermissionException 
     */
    public void sendFriendRequest(String possibleFriend) throws SessionNotActiveException, ControllerNotLoadedException, InvalidPermissionException{
        this.validateEnvironment();
        Profile friend = this.getRepository().getById(possibleFriend);
        friend.addFriendRequest(this.obj);
        this.getRepository().update(friend); //Saves Changes into repo
        saveChanges();
    }
    
    /**
     * Adds a friend to the users friends list 
     * @param friend controller to be added to the list
     * @throws SessionNotActiveException
     * @throws ControllerNotLoadedException
     * @throws InvalidPermissionException 
     */
    public void sendFriendRequest(ProfileController possibleFriend) throws SessionNotActiveException, ControllerNotLoadedException, InvalidPermissionException{
        this.validateEnvironment();
        this.validateController(possibleFriend);
        possibleFriend.getObject().addFriendRequest(this.obj);
        possibleFriend.saveChanges();
        saveChanges();
    }

    /**
     * Adds a user to this's blocked users list
     * @param user to be blocked
     * @throws SessionNotActiveException
     * @throws ControllerNotLoadedException
     */
    public void blockUser(ProfileController user) throws SessionNotActiveException, ControllerNotLoadedException{
        this.validateEnvironment();
        this.validateController(user);
        this.obj.blockUser(user.getObject());
        saveChanges();
    }

    /**
     * Removes a user from this's blocked list
     * @param user to be unblocked
     * @throws SessionNotActiveException
     * @throws ControllerNotLoadedException
     */
    public void unBlockUser(ProfileController user) throws SessionNotActiveException, ControllerNotLoadedException{
        this.validateEnvironment();
        this.validateController(user);
        this.obj.unBlockedUsr(user.getObject());
        saveChanges();
    }

    /**
     * @param oldPass used to validate if the user is able to change the password
     * @param newPass to be set if the oldPass is a valid one
     * @throws InvalidPasswordException when the oldPass is not a valid one
     */
    public void changePass(String oldPass, String newPass) throws InvalidPasswordException {
        this.obj.changePass(oldPass, newPass);
        saveChanges();
    }

    /**
     * @param group to be joined by user
     * @throws SessionNotActiveException
     * @throws ControllerNotLoadedException
     */
    public void joinGroup(GroupController group) throws SessionNotActiveException, ControllerNotLoadedException{
        this.validateEnvironment();
        this.validateController(group);
        this.obj.joinGroup(group.getObject());
        saveChanges();
    }

    /**
     * @param group to be left by the user
     * @throws SessionNotActiveException
     * @throws ControllerNotLoadedException
     */
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
    
    /**
     * @return Returns a Map with profile controllers and each of their status
     * @throws SessionNotActiveException
     * @throws ControllerNotLoadedException
     */
    public HashMap<ProfileController, RequestStatus> getFriendRequests() throws SessionNotActiveException, ControllerNotLoadedException{
    	this.validateEnvironment();
    	HashMap<ProfileController, RequestStatus> newMap = new HashMap<>();
        Application app = Application.getInstance();
        HashMap<Profile, RequestStatus> friendRequests = this.obj.getFriendRequests();
        for (Profile profile : friendRequests.keySet()) {
            newMap.put(app.getAProfileController(profile), friendRequests.get(profile));
        }
        return newMap;
    }
}
