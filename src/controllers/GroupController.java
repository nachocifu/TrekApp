package controllers;

import java.util.Collection;
import java.util.HashSet;

import domain.ControllerNotLoadedException;
import domain.Group;
import domain.InvalidPermissionException;
import domain.SessionNotActiveException;
import repository.AbstractRepository;
import repository.GroupRepository;
import domain.Message;


public class GroupController extends AbstractController<Group> {

    public GroupController(GroupRepository groupRepo) {
        super(groupRepo);
    }

    /**
     * Returns the group name
     * @return
     * @throws SessionNotActiveException
     * @throws ControllerNotLoadedException
     */
    public String getGroupName() throws SessionNotActiveException, ControllerNotLoadedException{
        this.validateEnvironment();
        return this.obj.getGroupName();
    }

    /**
     * Returns a Collection of ProfileControllers
     * @return
     * @throws SessionNotActiveException
     * @throws ControllerNotLoadedException
     */
    public HashSet<ProfileController> getMembers() throws SessionNotActiveException, ControllerNotLoadedException{
        this.validateEnvironment();
        return ProfileController.generateListOfControllers(obj.getMembers());
    }

    /**
     * Returns
     * @return
     * @throws SessionNotActiveException
     * @throws ControllerNotLoadedException
     */
    public ProfileController getAdmin() throws SessionNotActiveException, ControllerNotLoadedException{
        this.validateEnvironment();
        return Application.getInstance().getAProfileController(this.obj.getAdminUser());
    }

    /**
     * Ads a post to the wall if the profile is a member of the group
     * @param profileController
     * @param msg
     * @throws SessionNotActiveException
     * @throws ControllerNotLoadedException
     * @throws InvalidPermissionException
     */
    public void addPost(ProfileController profileController, Message msg) throws SessionNotActiveException, ControllerNotLoadedException, InvalidPermissionException{
        this.validateEnvironment();
        this.validateController(profileController);
        this.obj.addPost(profileController.getObject(), msg);
        saveChanges();
    }

    /**
     * Ads a trip to the group if the profile is a member of the group
     * @param profileController
     * @param tripController
     * @throws SessionNotActiveException
     * @throws ControllerNotLoadedException
     * @throws InvalidPermissionException
     */
    public void addGroupTrip(ProfileController profileController, TripController tripController) throws SessionNotActiveException, ControllerNotLoadedException, InvalidPermissionException{
        this.validateEnvironment();
        this.validateController(tripController);
        this.validateController(profileController);
        this.obj.addGroupTrip(profileController.getObject(), tripController.getObject());
        saveChanges();
    }

    public Integer groupSize(){
        return this.obj.groupSize();
    }

    /**
     * Validates who is sending the review and if that "member" is a member of the group
     * @param loggedUser
     * @param member
     * @param msg
     * @param rating
     * @throws SessionNotActiveException
     * @throws ControllerNotLoadedException
     */
    public void sendReviewToAMember(CurrentProfileController loggedUser, ProfileController member, String msg, Integer rating) throws SessionNotActiveException, ControllerNotLoadedException{
    	this.validateEnvironment();
        this.validateController(loggedUser);
        this.validateController(member);
    	if(loggedUser.getObject().equals(member.getObject())){
    		throw new IllegalArgumentException("No puedes mandar una review a ti mismo");
    	}
    	else if(!this.obj.getMembers().contains(loggedUser.getObject())){
    		throw new IllegalArgumentException("No puedes enviar una review porque no perteneces a este grupo");
    	}
    	else if(!this.obj.getMembers().contains(member.getObject())){
    		throw new IllegalArgumentException("No puedes enviar una review a esa persona porque no pertenece a este grupo");
    	}
    	member.addReview(loggedUser.getObject(), msg, rating);
    }
    
    /**
     * Returns a TripController or a MyTripController depending of the admin access
     * @return
     * @throws SessionNotActiveException
     * @throws ControllerNotLoadedException
     */
    public TripController getTripController() throws SessionNotActiveException, ControllerNotLoadedException{
    	this.validateEnvironment();
    	Application app = Application.getInstance();
    	return app.getATripController(this.obj.getGroupTrip(), this.obj.getAdminUser());
    }
    
    /**
     * Ads a member request to the group
     * @param possibleMember
     * @throws SessionNotActiveException
     * @throws ControllerNotLoadedException
     */
    public void sendMemberRequest(ProfileController possibleMember) throws SessionNotActiveException, ControllerNotLoadedException{
    	this.validateEnvironment();
        this.validateController(possibleMember);
        this.obj.addMemberRequest(possibleMember.getObject());
    }
    
    /**
     * Generate list of controllers from list of T objects
     * @param list
     * @return response List of controllers
     * @throws SessionNotActiveException
     */
    protected static HashSet<GroupController> generateListOfControllers(Collection<Group> list) throws SessionNotActiveException{
        HashSet<GroupController> response = new  HashSet<GroupController>();
        Application app = Application.getInstance();
        for(Group each: list){
            response.add(app.getAGroupController(each));
        }
        return response;
    }

    @Override
    protected AbstractRepository<Group> getRepository() {
        return (GroupRepository) this.repository;
    }
}
