package repositorySQL;


import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

import domain.Profile;
import domain.Trip;


/**
 * This class is used to easily persist Integer relationships to database as
 * an alternative to using native querys due to time and framework limitations.
 * @author nacho
 *
 */
@DatabaseTable
class TripProfileRelationship {

    @DatabaseField( generatedId = true )
    private Integer id;

    @DatabaseField
    private Integer trip;

    @DatabaseField
    private Integer user;

    protected TripProfileRelationship(){

    }

    protected TripProfileRelationship(Trip trip, Profile user){
        this.user = user.getUsrId();
        this.trip = trip.getId();
    }

	public Integer getId() {
		return id;
	}

	public Integer getTrip() {
		return trip;
	}

	public Integer getUser() {
		return user;
	}

}
