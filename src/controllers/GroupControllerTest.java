package controllers;

import static org.junit.Assert.*;

import java.rmi.ServerException;
import java.util.Date;

import org.junit.Test;

import repositoryMem.GroupRepository;
import repositoryMem.ProfileRepository;
import repositoryMem.TripRepository;
import domain.ControllerNotLoadedException;
import domain.GroupNameAlreadyExistsException;
import domain.InvalidPermissionException;
import domain.Profile;
import domain.SessionNotActiveException;
import domain.TripNotClosedException;
import domain.TripStatus;
import domain.UserNameAlreadyExistsException;

public class GroupControllerTest {

	
	
	@Test(expected=TripNotClosedException.class)
	public void groupControllerTest() throws ServerException, GroupNameAlreadyExistsException, SessionNotActiveException, ControllerNotLoadedException, UserNameAlreadyExistsException, InvalidPermissionException, TripNotClosedException{
		
		Application app= Application.getInstance();
		Session session = Session.getInstance();
        
		/*register the members that will be a part of the group*/
		app.registerUser("naty", "natalia", "navas", new Date(1994, 3, 13), true, "agua", "lkj", "lkj@gmail.com");
		app.registerUser("username", "nombre", "apellido", new Date(2000,12,12), true, "12345","buenos aires", "nati@email.com");
		app.registerUser("username2", "nombre2", "apellido2", new Date(1998, 11,11), true, "123123", "hola", "hola@email.com");
		
        session.logIn("naty", "agua");
        CollectionAndSearchController searchRepo = app.getCollectionController();

        /*Get all the profile controllers of the members that will be added to the group*/
		CurrentProfileController profile1= app.getCurrentProfileController();
		ProfileController profile2=searchRepo.getProfileControllerByUsername("username");
		ProfileController profile3=searchRepo.getProfileControllerByUsername("username2");
		
		/*Add members to the group*/
		MyGroupController groupController= app.registerGroup("grupo", profile1, 3, 33, "Buenos Aires");
		groupController.addMember(profile2);
		groupController.addMember(profile3);
		
		/*various tests*/
		assertTrue(groupController.getGroupName().equals("grupo"));
		MyTripController trip= app.registerTrip(new Date(2011,11,11), new Date(2012,1,1), 213.11, "hola", "buenos aires", "cordoba");
		groupController.addGroupTrip(trip);
		groupController.sendReviewToAMember(profile2, "hola", 4);
		
		
	}
	
	
	
	@Test(expected=TripNotClosedException.class)
	public void tripNotClosedTest() throws ServerException, UserNameAlreadyExistsException, SessionNotActiveException, GroupNameAlreadyExistsException, ControllerNotLoadedException, InvalidPermissionException, TripNotClosedException{
		Application app= Application.getInstance();
		Session session = Session.getInstance();
		
		app.registerUser("naty1", "natalia", "navas", new Date(1994, 3, 13), true, "agua", "lkj", "lkj@gmail.com");
		app.registerUser("username1", "nombre", "apellido", new Date(2000,12,12), true, "12345","buenos aires", "nati@email.com");
		session.logIn("naty1", "agua");
		
		CurrentProfileController profile1= app.getCurrentProfileController();

        CollectionAndSearchController searchRepo = app.getCollectionController();
		ProfileController profile2=searchRepo.getProfileControllerByUsername("username1");
		MyGroupController groupController= app.registerGroup("grupo", profile1, 3, 33, "Buenos Aires");
		
		MyTripController trip= app.registerTrip(new Date(2011,11,11), new Date(2012,1,1), 213.11, "hola", "buenos aires", "cordoba");
		groupController.addGroupTrip(trip);

		groupController.sendReviewToAMember(profile2, "hola", 4);
	}
	
	@Test
	public void deleteMemberTest(){
		
	}
}
