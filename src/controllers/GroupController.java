package controllers;

import java.util.Collection;
import java.util.HashSet;

import domain.ControllerNotLoadedException;
import domain.Group;
import domain.Profile;
import domain.Session;
import domain.SessionNotActiveException;
import repository.AbstractRepository;
import repository.GroupRepository;
import repository.TripRepository;
import repository.ProfileRepository;
import domain.Message;


public class GroupController extends AbstractController<Group> {

    public GroupController(GroupRepository groupRepo) {
        super(groupRepo);
    }

    public String getGroupName() throws SessionNotActiveException, ControllerNotLoadedException{
        this.validateEnvironment();
        return this.obj.getGroupName();
    }

    public HashSet<ProfileController> getMembers() throws SessionNotActiveException, ControllerNotLoadedException{
        this.validateEnvironment();
        return ProfileController.generateListOfControllers(obj.getMembers());
    }

    public ProfileController getAdmin() throws SessionNotActiveException, ControllerNotLoadedException{
        this.validateEnvironment();
        ProfileController response = null;
        if(Session.getInstance().getUserName().equals(obj.getAdminUser().getUsrName()))
            response = Application.getInstance().getCurrentProfileController();
        else
            response = Application.getInstance().getProfileController();

        response.load(Session.getInstance().getUserName());
        return response;
    }

    //A continuaci�n solo getters y métodos que pueden hacer todos

    public Double getCosts() throws SessionNotActiveException, ControllerNotLoadedException{
        this.validateEnvironment();
        return this.obj.getCosts();
    }

    public Double getCostsPerMember() throws SessionNotActiveException, ControllerNotLoadedException{
        this.validateEnvironment();
        return this.obj.getCostsPerMember();
    }

    public void addPost(ProfileController profileController, Message msg) throws SessionNotActiveException, ControllerNotLoadedException{
        this.validateEnvironment();
        this.validateController(profileController);
        this.obj.addPost(profileController.getObject(), msg);
    }

    public void addGroupTrip(TripController tripController) throws SessionNotActiveException, ControllerNotLoadedException{
        this.validateEnvironment();
        this.validateController(tripController);
        this.obj.addGroupTrip(tripController.getObject());
    }

    public Integer groupSize(){
        return this.obj.groupSize();
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
        String currentUser = Session.getInstance().getUserName();
        GroupController controller;

        for(Group each: list){
            if(each.getAdminUser().getUsrName().equals(currentUser))
                controller = app.getMyGroupController();
            else
                controller = app.getGroupController();

            controller.load(each);
            response.add(controller);
        }
        return response;
    }

    @Override
    protected AbstractRepository<Group> getRepository() {
        return (GroupRepository) this.repository;
    }
}
