package controllers;

import java.util.Collection;
import java.util.Date;
import java.util.HashSet;

import repository.AbstractRepository;
import repository.ProfileRepository;
import domain.ControllerNotLoadedException;
import domain.Profile;
import domain.Review;
import domain.SessionNotActiveException;

public class ProfileController extends AbstractController<Profile> {

    public ProfileController(AbstractRepository<Profile> repo) {
        super(repo);
    }

    /**
     * Sets the Profile to be controlled by this controller.
     * @param profile the object id
     * @return success If load was successful
     * @throws SessionNotActiveException
     */
    public Boolean load(String username) throws SessionNotActiveException{
        if(!Session.getInstance().isActive())
            throw new SessionNotActiveException("ERROR || You must log in before operating.");

        return this.setObj( username );
    }

    /**
     * Generates the request to server and loads response.
     * @param username The username of the user
     * @return boolean To signal success
     */
    protected Boolean setObj(String username){
        Profile response = this.getRepository().getById(username);

        if(response != null){
            obj = response;
            return true;
        }
        else
            return false;
    }
    
    /**
     * This method is called from the method sendReviewToAMember in GroupController
     * @param rec
     * @param send
     * @param msg
     * @param rating
     */
    public void addReview(Profile sender, String msg, Integer rating){
    	this.obj.addReview(sender, msg, rating);
    	saveChanges();
    }

   public String getUsername() throws SessionNotActiveException, ControllerNotLoadedException{
        this.validateEnvironment();
        return this.obj.getUsrName();
    }

    public String getUserName() throws SessionNotActiveException, ControllerNotLoadedException{
        this.validateEnvironment();
        return this.obj.getName();
    }

    public String getSurname() throws SessionNotActiveException, ControllerNotLoadedException{
        this.validateEnvironment();
        return this.obj.getSurname();
    }

    public Boolean getSex() throws SessionNotActiveException, ControllerNotLoadedException{
        this.validateEnvironment();
        return this.obj.getSex();
    }

    public Date getBirthday() throws SessionNotActiveException, ControllerNotLoadedException{
        this.validateEnvironment();
        return this.obj.getBirthDay();
    }

    public Double getRating() throws SessionNotActiveException, ControllerNotLoadedException{
        this.validateEnvironment();
        return this.obj.getRating();
    }

    public String getCity() throws SessionNotActiveException, ControllerNotLoadedException{
        this.validateEnvironment();
        return this.getCity();
    }

    public String getMail() throws SessionNotActiveException, ControllerNotLoadedException{
        this.validateEnvironment();
        return this.obj.getEmail();
    }

    public HashSet<ProfileController> getFriends() throws SessionNotActiveException, ControllerNotLoadedException{
        this.validateEnvironment();
        return ProfileController.generateListOfControllers(obj.getFriends());
    }
    
    public HashSet<ProfileController> getBlockUsers() throws SessionNotActiveException, ControllerNotLoadedException{
        this.validateEnvironment();
        return ProfileController.generateListOfControllers(obj.getBlockedUsrs());
    }

    public Collection<GroupController> getGroups() throws SessionNotActiveException, ControllerNotLoadedException{
        this.validateEnvironment();
        return GroupController.generateListOfControllers(obj.getGroups());
    }

    public Collection<TripController> getTrips() throws SessionNotActiveException, ControllerNotLoadedException{
        this.validateEnvironment();
        return TripController.generateListOfControllers(obj.getTrips());
    }
    
    public Collection<Review> getReviews() throws SessionNotActiveException, ControllerNotLoadedException{
    	this.validateEnvironment();
    	return this.obj.getReviews();
    }

    /**
     * Generate list of controllers from list of T objects
     * @param list
     * @return response List of controllers
     * @throws SessionNotActiveException
     */
    protected static HashSet<ProfileController> generateListOfControllers(Collection<Profile> list) throws SessionNotActiveException{
        HashSet<ProfileController> response = new  HashSet<ProfileController>();
        Application app = Application.getInstance();
        for(Profile each: list){
            response.add(app.getAProfileController(each));
        }
        return response;
    }

    @Override
    protected  ProfileRepository getRepository() {
        return (ProfileRepository) this.repository;
    }
}
