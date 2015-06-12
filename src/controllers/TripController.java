package controllers;

import java.util.Collection;
import java.util.Date;
import java.util.HashSet;

import repository.AbstractRepository;
import repository.GroupRepository;
import repository.TripRepository;
import repository.ProfileRepository;
import domain.ControllerNotLoadedException;
import domain.Profile;
import domain.Session;
import domain.SessionNotActiveException;
import domain.Trip;
import domain.TripStatus;
import domain.UniversalString;

public class TripController extends AbstractController<Trip> {

    public TripController(AbstractRepository<Trip> repo) {
        super(repo);
    }

    public Date getStartDate() throws SessionNotActiveException, ControllerNotLoadedException {
        this.validateEnvironment();
        return this.obj.getStartDate();
    }

    public Date getEndDate() throws SessionNotActiveException, ControllerNotLoadedException {
        this.validateEnvironment();
        return this.obj.getEndDate();
    }

    public Double getEstimateCost() throws SessionNotActiveException, ControllerNotLoadedException {
        this.validateEnvironment();
        return this.obj.getEstimateCost();
    }

    public UniversalString getTripDescription() throws SessionNotActiveException, ControllerNotLoadedException {
        this.validateEnvironment();
        return this.obj.getTripDescription();
    }

    public String getOriginCity() throws SessionNotActiveException, ControllerNotLoadedException {
        this.validateEnvironment();
        return this.obj.getOriginCity();
    }

    public String getEndCity() throws SessionNotActiveException, ControllerNotLoadedException {
        this.validateEnvironment();
        return this.obj.getEndCity();
    }

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
    protected static HashSet<TripController> generateListOfControllers(Collection<Trip> list) throws SessionNotActiveException{
        HashSet<TripController> response = new  HashSet<TripController>();
        Application app = Application.getInstance();
        String currentUser = Session.getInstance().getUserName();
        TripController controller;

        for(Trip each: list){
            if(each. getUsrName().equals(currentUser))
                controller = app.getMyTripController();
            else
                controller = app.getTripController();

            controller.load(each);
            response.add(controller);
        }
        return response;
    }

    @Override
    protected TripRepository getRepository() {
        return (TripRepository) this.repository;
    }


}
