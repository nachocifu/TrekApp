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

    public Date getStart_date() throws SessionNotActiveException, ControllerNotLoadedException {
        this.validateEnvironment();
        return this.obj.getstartDate();
    }

    public Date getEnd_date() throws SessionNotActiveException, ControllerNotLoadedException {
        this.validateEnvironment();
        return this.obj.getendDate();
    }

    public Integer getEstimate_cost() throws SessionNotActiveException, ControllerNotLoadedException {
        this.validateEnvironment();
        return this.obj.getestimateCost();
    }

    public UniversalString getTrip_description() throws SessionNotActiveException, ControllerNotLoadedException {
        this.validateEnvironment();
        return this.obj.gettripDescription();
    }

    public String getOrigin_city() throws SessionNotActiveException, ControllerNotLoadedException {
        this.validateEnvironment();
        return this.obj.getoriginCity();
    }

    public String getEnd_city() throws SessionNotActiveException, ControllerNotLoadedException {
        this.validateEnvironment();
        return this.obj.getendCity();
    }

    public TripStatus getTrip_status() throws SessionNotActiveException, ControllerNotLoadedException {
        this.validateEnvironment();
        return this.obj.getTripStatus();
    }

    public Integer getTripId() throws SessionNotActiveException, ControllerNotLoadedException{
        this.validateEnvironment();
        return this.obj.getId();
    }

}
