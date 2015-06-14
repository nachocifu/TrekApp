package controllers;

import java.util.HashMap;

import domain.ControllerNotLoadedException;
import domain.InvalidPermissionException;
import domain.Profile;
import domain.RequestStatus;
import domain.SessionNotActiveException;
import repository.GroupRepository;
import domain.Message;

public class MyGroupController extends GroupController {

    public MyGroupController(GroupRepository groupRepo) {
        super(groupRepo);
        // TODO Auto-generated constructor stub
    }

    /**
     * Ads a particular member
     * @param profileController
     * @throws SessionNotActiveException
     * @throws ControllerNotLoadedException
     */
    public void addMember(ProfileController profileController) throws SessionNotActiveException, ControllerNotLoadedException{
        this.validateEnvironment();
        this.validateController(profileController);
        Profile member = profileController.getObject();
        if(!member.equals(this.obj.getAdminUser())){
            this.obj.addMember(member);
            saveChanges();
        }
    }

    /**
     * Deletes a member and if that member is yourself then a new admin is set
     * @param profileController
     * @throws SessionNotActiveException
     * @throws ControllerNotLoadedException
     */
    public void deleteMember(ProfileController profileController) throws SessionNotActiveException, ControllerNotLoadedException{
        this.validateEnvironment();
        this.validateController(profileController);
        if(this.obj.deleteMember(profileController.getObject()))
        	getRepository().delete(this); // Crear delete para group con id integer
        profileController.saveChanges();
        saveChanges();
    }
    
    /**
     * Returns a HashMap so that the front can list it and its values (REJECTED if he has been rejected and WAITING if is waiting for acceptance)
     * @return
     * @throws SessionNotActiveException
     * @throws ControllerNotLoadedException
     */
    public HashMap<ProfileController, RequestStatus> getMemberRequests() throws SessionNotActiveException, ControllerNotLoadedException{
    	this.validateEnvironment();
    	HashMap<ProfileController, RequestStatus> newMap = new HashMap<>();
    	Application app = Application.getInstance();
    	for (Profile profile : this.obj.getMemberRequests().keySet()) {
			newMap.put(app.getAProfileController(profile), this.obj.getMemberRequests().get(profile));
		}
    	return newMap;
    }
    
    /**
     * Accepts a member from the requestMember list
     * @param newMember
     * @throws SessionNotActiveException
     * @throws ControllerNotLoadedException
     */
    public void acceptMember(ProfileController newMember) throws SessionNotActiveException, ControllerNotLoadedException{
    	this.validateEnvironment();
    	this.validateController(newMember);
    	this.obj.acceptMember(newMember.getObject());
    	saveChanges();
    }

    /**
     * Deletes a trip from the group
     * @param tripController
     * @throws SessionNotActiveException
     * @throws ControllerNotLoadedException
     */
    public void deleteGroupTrip(TripController tripController) throws SessionNotActiveException, ControllerNotLoadedException{
        this.validateEnvironment();
        this.validateController(tripController);
        this.obj.deleteGroupTrip(tripController.getObject());
        saveChanges();
    }
    
    /**
     * Sets filters to filter member requests
     * @param edad
     * @param ciudad
     * @throws SessionNotActiveException
     * @throws ControllerNotLoadedException
     */
    public void setFilters(Integer age, String city) throws SessionNotActiveException, ControllerNotLoadedException{
    	this.validateEnvironment();
    	this.obj.setFilterAge(age);
    	this.obj.setFilterCity(city);
    	saveChanges();
    }
    
    /**
     * Deletes the group from all of its members
     * @throws SessionNotActiveException
     * @throws ControllerNotLoadedException
     */
    public void deleteGroup() throws SessionNotActiveException, ControllerNotLoadedException{
    	this.validateEnvironment();
    	for (Profile member : this.obj.getMembers()) {
			member.leaveGroup(this.obj);
		}
    	getRepository().delete(this); // Crear delete para group con id integer  	
    }
    
    /**
     * Deletes a post from the group
     * @param msg
     * @throws SessionNotActiveException
     * @throws ControllerNotLoadedException
     * @throws IllegalArgumentException
     * @throws InvalidPermissionException
     */
    public void deletePost(Message msg) throws SessionNotActiveException, ControllerNotLoadedException, IllegalArgumentException, InvalidPermissionException{
        this.validateEnvironment();
        this.obj.deletePost(msg);
        saveChanges();
    }

    
    /**
     * Changes the group name
     * @param newName
     * @throws SessionNotActiveException
     * @throws ControllerNotLoadedException
     */
    public void changeGroupName(String newName) throws SessionNotActiveException, ControllerNotLoadedException{
    	this.validateEnvironment();
    	this.obj.setGroupName(newName);
    }




}
