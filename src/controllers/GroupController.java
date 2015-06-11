package controllers;

import java.util.HashSet;

import domain.ControllerNotLoadedException;
import domain.Group;
import domain.Session;
import domain.SessionNotActiveException;
import repository.GroupRepository;
import repository.TripRepository;
import repository.UserRepository;
import domain.Message;


public class GroupController extends AbstractController<Group> {

    public GroupController(UserRepository profileRepo, TripRepository tripRepo,
            GroupRepository groupRepo) {
        super(profileRepo, tripRepo, groupRepo);
    }

    public String getGroupName() throws SessionNotActiveException, ControllerNotLoadedException{
        this.validateEnvironment();
        return this.obj.getGroupName();
    }

    public HashSet<ProfileController> getMembers() throws SessionNotActiveException, ControllerNotLoadedException{
        this.validateEnvironment();
        return this.generateListOfProfileControllers(obj.getMembers());
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

	@Override
	protected Boolean setObj(Integer objId) throws SessionNotActiveException {
		// TODO Auto-generated method stub
		return null;
	}
}
