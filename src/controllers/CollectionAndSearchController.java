package controllers;

import java.util.Collection;
import java.util.Date;

import repositoryMem.GroupRepository;
import repositoryMem.ProfileRepository;
import repositoryMem.TripRepository;
import domain.SessionNotActiveException;


public class CollectionAndSearchController{
	
	private ProfileRepository profileRepo;
	private GroupRepository groupRepo;
	private TripRepository tripRepo;
	
	public CollectionAndSearchController(ProfileRepository profileRepo, GroupRepository groupRepo, TripRepository tripRepo){
		this.profileRepo = profileRepo;
		this.groupRepo = groupRepo;
		this.tripRepo = tripRepo;
	}
	
	public Collection<GroupController> getGroupsWithTripsBy(Date startDate, Date endDate, String startCity, String endCity, String description ) throws SessionNotActiveException{
		validateEnvironment();
		return GroupController.generateListOfControllers(this.groupRepo.getGroupsWithTripsBy(startDate, endDate, startCity, endCity, description));
	}
	
	public ProfileController getProfileControllerByUsername(String username) throws SessionNotActiveException{
		validateEnvironment();
		ProfileController profileController = new ProfileController(profileRepo);
		profileController.load(this.profileRepo.getById(username));
		return profileController;
	}
	
	private void validateEnvironment() throws SessionNotActiveException{
		if(!Session.getInstance().isActive())
			throw new SessionNotActiveException("ERROR || You must log in before operating.");
	}
	
}
