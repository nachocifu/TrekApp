package controllers;

import java.rmi.ServerException;
import java.util.Date;
import repository.GroupRepository;
import repository.TripRepository;
import repository.ProfileRepository;
import domain.Group;
import domain.Profile;
import domain.Session;
import domain.Trip;
import domain.UserNameAlreadyExistsException;

public class Application{

    /* Singleton Reference*/
    private static Application application = null;

    /* Default database to be used if non selected.*/
    private String DEFAULT_DATABASE = "DataBase";

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
        String pathString = pathToDataBase.trim();
        if(pathString.isEmpty())
            pathString = this.DEFAULT_DATABASE;

        pathString = "jdbc:sqlite:" + pathString;

        /* Start all Databases */
        userRepo = new ProfileRepository(pathToDataBase, Profile.class);
        groupRepo = new GroupRepository(pathToDataBase, Group.class);
        tripRepo = new TripRepository(pathToDataBase, Trip.class);
    }

    /**
     * Request singeton reference to the Application
     * @return application The Application
     */
    public static Application getInstance(){
        if(application == null)
            application = new Application("");
        return application;
    }

    /**
     * Registers a new user in the system. If username exists throws exception.
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
            || city.trim().isEmpty() )
            throw new IllegalArgumentException("ERROR || Error registering user. Check arguments.");

        this.userRepo.add( new Profile(username, name, surname, brthDay, sex, password, city, email));
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
    public MyGroupController registerGroup(String groupName, CurrentProfileController admin, Integer maxGroupSize, Integer filterAge, String filterCity) throws ServerException, UserNameAlreadyExistsException{
    	if(groupName.trim().isEmpty() || admin == null || maxGroupSize <= 0 || filterAge <= 0 || filterCity.trim().isEmpty())
                throw new IllegalArgumentException("ERROR || Error registering group. Check arguments.");
        Group newGroup = new Group(groupName, admin.getObject(), maxGroupSize, filterAge, filterCity);
    	this.groupRepo.add(newGroup);
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
     */
    //Revisar el throwsUserName
    public MyTripController registerTrip(Date startDate, Date endDate, Double estimateCost, String tripDescription, String originCity, String endCity) throws ServerException, UserNameAlreadyExistsException{
    	if(estimateCost < 0 || endCity.trim().isEmpty() || originCity.trim().isEmpty() || tripDescription.trim().isEmpty())
                throw new IllegalArgumentException("ERROR || Error registering group. Check arguments.");
        Trip newTrip = new Trip(startDate, endDate, estimateCost, tripDescription, originCity, endCity);
    	this.tripRepo.add(newTrip);
        return new MyTripController(tripRepo);    
    }

    /**
     * Change the Default Data Base.
     * NOTE: ALL sessions are imediately logged out.
     * @param pathToDataBase
     */
    public void changeDataBase(String pathToDataBase){
        Session.getInstance().logOut();
        application = new Application(pathToDataBase);
    }


    public boolean validate(String userName, String passWord) {
        return this.userRepo.validateCredentials(userName,passWord);
    }

    public ProfileController getProfileController(){
        return new ProfileController(userRepo);
    }

    public CurrentProfileController getCurrentProfileController(){
        return new CurrentProfileController(userRepo);
    }

    public GroupController getGroupController(){
        return new GroupController(groupRepo);
    }

    public MyGroupController getMyGroupController(){
        return new MyGroupController(groupRepo);
    }

    public TripController getTripController(){
        return new TripController(tripRepo);
    }

    public MyTripController getMyTripController(){
        return new MyTripController(tripRepo);
    }

    public ProfileController getController(Profile profile) {
        if (profile.getUsrName().equals(Session.getInstance().getUserName())) {
            return getCurrentProfileController();
        } else {
            return getProfileController();
        }
    }
}

