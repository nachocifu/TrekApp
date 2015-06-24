package controllers;

import java.rmi.ServerException;
import java.util.Date;

import repositoryMem.GroupRepository;
import repositoryMem.TripRepository;
import repositoryMem.ProfileRepository;
import domain.Group;
import domain.GroupNameAlreadyExistsException;
import domain.Profile;
import domain.SessionNotActiveException;
import domain.Trip;
import domain.UserNameAlreadyExistsException;
import domain.UserNameDoesNotExist;

public class Application{

    /* Singleton Reference*/
    private static Application application = null;

    /* Default database to be used if non selected.*/
    private String DEFAULT_DATABASE = "DataBase";

    /* Default database to be used if non selected.*/
    private String currentDatabase;


    /* Store references to all repository's*/
    private ProfileRepository userRepo;
    private GroupRepository groupRepo;
    private TripRepository tripRepo;

    /**
     * Constructor of the application.
     * NOTE: If database non existant, it will be created.
     * @param pathToDataBase Path to dataBase from  ./src/
     */
    private Application(String pathToDataBase){
        this.currentDatabase = pathToDataBase.trim();
        if(this.currentDatabase.isEmpty())
            this.currentDatabase = this.DEFAULT_DATABASE;

        /* Start all Databases */
        userRepo = new ProfileRepository(currentDatabase, Profile.class);
        groupRepo = new GroupRepository(currentDatabase, Group.class);
        tripRepo = new TripRepository(currentDatabase, Trip.class);

    }

    /**
     * Request singleton reference to the Application
     * @return application The Application
     */
    public static Application getInstance(){
        if(application == null){
            application = new Application("");
        }
        return application;
    }

    /**
     * Registers a new user into the system. If username exists throws exception.
     * @param username
     * @param name
     * @param surname
     * @param brthDay
     * @param sex
     * @param password
     * @param city
     * @param email
     * @throws ServerException
     * @throws UserNameAlreadyExistsException
     */
    public void registerUser(String username, String name, String surname, Date brthDay, boolean sex, String password, String city, String email ) throws ServerException, UserNameAlreadyExistsException{
        if(username.trim().isEmpty()
            || name.trim().isEmpty()
            || surname.trim().isEmpty()
            || brthDay == null
            || password.trim().isEmpty()
            || city.trim().isEmpty())
            throw new IllegalArgumentException("ERROR || Error registering user. Check arguments.");
        if(! this.userRepo.add( new Profile(username, name, surname, brthDay, sex, password, city, email)))
        	throw new UserNameAlreadyExistsException("the username already exists");
    }

    /**
     * Registers a new group into the system.
     * @param groupName
     * @param admin
     * @param maxGroupSize
     * @param filterAge
     * @param filterCity
     * @return
     * @throws ServerException
     * @throws UserNameAlreadyExistsException
     */
    //Revisar el throwsUserName
    public MyGroupController registerGroup(String groupName, CurrentProfileController admin, Integer maxGroupSize, Integer filterAge, String filterCity) throws ServerException, GroupNameAlreadyExistsException{
        if(groupName.trim().isEmpty()
            || admin == null
            || maxGroupSize <= 0
            || filterAge <= 0
            || filterCity.trim().isEmpty())
            throw new IllegalArgumentException("ERROR || Error registering group. Check arguments.");
        Group newGroup = new Group(groupName, admin.getObject(), maxGroupSize, filterAge, filterCity);
        if ( !this.groupRepo.add(newGroup))
        	throw new GroupNameAlreadyExistsException("a group with this name already exists");
        return new MyGroupController(groupRepo);
    }

    /**
     * Registers a new trip into the system,
     * @param startDate
     * @param endDate
     * @param estimateCost
     * @param tripDescription
     * @param originCity
     * @param endCity
     * @return
     * @throws ServerException
     * @throws UserNameAlreadyExistsException
     * @throws SessionNotActiveException
     */
    //Revisar el throwsUserName
    public MyTripController registerTrip(Date startDate, Date endDate, Double estimateCost, String tripDescription, String originCity, String endCity) throws ServerException, UserNameAlreadyExistsException, SessionNotActiveException{
        if(estimateCost < 0 || endCity.trim().isEmpty() || originCity.trim().isEmpty() || tripDescription.trim().isEmpty())
                throw new IllegalArgumentException("ERROR || Error registering trip. Check arguments.");
        Trip newTrip = new Trip(startDate, endDate, estimateCost, tripDescription, originCity, endCity);
        this.tripRepo.add(newTrip);
        MyTripController myTripController = new MyTripController(tripRepo);
        myTripController.load(newTrip);
        return myTripController;
    }

    /**
     * Change the Default Data Base.
     * NOTE: ALL sessions are immediately logged out.
     * @param pathToDataBase
     */
    public void changeDataBase(String pathToDataBase){
        Session.getInstance().logOut();
        application = new Application(pathToDataBase);
    }

    /**
     * Validates userName and password with the repository
     * @param userName
     * @param passWord
     * @return boolean value informing if the password is valid
     */
    protected boolean validate(String userName, String passWord) {
        return this.userRepo.validateCredentials(userName,passWord);
    }

    /**
     * Returns a Controller of the actual logged user
     * @return
     * @throws SessionNotActiveException
     */
    public CurrentProfileController getCurrentProfileController() throws SessionNotActiveException{
        if(!Session.getInstance().isActive()){
            throw new SessionNotActiveException("There is no user logged in");
        }
        Profile currentProfile = this.userRepo.getById(Session.getInstance().getUserName());
        return getCurrentProfileController(currentProfile);
    }
    
    public CollectionAndSearchController getCollectionController() throws SessionNotActiveException{
        if(!Session.getInstance().isActive()){
            throw new SessionNotActiveException("There is no user logged in");
        }
        return new CollectionAndSearchController(this.userRepo, this.groupRepo, this.tripRepo);
    }

    /**
     * Returns a trip Controller depending on the admin access, only for Controllers use inside this package
     * @param trip
     * @param groupAdmin
     * @return
     * @throws SessionNotActiveException
     */
    protected TripController getATripController(Trip trip, Profile groupAdmin) throws SessionNotActiveException{
        if (groupAdmin != null && groupAdmin.getUsrName().equals(Session.getInstance().getUserName())) {
            return getMyTripController(trip);
        } else {
            return getTripController(trip);
        }
    }

    /**
     * For internal use to create a TripController
     * @param trip
     * @return
     * @throws SessionNotActiveException
     */
    private TripController getTripController(Trip trip) throws SessionNotActiveException{
        TripController newController = new TripController(tripRepo);
        newController.load(trip);
        return newController;
    }

    /**
     * For internal use to create a TripController
     * @param trip
     * @return
     * @throws SessionNotActiveException
     */
    private MyTripController getMyTripController(Trip trip) throws SessionNotActiveException{
        MyTripController newController = new MyTripController(tripRepo);
        newController.load(trip);
        return newController;
    }

    /**
     * Returns a profile Controller depending on the admin access, only for Controllers use inside this package
     * @param profile
     * @return
     * @throws SessionNotActiveException
     */
    protected ProfileController getAProfileController(Profile profile) throws SessionNotActiveException {
        if (profile.getUsrName().equals(Session.getInstance().getUserName())) {
            return getCurrentProfileController(profile);
        } else {
            return getProfileController(profile);
        }
    }

    /**
     * For internal use to create a ProfileController
     * @param profile
     * @return
     * @throws SessionNotActiveException
     */
    private ProfileController getProfileController(Profile profile) throws SessionNotActiveException{
        ProfileController newController = new ProfileController(userRepo);
        newController.load(profile);
        return newController;
    }

    /**
     * For internal use to create a ProfileController
     * @param profile
     * @return
     * @throws SessionNotActiveException
     */
    private CurrentProfileController getCurrentProfileController(Profile profile) throws SessionNotActiveException{
        CurrentProfileController newController = new CurrentProfileController(userRepo);
        newController.load(profile);
        return newController;
    }

    /**
     * Returns a group Controller depending on the admin access, only for Controllers use inside this package
     * @param group
     * @return
     * @throws SessionNotActiveException
     */
    protected GroupController getAGroupController(Group group) throws SessionNotActiveException{
        if (group.getAdminUser().getUsrName().equals(Session.getInstance().getUserName())) {
            return getMyGroupController(group);
        } else {
            return getGroupController(group);
        }
    }

    /**
     * For internal use to create a GroupController
     * @param group
     * @return
     * @throws SessionNotActiveException
     */
    private MyGroupController getMyGroupController(Group group) throws SessionNotActiveException{
        MyGroupController newController = new MyGroupController(groupRepo);
        newController.load(group);
        return newController;
    }

    /**
     * For internal use to create a GroupController
     * @param group
     * @return
     * @throws SessionNotActiveException
     */
    private GroupController getGroupController(Group group) throws SessionNotActiveException{
        GroupController newController = new GroupController(groupRepo);
        newController.load(group);
        return newController;
    }

    public String getDatabase() {
        return this.currentDatabase;
    }
}

