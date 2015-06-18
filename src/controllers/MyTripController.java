package controllers;

import java.util.Collection;
import java.util.Date;
import java.util.HashSet;

import domain.ControllerNotLoadedException;
import domain.Profile;
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

    public void setTripStatus(MyGroupController tripGroupController, TripStatus newTripStatus) throws SessionNotActiveException, ControllerNotLoadedException{
        this.validateEnvironment();
        this.validateController(tripGroupController);
        if(newTripStatus == TripStatus.CLOSED && this.obj.getTripStatus() != TripStatus.CLOSED){
        	tripGroupController.getObject().updateMissingReviews();
        	Collection<Profile> groupMembers = tripGroupController.getObject().getMembers();
        	for (Profile profile : groupMembers) {
				profile.getTrips().add(this.obj);
			}
        	tripGroupController.saveChanges();
        }
        this.obj.setTripStatus(newTripStatus);
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
