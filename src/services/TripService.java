package services;

import java.util.Collection;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;

import domain.Group;
import domain.Trip;
import domain.UniversalString;
import repository.AbstractRepository;
import domain.Profile;
import domain.Trip;
import domain.UniversalString;
import domainUI_Controller.ProfileUI;
import domainUI_Controller.TripUI;



public class TripService implements ServiceInterface<Trip> {


    private AbstractRepository<Trip> repo;

    /**
     * @param user repository
     */
    public TripService(AbstractRepository<Trip> repo) {
        this.repo = repo;
    }

    public TripService() {
    }

    /**
     * Creates a trip
     * @param startDate
     * @param endDate
     * @param groupId
     * @param tripId
     * @param estimateCost
     * @param tripDescription
     * @param originCity
     * @param endCity
     */

    public void createTrip(Date startDate, Date endDate, Integer groupId, Integer tripId, Integer estimateCost, UniversalString tripDescription, String originCity, String endCity){
        Trip trip= new Trip(startDate, endDate, groupId, tripId, estimateCost, tripDescription, originCity, endCity);
        repo.add(trip);
    }

    /**
     * Gets the tripUI for the interface to show information
     * @param tripId
     * @param userId
     * @return
     */
    public TripUI getTripUI(Trip trip){
        return new TripUI(trip);
    }

    public Collection<PastTripUI> getProfilePastTripsUI(Profile profile){
        Collection<PastTripUI> pastTrips = new HashSet<PastTripUI>();
        for (Trip pastTrip : profile.getTrips()) {
            pastTrips.add(new PastTripUI(pastTrip));
        }
        return pastTrips;
    }

    public Collection<ProfileUI> getTripParticipantsUI(Trip trip){
        Collection<ProfileUI> participantsUI = new HashSet<ProfileUI>();
        Collection<Profile> participants = this.repo.getById(trip.getGroupId()).getMemebers();
        for (Profile profile : participants) {
            participantsUI.add(new ProfileUI(profile));
        }
        return participantsUI;
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