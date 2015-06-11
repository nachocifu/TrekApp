package controllers;

import java.util.Date;

import repository.GroupRepository;
import repository.TripRepository;
import repository.UserRepository;
import domain.ControllerNotLoadedException;
import domain.SessionNotActiveException;
import domain.Trip;
import domain.TripStatus;
import domain.UniversalString;

public class TripController extends AbstractController<Trip> {

    public TripController(UserRepository profileRepo, TripRepository tripRepo,
            GroupRepository groupRepo) {
        super(profileRepo, tripRepo, groupRepo);
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


}
