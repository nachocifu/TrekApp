package src.controllers;

import domain.ControllerNotLoadedException;
import domain.Profile;
import domain.SessionNotActiveException;
import repository.GroupRepository;
import repository.TripRepository;
import repository.UserRepository;
import src.domain.Message;
import src.domain.Trip;
import GroupController;

public class MyGroupController extends GroupController {

	public MyGroupController(UserRepository profileRepo, TripRepository tripRepo, GroupRepository groupRepo) {
        super(profileRepo, tripRepo, groupRepo);
        // TODO Auto-generated constructor stub
    }
	
	public void addMember(ProfileController profileController) throws SessionNotActiveException, ControllerNotLoadedException{
		this.validateEnvironment();
		Profile member = profileController.getObject();
		if(!member.equals(this.obj.getAdminUser())){
			this.obj.addMember(member);
		}
	}
	
	public void deleteMember(ProfileController profileController) throws SessionNotActiveException, ControllerNotLoadedException{
		this.validateEnvironment();
		Profile member = profileController.getObject();
		if(!member.equals(this.obj.getAdminUser())){
			this.obj.deleteMember(member);
		}
	}
	
	public void setCost(Double newCost) throws SessionNotActiveException, ControllerNotLoadedException{
		this.validateEnvironment();
		this.obj.setCosts(newCost);
	}
	
	public void addCost(Double costToAdd) throws SessionNotActiveException, ControllerNotLoadedException{
		this.validateEnvironment();
		this.obj.addCost(costToAdd);
	}
	
	public void deleteGroupTrip(TripController tripController) throws SessionNotActiveException, ControllerNotLoadedException{
		this.validateEnvironment();
		this.obj.deleteGroupTrip(tripController.getObject());
	}
	
	public void deletePost(Message msg) throws SessionNotActiveException, ControllerNotLoadedException{
		this.validateEnvironment();
		this.obj.deletePost(msg);
	}
	
	
	
	
}
