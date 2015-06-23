package repositorySQL;


import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

import domain.Profile;
import domain.Review;


/**
 * This class is used to easily persist Integer relationships to database as
 * an alternative to using native querys due to time and framework limitations.
 * @author nacho
 *
 */
@DatabaseTable
class ReviewRelationship {

    @DatabaseField( id = true )
    private Integer reviewId;

    @DatabaseField
    private Integer from;

    @DatabaseField
    private Integer to;

    protected ReviewRelationship(){

    }

    protected ReviewRelationship(Review rvw){
        this.reviewId = rvw.getId();
        this.from = rvw.getprofileOrigin().getUsrId();
        this.to = rvw.getprofileTarget().getUsrId();
    }

	public Integer getReviewId() {
		return reviewId;
	}

	public Integer getFrom() {
		return from;
	}

	public Integer getTo() {
		return to;
	}

}
