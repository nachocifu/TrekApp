package repositorySQL;


import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

import domain.Group;
import domain.Profile;
import domain.Review;
import domain.Trip;


/**
 * This class is used to easily persist Integer relationships to database as
 * an alternative to using native querys due to time and framework limitations.
 * @author nacho
 *
 */
@DatabaseTable
class TripGroupRelationship {

    @DatabaseField( generatedId = true )
    private Integer id;

    @DatabaseField
    private Integer group;

    @DatabaseField
    private Integer trip;

    protected TripGroupRelationship(){

    }

    protected TripGroupRelationship(Group group, Trip trip){
        this.trip = trip.getId();
        this.group = group.getId();
    }

    protected Integer getId() {
        return id;
    }

    protected Integer getGroup() {
        return group;
    }

    protected Integer getTrip() {
        return trip;
    }
}