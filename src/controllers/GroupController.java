package controllers;

import java.util.Collection;
import java.util.HashSet;

import domain.ControllerNotLoadedException;
import domain.Group;
import domain.InvalidPermissionException;
import domain.Profile;
import domain.SessionNotActiveException;
import domain.TripNotClosedException;
import domain.TripStatus;
import repositoryMem.AbstractRepository;
import repositoryMem.GroupRepository;
import domain.Message;


public class GroupController extends AbstractController<Group> {

    public GroupController(GroupRepository groupRepo) {
        super(groupRepo);
    }

    /**
     * @return a Collection with the Members that are not reviewed
     * @throws SessionNotActiveException
     * @throws ControllerNotLoadedException
     */
    public Collection<ProfileController> getMissingMembersToReview() throws SessionNotActiveException, ControllerNotLoadedException{
        this.validateEnvironment();
        return ProfileController.generateListOfControllers(this.obj.getMissingReviewsToMakeByAMember(Application.getInstance().getCurrentProfileController().getObject()));
    }

    /**
     * @return
     * @throws ControllerNotLoadedException
     * @throws SessionNotActiveException
     */
    public HashSet<ProfileController> getMyMissingReviews() throws SessionNotActiveException, ControllerNotLoadedException{
        this.validateEnvironment();
        return ProfileController.generateListOfControllers(this.obj.getMissingReviewsToMakeByAMember(Application.getInstance().getCurrentProfileController().getObject()));
    }

    /**
     * @return Returns the status of the group trip
     * @throws SessionNotActiveException
     * @throws ControllerNotLoadedException
     */
    public TripStatus getTripStatus() throws SessionNotActiveException, ControllerNotLoadedException{
        this.validateEnvironment();
        return this.obj.getGroupTrip().getTripStatus();
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

//    /**
//     * Adds a post to the wall if the profile is a member of the group
//     * @param profileController
//     * @param msg
//     * @throws SessionNotActiveException
//     * @throws ControllerNotLoadedException
//     * @throws InvalidPermissionException
//     */
//    public void addPost(Message msg) throws SessionNotActiveException, ControllerNotLoadedException, InvalidPermissionException{
//        this.validateEnvironment();
//        Profile loggedUser = Application.getInstance().getCurrentProfileController().getObject();
//        if(!this.obj.getMembers().contains(loggedUser)){
//            throw new InvalidPermissionException("Cannot post because user is not a member of this group");
//        }
//        this.obj.addPost(loggedUser, msg);
//        saveChanges();
//    }

    /**
     * Adds a trip to the group if the profile is a member of the group
     * @param profileController
     * @param tripController
     * @throws SessionNotActiveException
     * @throws ControllerNotLoadedException
     * @throws InvalidPermissionException
     */
    public void addGroupTrip(TripController tripController) throws SessionNotActiveException, ControllerNotLoadedException, InvalidPermissionException{
        this.validateEnvironment();
        this.validateController(tripController);
        Profile loggedUser = Application.getInstance().getCurrentProfileController().getObject();
        if(!this.obj.getMembers().contains(loggedUser)){
            throw new InvalidPermissionException("Cannot add a trip because user is not a member of this group");
        }
        this.obj.addGroupTrip(tripController.getObject());
        saveChanges();
    }

    /**
     * @return Returns the size of the group
     */
    public Integer groupSize(){
        return this.obj.groupSize();
    }

    /**
     * @return Returns the maximum group size
     * @throws SessionNotActiveException
     * @throws ControllerNotLoadedException
     */
    public Integer getMaxGroupSize() throws SessionNotActiveException, ControllerNotLoadedException{
        this.validateEnvironment();
        return this.obj.getMaxGroupSize();
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
    public void sendReviewToAMember(ProfileController member, String msg, Integer rating) throws SessionNotActiveException, ControllerNotLoadedException, TripNotClosedException{
        this.validateEnvironment();
        this.validateController(member);
        Profile loggedUser = Application.getInstance().getCurrentProfileController().getObject();
        this.obj.sendReviewToAMember(loggedUser, member.getObject(), msg, rating);
        member.saveChanges();
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
     * @throws InvalidPermissionException
     */
    public void sendMemberRequest() throws SessionNotActiveException, ControllerNotLoadedException, InvalidPermissionException{
        this.validateEnvironment();
        Profile loggedUser = Application.getInstance().getCurrentProfileController().getObject();
        this.obj.addMemberRequest(loggedUser);
        saveChanges();
    }

    /**
     * Generate list of controllers from list of T objects
     * @param list
     * @return response List of controllers
     * @throws SessionNotActiveException
     * @throws ControllerNotLoadedException
     */
    protected static HashSet<GroupController> generateListOfControllers(Collection<Group> list) throws SessionNotActiveException, ControllerNotLoadedException{
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
