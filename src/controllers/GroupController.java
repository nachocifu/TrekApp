package controllers;

import java.util.HashSet;

import domain.ControllerNotLoadedException;
import domain.Group;
import domain.Session;
import domain.SessionNotActiveException;
import repository.GroupRepository;
import repository.TripRepository;
import repository.UserRepository;

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


}
