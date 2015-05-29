package services;

import repository.AbstractRepository;
import repository.GroupRepository;
import domain.Group;
import domain.Trip;

public class GroupService {

    private GroupRepository repo;


    //me conviene agregar un hashset en profile con los group ids de los cuales es admin?
    /**
     * New group will be added to group repository
     * @param groupName
     * @param adminUserId
     */
    public void newGroup(String groupName, String groupId, String adminUserId){
        Group group= new Group(groupName, adminUserId);
        this.repo.add(group);
    }

    /**
     * @param groupId of the group that will be deleted from the respository
     */
    public void deleteGroup(String groupId){
        this.repo.delete(groupId);
    }

    public void addMember(String groupId, String userId){
        //this.repo.getById(groupId);
    }

    /**
     * @param groupId of the group where the post will be deleted
     * @param msgId of the message that will be deleted
     */
    public void deletePost(String groupId, String msgId){
        Group group=this.repo.getById(groupId);
        group.deletePost(msgId);
        this.repo.update(group);
    }

}

