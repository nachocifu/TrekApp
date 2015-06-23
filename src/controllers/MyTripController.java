package controllers;

import java.util.Collection;
import java.util.Date;
import java.util.HashSet;

import domain.ControllerNotLoadedException;
import domain.Profile;
import domain.SessionNotActiveException;
import domain.TripStatus;
import repositoryMem.TripRepository;


/**
 * An object that can only be obtainable by the group admin (also the trip admin)
 * 
 *
 */
public class MyTripController extends TripController {

    public MyTripController(TripRepository tripRepo) {
        super(tripRepo);
    }

    /**
     * @param date set for the trip to begin
     * @throws SessionNotActiveException
     * @throws ControllerNotLoadedException
     */
    public void setStartDate(Date date) throws SessionNotActiveException, ControllerNotLoadedException{
        this.validateEnvironment();
        this.setStartDate(date);
        saveChanges();
    }

    /**
     * 
     * @param date date set for the trip to end
     * @throws SessionNotActiveException
     * @throws ControllerNotLoadedException
     */
    public void setEndDate(Date date) throws SessionNotActiveException, ControllerNotLoadedException{
        this.validateEnvironment();
        this.setEndDate(date);
        saveChanges();
    }

    /**
     * @param cost that will be set as the trips estimated cost
     * @throws SessionNotActiveException
     * @throws ControllerNotLoadedException
     */
    public void setEstimateCost(Double cost) throws SessionNotActiveException, ControllerNotLoadedException{
        this.validateEnvironment();
        this.setEstimateCost(cost);
        saveChanges();
    }

    /**
     * 
     * @param description that will be set as the trips description
     * @throws SessionNotActiveException
     * @throws ControllerNotLoadedException
     */
    public void setTripDescription(String description) throws SessionNotActiveException, ControllerNotLoadedException{
        this.validateEnvironment();
        this.setTripDescription(description);
        saveChanges();
    }

    /**
     * 
     * @param city that will be set as the origin city
     * @throws SessionNotActiveException
     * @throws ControllerNotLoadedException
     */
    public void setOriginCity(String city) throws SessionNotActiveException, ControllerNotLoadedException{
        this.validateEnvironment();
        this.setOriginCity(city);
        saveChanges();
    }

    /**
     * 
     * @param city that will be set as the ending city
     * @throws SessionNotActiveException
     * @throws ControllerNotLoadedException
     */
    public void setEndCity(String city) throws SessionNotActiveException, ControllerNotLoadedException{
        this.validateEnvironment();
        this.setEndCity(city);
        saveChanges();
    }

    /**
     * @param tripGroupController
     * @param newTripStatus that will be set
     * @throws SessionNotActiveException
     * @throws ControllerNotLoadedException
     */
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
    
    /**
     * 
     * @param newCost that will be added to the trips total cost
     * @throws SessionNotActiveException
     * @throws ControllerNotLoadedException
     */
    public void addNewCostToTrip(Double newCost) throws SessionNotActiveException, ControllerNotLoadedException{
    	this.validateEnvironment();
    	this.addNewCostToTrip(newCost);
    	saveChanges();
    }
    
    /**
     * deletes the trip
     * @throws SessionNotActiveException
     * @throws ControllerNotLoadedException
     */
    public void deleteTrip() throws SessionNotActiveException, ControllerNotLoadedException{
    	this.validateEnvironment();
    	getRepository().delete(this.obj);
        this.obj = null;
    }
}
