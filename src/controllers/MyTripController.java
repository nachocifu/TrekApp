package controllers;

import java.util.Date;

import domain.ControllerNotLoadedException;
import domain.SessionNotActiveException;
import domain.TripStatus;

import repository.TripRepository;


/**
 * An object that can only be obtainable by the group admin (also the trip admin)
 * 
 *
 */
public class MyTripController extends TripController {

    public MyTripController(TripRepository tripRepo) {
        super(tripRepo);
    }

    public void setStartDate(Date date) throws SessionNotActiveException, ControllerNotLoadedException{
        this.validateEnvironment();
        this.setStartDate(date);
        saveChanges();
    }

    public void setEndDate(Date date) throws SessionNotActiveException, ControllerNotLoadedException{
        this.validateEnvironment();
        this.setEndDate(date);
        saveChanges();
    }

    public void setEstimateCost(Double cost) throws SessionNotActiveException, ControllerNotLoadedException{
        this.validateEnvironment();
        this.setEstimateCost(cost);
        saveChanges();
    }

    public void setTripDescription(String description) throws SessionNotActiveException, ControllerNotLoadedException{
        this.validateEnvironment();
        this.setTripDescription(description);
        saveChanges();
    }

    public void setOriginCity(String city) throws SessionNotActiveException, ControllerNotLoadedException{
        this.validateEnvironment();
        this.setOriginCity(city);
        saveChanges();
    }

    public void setEndCity(String city) throws SessionNotActiveException, ControllerNotLoadedException{
        this.validateEnvironment();
        this.setEndCity(city);
        saveChanges();
    }

    public void setTripStatus(TripStatus tripStatus) throws SessionNotActiveException, ControllerNotLoadedException{
        this.validateEnvironment();
        this.setTripStatus(tripStatus);
        saveChanges();
    }
    
    public void addNewCostToTrip(Double newCost) throws SessionNotActiveException, ControllerNotLoadedException{
    	this.validateEnvironment();
    	this.addNewCostToTrip(newCost);
    	saveChanges();
    }
    
    public void deleteTrip() throws SessionNotActiveException, ControllerNotLoadedException{
    	this.validateEnvironment();
    	getRepository().delete(this.obj);
        this.obj = null;
    }
}
