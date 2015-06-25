package repositoryMem;

import java.util.Collection;
import java.util.Date;
import java.util.HashSet;

import domain.Group;
import domain.InvalidPermissionException;
import domain.Profile;
import domain.Trip;


/**
 * The connection to the file system regarding groups and their persistence.
 */
public class GroupRepository extends AbstractRepository<Group> {


    @SuppressWarnings("deprecation")
	public GroupRepository() {
        add(new Group("grupo1", new Profile("naty2", "Ignacio", "Cifu", new Date(7, 5, 1994), true, "agua", "Baires", "naty.navas2@gmail.com"), 5, 3, "baires"));
        add(new Group("grupo1", new Profile("naty2", "Ignacio", "Cifu", new Date(7, 5, 1994), true, "agua", "Baires", "naty.navas2@gmail.com"), 5, 3, "baires"));
        add(new Group("grupo1", new Profile("naty2", "Ignacio", "Cifu", new Date(7, 5, 1994), true, "agua", "Baires", "naty.navas2@gmail.com"), 5, 3, "baires"));
        add(new Group("grupo1", new Profile("naty5", "Ignacio", "Cifu", new Date(7, 5, 1994), true, "agua", "Baires", "naty.navas2@gmail.com"), 5, 3, "baires"));
        Group g = new Group("grupo1", new Profile("naty", "Ignacio", "Cifu", new Date(7, 5, 1994), true, "agua", "Baires", "naty.navas2@gmail.com"), 5, 3, "baires");
        try {
            g.addGroupTrip(new Trip(new Date(7, 5, 2020), new Date(7, 5, 2023), 500000.0, "hola soy jesus", "buenos aires", "cordoba"));
        } catch (InvalidPermissionException e) {
            e.printStackTrace();
        }

        add(g);
    }

    public Collection<Group> getGroupsWithTripsBy(Date startDate, Date endDate, String startCity, String endCity, String description ){
        Collection<Group> results = new HashSet<Group>();
        for (Group group : this.repository) {
            if( group.getGroupTrip() != null &&
                        (
                        ((startDate == null)?
                                false : group.getGroupTrip().getStartDate().equals(startDate)) ||
                        ((endDate == null)?
                                false : group.getGroupTrip().getEndDate().equals(endDate)) ||
                        ((startCity == null?
                                false : (startCity.trim().isEmpty())? false : group.getGroupTrip().getOriginCity().toLowerCase().contains(startCity.trim().toLowerCase()))) ||
                        ((endCity == null?
                                false : (endCity.trim().isEmpty())? false : group.getGroupTrip().getEndCity().toLowerCase().contains(endCity.trim().toLowerCase()))) ||
                        ((description == null)?
                                false: ((description.trim().isEmpty())? false : group.getGroupTrip().getTripDescription().toLowerCase().contains(description.trim().toLowerCase())))
                        )
                    )
                results.add(group);
        }
        return results;

    }

}
