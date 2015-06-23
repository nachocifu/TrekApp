package controllers;

import java.util.Collection;
import java.util.Date;
import java.util.HashSet;

import repositoryMem.AbstractRepository;
import repositoryMem.TripRepository;
import domain.ControllerNotLoadedException;
import domain.SessionNotActiveException;
import domain.Trip;
import domain.TripStatus;

/**
 * An Trip object accessible to everyone
 * 
 *
 */
public class TripController extends AbstractController<Trip> {

    public TripController(AbstractRepository<Trip> repo) {
        super(repo);
    }

    /**
     * 
     * @return date when the trip starts
     * @throws SessionNotActiveException
     * @throws ControllerNotLoadedException
     */
    public Date getStartDate() throws SessionNotActiveException, ControllerNotLoadedException {
        this.validateEnvironment();
        return this.obj.getStartDate();
    }

    /**
     * 
     * @return the date when the trip begins
     * @throws SessionNotActiveException
     * @throws ControllerNotLoadedException
     */
    public Date getEndDate() throws SessionNotActiveException, ControllerNotLoadedException {
        this.validateEnvironment();
        return this.obj.getEndDate();
    }

    /**
     * 
     * @return the estimated cost of the trip
     * @throws SessionNotActiveException
     * @throws ControllerNotLoadedException
     */
    public Double getEstimateCost() throws SessionNotActiveException, ControllerNotLoadedException {
        this.validateEnvironment();
        return this.obj.getEstimateCost();
    }

    /**
     * 
     * @return the trips description
     * @throws SessionNotActiveException
     * @throws ControllerNotLoadedException
     */
    public String getTripDescription() throws SessionNotActiveException, ControllerNotLoadedException {
        this.validateEnvironment();
        return this.obj.getTripDescription();
    }

    /**
     * 
     * @return the city where the trip starts
     * @throws SessionNotActiveException
     * @throws ControllerNotLoadedException
     */
    public String getOriginCity() throws SessionNotActiveException, ControllerNotLoadedException {
        this.validateEnvironment();
        return this.obj.getOriginCity();
    }

    /**
     * 
     * @return the city where the finishes
     * @throws SessionNotActiveException
     * @throws ControllerNotLoadedException
     */
    public String getEndCity() throws SessionNotActiveException, ControllerNotLoadedException {
        this.validateEnvironment();
        return this.obj.getEndCity();
    }

    /**
     * 
     * @return the trips current status
     * @throws SessionNotActiveException
     * @throws ControllerNotLoadedException
     */
    public TripStatus getTripStatus() throws SessionNotActiveException, ControllerNotLoadedException {
        this.validateEnvironment();
        return this.obj.getTripStatus();
    }

    /**
     * Generate list of controllers from list of T objects
     * @param list
     * @return response List of controllers
     * @throws SessionNotActiveException
     */  
    protected static HashSet<TripController> generateListOfControllers(Collection<Trip> list) throws SessionNotActiveException {
        HashSet<TripController> response = new  HashSet<TripController>();
        Application app = Application.getInstance();
        for(Trip each: list){
            response.add(app.getATripController(each, null));
        }
        return response;
    }

    @Override
    protected TripRepository getRepository() {
        return (TripRepository) this.repository;
    }


}
