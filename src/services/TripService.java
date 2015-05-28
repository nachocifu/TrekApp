package services;

import java.util.Date;
import java.util.HashMap;

import repository.AbstractRepository;
import domain.Profile;
import domain.Trip;
import domain.UniversalString;



public class TripService implements ServiceInterface<Trip> {
	
	private AbstractRepository<Trip> repo;
	
	public TripService(AbstractRepository<Trip> repo) {
		//super(repo);
		// TODO Auto-generated constructor stub
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
	 * Gets the tripStatus
	 * @param trip_id
	 * @return a String containing the trip Status
	 */
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
	public void delete(Integer id) {
		// TODO Auto-generated method stub
		
	}
	
	
	
	
}