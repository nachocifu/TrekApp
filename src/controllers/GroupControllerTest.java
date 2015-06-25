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
import domain.TripStatus;
import domain.UserNameAlreadyExistsException;

public class GroupControllerTest {

	
	
	@Test
	public void groupControllerTest() throws ServerException, GroupNameAlreadyExistsException, SessionNotActiveException, ControllerNotLoadedException, UserNameAlreadyExistsException, InvalidPermissionException{
		
		Application app= Application.getInstance();
		Session session = Session.getInstance();
		
        TripRepository tripRepo = new TripRepository(null, null);
        GroupRepository groupRepo = new GroupRepository(null, null);
        ProfileRepository userRepo = new ProfileRepository(null, null);
        CollectionAndSearchController searchRepo = new CollectionAndSearchController(userRepo, groupRepo, tripRepo);
        
		app.registerUser("naty", "natalia", "navas", new Date(1994, 3, 13), true, "agua", "lkj", "lkj@gmail.com");
		app.registerUser("username", "nombre", "apellido", new Date(2000,12,12), true, "12345","buenos aires", "nati@email.com");
		app.registerUser("username2", "nombre2", "apellido2", new Date(1998, 11,11), true, "123123", "hola", "hola@email.com");
		
        session.logIn("naty", "agua");

		CurrentProfileController profile1= app.getCurrentProfileController();
		//ProfileController profile2=searchRepo.getProfileControllerByUsername("username");
	//	ProfileController profile3=searchRepo.getProfileControllerByUsername("username2");
		System.out.println("SE CREO A NATY y al perfil dos");
		
		MyGroupController groupController= app.registerGroup("grupo", profile1, 3, 33, "Buenos Aires");
		//groupController.addMember(profile2);
		//groupController.addMember(profile3);
		
	}
	
	@Test(expected=ControllerNotLoadedException.class)
	public void testTripStatus1() throws ServerException, UserNameAlreadyExistsException, SessionNotActiveException, GroupNameAlreadyExistsException, ControllerNotLoadedException{

		Application app= Application.getInstance();
		Session session = Session.getInstance();
		app.registerUser("naty2", "natalia", "navas", new Date(1994, 3, 13), true, "agua", "lkj", "lkj@gmail.com");
		session.logIn("naty2", "agua");
		CurrentProfileController profile1= app.getCurrentProfileController();
		MyGroupController groupController= app.registerGroup("grupo", profile1, 3, 33, "Buenos Aires");
		groupController.getTripStatus();	
	}
}
