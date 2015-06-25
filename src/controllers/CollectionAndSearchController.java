package controllers;

import java.util.Collection;
import java.util.Date;
import java.util.HashSet;

import repositoryMem.GroupRepository;
import repositoryMem.ProfileRepository;
import repositoryMem.TripRepository;
import domain.ControllerNotLoadedException;
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

    public Collection<GroupController> getGroupsWithTripsBy(Date startDate, Date endDate, String startCity, String endCity, String description ) throws SessionNotActiveException, ControllerNotLoadedException{
        validateEnvironment();
        return GroupController.generateListOfControllers(this.groupRepo.getGroupsWithTripsBy(startDate, endDate, startCity, endCity, description));
    }

    public ProfileController getProfileControllerByUsername(String username) throws SessionNotActiveException{
        validateEnvironment();
        ProfileController profileController = new ProfileController(profileRepo);
        profileController.load(this.profileRepo.getById(username));
        return profileController;
    }

    public Collection<ProfileController> searchProfilesBy(String text) throws SessionNotActiveException{
        validateEnvironment();
        Collection<ProfileController> response = new HashSet<ProfileController>();
        return response;
    }

    private void validateEnvironment() throws SessionNotActiveException{
        if(!Session.getInstance().isActive())
            throw new SessionNotActiveException("ERROR || You must log in before operating.");
    }

}
