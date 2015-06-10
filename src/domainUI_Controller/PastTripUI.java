package domainUI_Controller;

import java.util.Collection;

import domain.Trip;
import services.TripService;
@Deprecated
public class PastTripUI extends AbstractDomainUI{

    private Trip trip;
    private TripService tripService;
    @Deprecated
    public PastTripUI(Trip trip){
        this.trip = trip;
        this.tripService = new TripService();
    }
    @Deprecated
    public Collection<ProfileUI> getIntegrants(){
        return this.tripService.getTripParticipantsUI(this.trip);
    }


}
