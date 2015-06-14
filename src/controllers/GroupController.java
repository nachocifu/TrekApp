package controllers;

import java.util.Collection;
import java.util.HashSet;

import domain.ControllerNotLoadedException;
import domain.Group;
import domain.InvalidPermissionException;
import domain.SessionNotActiveException;
import domain.TripNotClosedException;
import repository.AbstractRepository;
import repository.GroupRepository;
import domain.Message;


public class GroupController extends AbstractController<Group> {

    public GroupController(GroupRepository groupRepo) {
        super(groupRepo);
    }

    /**
     * @return Returns the Group name
     * @throws SessionNotActiveException
     * @throws ControllerNotLoadedException
     */
    public String getGroupName() throws SessionNotActiveException, ControllerNotLoadedException{
        this.validateEnvironment();
        return this.obj.getGroupName();
    }

    /**
     * @return Returns a Collection of ProfileControllers
     * @throws SessionNotActiveException
     * @throws ControllerNotLoadedException
     */
    public HashSet<ProfileController> getMembers() throws SessionNotActiveException, ControllerNotLoadedException{
        this.validateEnvironment();
        return ProfileController.generateListOfControllers(obj.getMembers());
    }

    /**
     * @return Returns a ProfileController of the Group admin
     * @throws SessionNotActiveException
     * @throws ControllerNotLoadedException
     */
    public ProfileController getAdmin() throws SessionNotActiveException, ControllerNotLoadedException{
        this.validateEnvironment();
        return Application.getInstance().getAProfileController(this.obj.getAdminUser());
    }

    /**
     * Adds a post to the wall if the profile is a member of the group
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
     * Adds a trip to the group if the profile is a member of the group
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
    
    /**
     * @return Returns the size of the group
     */
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
     * @throws TripNotClosedException 
     */
    public void sendReviewToAMember(CurrentProfileController loggedUser, ProfileController member, String msg, Integer rating) throws SessionNotActiveException, ControllerNotLoadedException, TripNotClosedException{
    	this.validateEnvironment();
        this.validateController(loggedUser);
        this.validateController(member);
        this.obj.sendReviewToAMember(loggedUser.getObject(), member.getObject(), msg, rating);
    }
    
    /**
     * @return Returns a TripController or a MyTripController depending of the admin access
     * @throws SessionNotActiveException
     * @throws ControllerNotLoadedException
     */
    public TripController getTripController() throws SessionNotActiveException, ControllerNotLoadedException{
    	this.validateEnvironment();
    	Application app = Application.getInstance();
    	return app.getATripController(this.obj.getGroupTrip(), this.obj.getAdminUser());
    }
    
    /**
     * Adds a member request to the group
     * @param possibleMember
     * @throws SessionNotActiveException
     * @throws ControllerNotLoadedException
     */
    public void sendMemberRequest(ProfileController possibleMember) throws SessionNotActiveException, ControllerNotLoadedException{
    	this.validateEnvironment();
        this.validateController(possibleMember);
        this.obj.addMemberRequest(possibleMember.getObject());
        saveChanges();
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
