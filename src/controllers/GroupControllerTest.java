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
import domain.UserNameAlreadyExistsException;

public class GroupControllerTest {

	@Test
	public void groupControllerTest() throws ServerException, GroupNameAlreadyExistsException, SessionNotActiveException, ControllerNotLoadedException, UserNameAlreadyExistsException, InvalidPermissionException{
		Application app= Application.getInstance();
		Session session = Session.getInstance();
		
		
		app.registerUser("naty", "natalia", "navas", new Date(1994, 3, 13), true, "agua", "lkj", "lkj@gmail.com");
		
		app.registerUser("username", "nombre", "apellido", new Date(2000,12,12), true, "12345","buenos aires", "nati@email.com");
		
        TripRepository tripRepo = new TripRepository(null, null);
        GroupRepository groupRepo = new GroupRepository(null, null);
        ProfileRepository userRepo = new ProfileRepository(null, null);
        CollectionAndSearchController searchRepo = new CollectionAndSearchController(userRepo, groupRepo, tripRepo);
        session.logIn("naty", "agua");
		ProfileController user=searchRepo.getProfileControllerByUsername("username");
		System.out.println("SE CREO A NATY");
		
		
		
		CurrentProfileController profCont= app.getCurrentProfileController();
	//	MyGroupController groupController= app.registerGroup("grupo", profCont, 3, 33, "Buenos Aires");
		//groupController.addMember(user);
		
		
	}
	
	}

