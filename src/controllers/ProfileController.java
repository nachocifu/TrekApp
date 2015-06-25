package controllers;

import java.util.Collection;
import java.util.Date;
import java.util.HashSet;
import repositoryMem.AbstractRepository;
import repositoryMem.ProfileRepository;
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
     *
     * @return a string containing the profiles username
     * @throws SessionNotActiveException
     * @throws ControllerNotLoadedException
     */
   public String getUsername() throws SessionNotActiveException, ControllerNotLoadedException{
        this.validateEnvironment();
        return this.obj.getUsrName();
    }

   /**
    * @return a string containing the users name
    * @throws SessionNotActiveException
    * @throws ControllerNotLoadedException
    */
    public String getUserName() throws SessionNotActiveException, ControllerNotLoadedException{
        this.validateEnvironment();
        return this.obj.getName();
    }

    /**
     * @return a string with the users surname
     * @throws SessionNotActiveException
     * @throws ControllerNotLoadedException
     */
    public String getSurname() throws SessionNotActiveException, ControllerNotLoadedException{
        this.validateEnvironment();
        return this.obj.getSurname();
    }

    /**
     *
     * @return a boolean reperesenting the profiles sex, true being female and false male
     * @throws SessionNotActiveException
     * @throws ControllerNotLoadedException
     */
    public Boolean getSex() throws SessionNotActiveException, ControllerNotLoadedException{
        this.validateEnvironment();
        return this.obj.getSex();
    }

    /**
     *
     * @return a date with the profiles birthday
     * @throws SessionNotActiveException
     * @throws ControllerNotLoadedException
     */
    public Date getBirthday() throws SessionNotActiveException, ControllerNotLoadedException{
        this.validateEnvironment();
        return this.obj.getBirthDay();
    }

    /**
     *
     * @return the profiles average rating
     * @throws SessionNotActiveException
     * @throws ControllerNotLoadedException
     */
    public Double getRating() throws SessionNotActiveException, ControllerNotLoadedException{
        this.validateEnvironment();
        return this.obj.getRating();
    }

    /**
     *
     * @return the city where the profile is from
     * @throws SessionNotActiveException
     * @throws ControllerNotLoadedException
     */
    public String getCity() throws SessionNotActiveException, ControllerNotLoadedException{
        this.validateEnvironment();
        return this.obj.getCity();
    }

    /**
     *
     * @return the profiles email
     * @throws SessionNotActiveException
     * @throws ControllerNotLoadedException
     */
    public String getMail() throws SessionNotActiveException, ControllerNotLoadedException{
        this.validateEnvironment();
        return this.obj.getEmail();
    }

    /**
     *
     * @return a Collection containing ProfileController's, which would be the profiles friends ProfileControllers
     * @throws SessionNotActiveException
     * @throws ControllerNotLoadedException
     */
    public Collection<ProfileController> getFriends() throws SessionNotActiveException, ControllerNotLoadedException{
        this.validateEnvironment();
        return ProfileController.generateListOfControllers(obj.getFriends());
    }

    /**
     *
     * @return a Collection containing ProfileController's, which would be the profiles blocked users ProfileControllers
     * @throws SessionNotActiveException
     * @throws ControllerNotLoadedException
     */
    public Collection<ProfileController> getBlockUsers() throws SessionNotActiveException, ControllerNotLoadedException{
        this.validateEnvironment();
        return ProfileController.generateListOfControllers(obj.getBlockedUsrs());
    }

    /**
     *
     * @returna Collection containing GroupController's, which would be the profile's groups GroupControllers
     * @throws SessionNotActiveException
     * @throws ControllerNotLoadedException
     */
    public Collection<GroupController> getGroups() throws SessionNotActiveException, ControllerNotLoadedException{
        this.validateEnvironment();
        return GroupController.generateListOfControllers(obj.getGroups());
    }

    /**
     *
     * @return a Collection containing TripController's, which would be the profiles trips TripControllers
     * @throws SessionNotActiveException
     * @throws ControllerNotLoadedException
     */
    public Collection<TripController> getTrips() throws SessionNotActiveException, ControllerNotLoadedException{
        this.validateEnvironment();
        return TripController.generateListOfControllers(obj.getTrips());
    }

    /**
     *
     * @return a Collection containting the profiles reviews
     * @throws SessionNotActiveException
     * @throws ControllerNotLoadedException
     */
    public Collection<Review> getReviews() throws SessionNotActiveException, ControllerNotLoadedException{
        this.validateEnvironment();
        return this.obj.getReviews();
    }

    /**
     * Generate list of controllers from list of T objects
     * @param list
     * @return response List of controllers
     * @throws SessionNotActiveException
     * @throws ControllerNotLoadedException
     */
    protected static HashSet<ProfileController> generateListOfControllers(Collection<Profile> list) throws SessionNotActiveException, ControllerNotLoadedException{
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
