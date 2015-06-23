package repository;

import java.util.Date;
import java.util.List;

import domain.Profile;
import domain.Trip;
import domain.TripStatus;

public class TripRepository extends AbstractRepository<Trip> {

    public TripRepository(String pathToDataBase, Class<Trip> reposClass) {
    }

    /**
     * Method to search the users
     * @param searchTxt string to search
     * @return response list of Profiles
     */
    public List<Trip> searchBy(String txt, Date StartDate, Date endDate, String originCity, String endCity, TripStatus status, Profile currentUser ) {
		return null;
       
    }

}
