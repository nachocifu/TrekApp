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
       return this.loadProfilesInside(obj, depth);
    }

    /**
     * Update the GROUP until the depth indicated
     * @param obj The goup
     * @param depth as to how inside to persist the group
     */
    public void update(Group obj, Integer depth){
        super.update(obj);
        this.persistObjectsInside(obj, depth);
    }


}
