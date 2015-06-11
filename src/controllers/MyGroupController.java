package controllers;

import domain.Profile;
import repository.GroupRepository;
import repository.TripRepository;
import repository.UserRepository;
import GroupController;

public class MyGroupController extends GroupController {

	public MyGroupController(UserRepository profileRepo, TripRepository tripRepo, GroupRepository groupRepo) {
        super(profileRepo, tripRepo, groupRepo);
        // TODO Auto-generated constructor stub
    }
	
	public void addMember(String usrName){
		Profile newMember = this.profileRepo.getById(usrName);
		this.obj.addMember(newMember);
	}
	
	public void deleteMember(String usrName){
		Profile member = this.profileRepo.getById(usrName);
		this.obj.deleteMember(member);
	}
	
	public void setCost(Double newCost){
		this.obj.setCosts(newCost);
	}
	
	public void addCost(Double costToAdd){
		this.obj.addCost(costToAdd);
	}
	
	
	
	
}
