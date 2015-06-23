/**
 *
 */
package repositorySQL;

import java.util.List;

import domain.Group;
import domain.Profile;

/**
 * The connection to the file system regarding groups and their persistence.
 */
public class GroupRepository extends AbstractRepository<Group> {

    public GroupRepository(String pathToDataBase, Class<Group> reposClass) {
        super(pathToDataBase, reposClass);
        // TODO Auto-generated constructor stub
    }


    /**
     * Search groups by text.
     * @param searchTxt the text to search
     * @param currentUser to detect for blocked lists
     * @return response the list of groups to return
     */
    @Override
    public List<Group> searchBy(String searchTxt, Profile currentUser) {
        // TODO Auto-generated method stub
        return null;
    }

    /**
     * Query for group by group ID
     * @param id The user ID
     * @param depth as to how inside to load the group
     * @return response The group or null if no results
     */
    public Group getById(Integer groupId, Integer depth){
       Group obj = super.getById(groupId);
       System.err.println("En este momento se deberian levantar los objetos y colocar adentro del group con id: " + groupId);
       obj = this.loadObjectsInside(obj, depth);
       return obj;
    }

    /**
     * Update the GROUP until the depth indicated
     * @param obj The goup
     * @param depth as to how inside to persist the group
     */
    public void update(Group obj, Integer depth){
        super.update(obj);
        System.err.println("En este momento se deberian persistir los objetos adentro del group con id: " + obj.getId());
        //this.persistObjectsInside(obj, depth);
    }

    private Group loadObjectsInside(Group obj, Integer depth) {
        // TODO Auto-generated method stub
        return null;
    }


}
