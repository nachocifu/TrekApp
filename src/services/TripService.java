package src.services;

import java.util.Date;
import java.util.HashMap;

import src.domain.Group;
import src.domain.Trip;
import src.domain.UniversalString;
import src.domainUI.TripUI;
import src.repository.AbstractRepository;
import repository.AbstractRepository;
import domain.Profile;
import domain.Trip;
import domain.UniversalString;



public class TripService implements ServiceInterface<Trip> {
	

	private AbstractRepository<Trip> repo;
	
	/**
	 * @param user repository
	 */
	public TripService(AbstractRepository<Trip> repo) {
		this.repo = repo;
	}

	/**
	 * Creates a trip
	 * @param start_date
	 * @param end_date
	 * @param group_id
	 * @param trip_id
	 * @param estimate_cost
	 * @param trip_description
	 * @param origin_city
	 * @param end_city
	 */

	public void createTrip(Date startDate, Date endDate, String groupId, String tripId, Integer estimateCost, UniversalString tripDescription, String originCity, String endCity){
		Trip trip= new Trip(startDate, endDate, groupId, tripId, estimateCost, tripDescription, originCity, endCity);
		repo.add(trip);
	}
	
	/**
	 * Gets the tripUI for the interface to show information
	 * @param trip_id
<<<<<<< HEAD
	 * @param user_id
	 * @return
	 */
	public TripUI getTripUI(Integer trip_id, String user_id){
		Trip trip = this.repo.getById(trip_id.toString());
		Group group = this.repo.getById(trip.getGroup_id().toString());
		TripUI tripUI = new TripUI(trip.getStart_date(), trip.getEnd_date(), trip.getEstimate_cost(), trip.getTrip_description(), trip.getOrigin_city(), trip.getEnd_city());
		return tripUI;

	public String getTripStatus(String tripId){
		Trip trip= repo.getById(tripId);
		return trip.getTripStatus().toString();
	}
	
	/**
	 * Sets the tripStatus but before validates if the user is the admin of that trip
	 * @param trip_id
	 * @param user_id
	 * @return
	 */
	public void setTripStatus(Integer trip_id, String user_id ){
		
	}

	@Override
	public HashMap<String, Object> getById(Integer id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void delete(Integer trip_Id) {
		// TODO Auto-generated method stub
		repo.remove(trip_Id.toString());
	}
	
	
	
	
}