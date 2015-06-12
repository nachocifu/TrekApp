package controllers;

import java.util.HashMap;
import java.util.HashSet;

import domain.ControllerNotLoadedException;
import domain.InvalidPermissionException;
import domain.Profile;
import domain.RequestStatus;
import domain.SessionNotActiveException;
import repository.GroupRepository;
import repository.TripRepository;
import repository.ProfileRepository;
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
        Profile member = profileController.getObject();
        if(!member.equals(this.obj.getAdminUser())){
            this.obj.addMember(member);
        }
    }
    /**
     * Returns a MyTripController that only the group admin can use
     * @param currentUser
     * @param tripRepo
     * @return
     * @throws SessionNotActiveException
     * @throws ControllerNotLoadedException
     */
    public MyTripController getMyTripController(CurrentProfileController currentUser, TripRepository tripRepo) throws SessionNotActiveException, ControllerNotLoadedException{
    	this.validateEnvironment();
        this.validateController(currentUser);
        if(!this.obj.getMembers().contains(currentUser.getObject())){
        	throw new IllegalArgumentException("You are not a member of this group");
        }
        return new MyTripController(tripRepo);
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
        Profile member = profileController.getObject();
        if(!member.equals(this.obj.getAdminUser())){
            this.obj.deleteMember(member);
            Profile newAdmin = this.obj.getMembers().iterator().next();
            if(newAdmin != null){
                this.obj.setAdminUser(newAdmin);
                profileController.getObject().leaveGroup(this.obj);
            }
            else{
            	deleteGroup();
            }
        }
        else{
            this.obj.deleteMember(member);
        }
    }
    
    /**
     * Returns a HashMap so that the front can list it and its values (0 if he has been rejected and 1 if is waiting for acceptance)
     * @return
     * @throws SessionNotActiveException
     * @throws ControllerNotLoadedException
     */
    public HashMap<ProfileController, RequestStatus> getMemberRequests() throws SessionNotActiveException, ControllerNotLoadedException{
    	this.validateEnvironment();
    	HashMap<ProfileController, RequestStatus> newMap = new HashMap<>();
    	Application app = Application.getInstance();
    	for (Profile profile : this.obj.getMemberRequests().keySet()) {
    		ProfileController controller = app.getProfileController();
    		controller.load(profile);
			newMap.put(controller, this.obj.getMemberRequests().get(profile));
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
    }

//    public void setCost(Double newCost) throws SessionNotActiveException, ControllerNotLoadedException{
//        this.validateEnvironment();
//        this.obj.setCosts(newCost);
//    }
//
//    public void addCost(Double costToAdd) throws SessionNotActiveException, ControllerNotLoadedException{
//        this.validateEnvironment();
//        this.obj.addCost(costToAdd);
//    }

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
    	
    }




}
