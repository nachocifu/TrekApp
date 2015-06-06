package controllers;

import java.util.HashSet;

import com.sun.xml.internal.ws.util.StringUtils;

import repository.AbstractRepository;
import repository.GroupRepository;
import repository.TripRepository;
import repository.UserRepository;
import domain.Group;
import domain.Profile;
import domain.Session;
import domain.Trip;

public class Application{

    /* Singleton Reference*/
    private static Application application = null;

    /* Default database to be used if non selected.*/
    private String DEFAULT_DATABASE = "DataBase";

    /* Store references to all repository's*/
    private UserRepository userRepo;
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
        userRepo = new UserRepository(pathToDataBase, Profile.class);
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
}

