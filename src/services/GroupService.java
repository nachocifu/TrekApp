package services;

import repository.AbstractRepository;
import domain.Group;
import domain.Trip;

public class GroupService {

	private AbstractRepository<Group> repo;
	
	
	//me conviene agregar un hashset en profile con los group ids de los cuales es admin?
	/**
	 * New group will be added to group repository
	 * @param groupName
	 * @param groupId
	 * @param adminUserId
	 */
	public void newGroup(String groupName, String groupId, String adminUserId){
		Group group= new Group(groupName, groupId, adminUserId);
		this.repo.add(group);
	}
	
	/**
	 * @param groupId of the group that will be deleted from the respository
	 */
	public void deleteGroup(String groupId){
		this.repo.remove(groupId);
	}
	
	
	//es orientado a objetos tener un metodo en groupService para agregar a un miembro
	//y un metodo en profileService para agregar a un objeto o deberia todo ne uno?
	//como deberia hacer para acceder al repo de usuarios si es que lo hago de esta forma?
	public void addMember(String groupId, String userId){
		//this.repo.getById(groupId);
	}
	
	/**
	 * @param groupId where the message will be posted
	 * @param usrId that will post a message in the group
	 * @param msgId that will be posted
	 */
	public void post(String groupId, String usrId, String msgId){
		Group group= this.repo.getById(groupId);
		group.addPost(usrId, msgId);
		this.repo.update(group);
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

