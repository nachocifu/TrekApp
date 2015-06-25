package controllers;

import java.util.Collection;
import java.util.Date;
import java.util.HashSet;

import repositoryMem.GroupRepository;
import repositoryMem.ProfileRepository;
import repositoryMem.TripRepository;
import domain.ControllerNotLoadedException;
import domain.SessionNotActiveException;

/**
 * Class that will be used to access groups, profiles and trips that belong to the 
 * repository, this way the accessed repository will always be the same
 */
public class CollectionAndSearchController{

	/*the respositories that the class will access*/
    private ProfileRepository profileRepo;
    private GroupRepository groupRepo;
    @SuppressWarnings("unused")
	private TripRepository tripRepo;

    /**
     * Constructor with the repositories
     */
    public CollectionAndSearchController(ProfileRepository profileRepo, GroupRepository groupRepo, TripRepository tripRepo){
        this.profileRepo = profileRepo;
        this.groupRepo = groupRepo;
        this.tripRepo = tripRepo;
    }

    /**
     * @param startDate
     * @param endDate
     * @param startCity
     * @param endCity
     * @param description
     * @return a collection containing the group controllers, that match with the parameters
     * @throws SessionNotActiveException
     * @throws ControllerNotLoadedException
     */
    public Collection<GroupController> getGroupsWithTripsBy(Date startDate, Date endDate, String startCity, String endCity, String description ) throws SessionNotActiveException, ControllerNotLoadedException{
        validateEnvironment();
        return GroupController.generateListOfControllers(this.groupRepo.getGroupsWithTripsBy(startDate, endDate, startCity, endCity, description));
    }

    /**
     * 
     * @param username
     * @return a profile controller belonging to the profile with the username passed as a parameter
     * @throws SessionNotActiveException
     */
    public ProfileController getProfileControllerByUsername(String username) throws SessionNotActiveException{
        validateEnvironment();
        ProfileController profileController = new ProfileController(profileRepo);
        profileController.load(this.profileRepo.getById(username));
        return profileController;
    }

    /**
     * @param text
     * @return a collection of ProfileControllers whose fields coincide with the parameter
     * @throws SessionNotActiveException
     */
    public Collection<ProfileController> searchProfilesBy(String text) throws SessionNotActiveException{
        validateEnvironment();
        Collection<ProfileController> response = new HashSet<ProfileController>();
        return response;
    }

    /**
     * validate the enviroment, checks if the session is active
     * @throws SessionNotActiveException
     */
    private void validateEnvironment() throws SessionNotActiveException{
        if(!Session.getInstance().isActive())
            throw new SessionNotActiveException("ERROR || You must log in before operating.");
    }

}
