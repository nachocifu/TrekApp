package repositorySQL;


import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

import domain.Group;
import domain.Profile;


/**
 * This class is used to easily persist Integer relationships to database as
 * an alternative to using native querys due to time and framework limitations.
 * @author nacho
 *
 */
@DatabaseTable
class GroupProfileRelationship {

    @DatabaseField( generatedId = true )
    private Integer id;

    @DatabaseField
    private Integer group;

    @DatabaseField
    private Integer user;

    @DatabaseField
    private Boolean admin;

    @DatabaseField
    private String rel;

    protected GroupProfileRelationship(){

    }

    protected GroupProfileRelationship(Group group, Profile user, Boolean userIsAdmin, RelationshipEnum rel){
        this.user = user.getUsrId();
        this.group = group.getId();
        this.admin = userIsAdmin;
        this.rel = rel.toString();
    }

    public Integer getId() {
        return id;
    }

    public Integer getGroup() {
        return group;
    }

    public Integer getUser() {
        return user;
    }

    public Boolean getAdmin() {
        return admin;
    }

    protected String getRel() {
        return rel;
    }


}
