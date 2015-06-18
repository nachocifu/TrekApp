package repository;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

import domain.Profile;


/**
 * This class is used to easily persist Integer relationships to database as
 * an alternative to using native querys due to time and framework limitations.
 * @author nacho
 *
 */
@DatabaseTable
class ProfileRelationship {

    @DatabaseField
    private Integer thisUser;

    @DatabaseField
    private Integer otherUser;

    @DatabaseField
    private String relation;

    protected ProfileRelationship(Profile thisUser, Profile otherUser, Relationship relation){
        this.thisUser = thisUser.getUsrId();
        this.otherUser = otherUser.getUsrId();
        this.relation = relation.getStatus();
    }

    protected Integer getThisUser() {
        return thisUser;
    }

    protected Integer getOtherUser() {
        return otherUser;
    }

    protected String getRelation() {
        return relation;
    }
}
