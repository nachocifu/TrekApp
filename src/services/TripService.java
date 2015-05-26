package services;

import java.util.Date;

import domain.Trip;
import domain.UniversalString;



public class TripService extends AbstractService<Trip> {
	
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
	public void createTrip(Date start_date, Date end_date, Integer group_id, Integer trip_id, Integer estimate_cost, UniversalString trip_description, String origin_city, String end_city){
		//Codigo entre repository y service
	}
	
	/**
	 * Gets the tripStatus
	 * @param trip_id
	 * @return
	 */
	public String getTripStatus(Integer trip_id){
		//Buscar Trip con id
		return repoTrip.getTrip_status().toString();
	}
	
	/**
	 * Sets the tripStatus but before validates if the user is the admin of that trip
	 * @param trip_id
	 * @param user_id
	 * @return
	 */
	public void setTripStatus(Integer trip_id, String user_id ){
		//Buscar Trip con id
		
	}
	
	
	
	
}
