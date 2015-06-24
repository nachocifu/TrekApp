package controllers;

import java.util.Collection;
import java.util.Date;

import repositoryMem.GroupRepository;
import repositoryMem.ProfileRepository;
import repositoryMem.TripRepository;
import domain.SessionNotActiveException;


public class CollectionController{
	
	private ProfileRepository profileRepo;
	private GroupRepository groupRepo;
	private TripRepository tripRepo;
	
	public CollectionController(ProfileRepository profileRepo, GroupRepository groupRepo, TripRepository tripRepo){
		this.profileRepo = profileRepo;
		this.groupRepo = groupRepo;
		this.tripRepo = tripRepo;
	}
	
	public Collection<GroupController> getGroupsWithTripsBy(Date startDate, Date endDate, String startCity, String endCity, String description ){
		
	}

	
}
