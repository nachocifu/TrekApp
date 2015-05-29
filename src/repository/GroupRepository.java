/**
 *
 */
package repository;

import java.util.List;

import domain.Group;

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
    public List<Group> searchBy(String searchTxt, Group currentUser) {
        // TODO Auto-generated method stub
        return null;
    }


}
