package controllers;

import java.util.Date;

import domain.ControllerNotLoadedException;
import domain.SessionNotActiveException;
import domain.TripStatus;

import repository.GroupRepository;
import repository.TripRepository;
import repository.ProfileRepository;

public class MyTripController extends TripController {

    public MyTripController(TripRepository tripRepo) {
        super(tripRepo);
    }

    public void setStartDate(Date date) throws SessionNotActiveException, ControllerNotLoadedException{
        this.validateEnvironment();
        this.setStartDate(date);
    }

    public void setEndDate(Date date) throws SessionNotActiveException, ControllerNotLoadedException{
        this.validateEnvironment();
        this.setEndDate(date);
    }

    public void setEstimateCost(Double cost) throws SessionNotActiveException, ControllerNotLoadedException{
        this.validateEnvironment();
        this.setEstimateCost(cost);
    }

    public void setTripDescription(String description) throws SessionNotActiveException, ControllerNotLoadedException{
        this.validateEnvironment();
        this.setTripDescription(description);
    }

    public void setOriginCity(String city) throws SessionNotActiveException, ControllerNotLoadedException{
        this.validateEnvironment();
        this.setOriginCity(city);
    }

    public void setEndCity(String city) throws SessionNotActiveException, ControllerNotLoadedException{
        this.validateEnvironment();
        this.setEndCity(city);
    }

    public void setTripStatus(TripStatus tripStatus) throws SessionNotActiveException, ControllerNotLoadedException{
        this.validateEnvironment();
        this.setTripStatus(tripStatus);
    }
}
