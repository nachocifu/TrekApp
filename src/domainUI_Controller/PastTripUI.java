package src.domainUI_Controller;

import java.util.Collection;

import src.domain.Trip;
import src.services.TripService;

public class PastTripUI extends AbstractDomainUI{

	private Trip trip;
	private TripService tripService;
	
	public PastTripUI(Trip trip){
		this.trip = trip;
		this.tripService = new TripService();
	}
	
	public Collection<ProfileUI> getIntegrants(){
		return this.tripService.getTripParticipantsUI(this.trip);
	}
	
	
}
