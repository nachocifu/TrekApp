package services;

import repository.AbstractRepository;
import repository.GroupRepository;
import domain.Group;
import domain.Profile;
import domain.Trip;

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



}

