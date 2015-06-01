package src.services;

import java.util.Collection;
import java.util.HashSet;

import src.repository.AbstractRepository;
import src.repository.GroupRepository;
import src.domainUI_Controller.GroupUI;
import src.domainUI_Controller.TripUI;
import src.domain.Group;
import src.domain.Profile;
import src.domain.Trip;

public class GroupService {

    private GroupRepository repo;
    
    /**
     * New group will be added to group repository
     * @param groupName
     * @param adminUserId
     */
    public void newGroup(String groupName, Profile admin){
        Group group= new Group(groupName, admin);
        this.repo.add(group);
    }

    /**
     * @param groupId of the group that will be deleted from the respository
     */
    public void deleteGroup(String groupId){
        this.repo.delete(groupId);
    }
    
    public Collection<GroupUI> getProfileGroupsUI(Profile profile){
	    Collection<GroupUI> groups = new HashSet<GroupUI>();
		for (Group group : profile.getGroups()) {
			groups.add(new GroupUI(group));
		}
		return groups;
	}



}

