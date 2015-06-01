package src.domainUI_Controller;

import java.util.Date;

import src.domain.Trip;

import src.domain.UniversalString;

/**
 * 
 * An intermediate for the FrontEnd to use
 *
 */
public class TripUI extends AbstractDomainUI {
	
	private Trip trip;
	
	public TripUI(Trip trip){
		this.trip = trip;
		}

	public Date getStart_date() {
		return this.trip.getstartDate();
	}

	public Date getEnd_date() {
		return this.trip.getendDate();
	}

	public Integer getEstimate_cost() {
		return this.trip.getestimateCost();
	}

	public UniversalString getTrip_description() {
		return this.trip.gettripDescription();
	}

	public String getOrigin_city() {
		return this.trip.getoriginCity();
	}

	public String getEnd_city() {
		return this.trip.getendCity();
	}

	public Trip.tripStatus getTrip_status() {
		return this.trip.getTripStatus();
	}
	
	public Integer getTripId(){
		return this.trip.getId();
	}
	
	
	
}
