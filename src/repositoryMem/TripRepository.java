package repositoryMem;

import java.util.Collection;
import java.util.Date;
import java.util.HashSet;

import domain.Trip;

public class TripRepository extends AbstractRepository<Trip> {

    public TripRepository(String pathToDataBase, Class<Trip> reposClass) {
    }

    public Collection<Trip> getTripBy(Date startDate, Date endDate, String startCity, String endCity, String description ){
    	Collection<Trip> result = new HashSet<Trip>();
    	for (Trip trip : this.repository) {
			if(trip.getStartDate().before(startDate)
					&& trip.getEndDate().after(endDate)
					&& trip.getOriginCity().trim().contains(startCity.trim())
					&& trip.getEndCity().trim().contains(endCity.trim())
					&& trip.getTripDescription().trim().contains(description.trim())){
				result.add(trip);
			}
		}
    	return result;
    	
    }

}
