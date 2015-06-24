package repositorySQL;


import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

import domain.Group;
import domain.Profile;
import domain.Review;


/**
 * This class is used to easily persist Integer relationships to database as
 * an alternative to using native querys due to time and framework limitations.
 * @author nacho
 *
 */
@DatabaseTable
class GroupReviewsRelationship {

    @DatabaseField( generatedId = true )
    private Integer id;

    @DatabaseField
    private Integer fromUser;

    @DatabaseField
    private Integer toUser;

    @DatabaseField
    private Integer group;

    protected GroupReviewsRelationship(){

    }

    protected GroupReviewsRelationship(Profile to, Profile from, Group group){
        this.fromUser = from.getUsrId();
        this.toUser = to.getUsrId();
        this.group = group.getId();
    }

    public Integer getId() {
        return id;
    }

    public Integer getGroup(){
        return group;
    }

    public Integer getFrom() {
        return fromUser;
    }

    public Integer getTo() {
        return toUser;
    }

}
