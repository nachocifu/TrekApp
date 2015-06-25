package controllers;

import static org.junit.Assert.*;

import java.rmi.ServerException;
import java.util.Date;

import org.junit.Test;

import domain.ControllerNotLoadedException;
import domain.SessionNotActiveException;
import domain.UserNameAlreadyExistsException;

public class CurrentProfileControllerTest {

	@Test
	public void test() throws SessionNotActiveException, ControllerNotLoadedException{
		Application app= Application.getInstance();
		Session session = Session.getInstance();
		try {
			app.registerUser("naty", "natalia", "navas", new Date(1994, 3, 13), true, "agua", "lkj", "lkj@gmail.com");
		} catch (ServerException | UserNameAlreadyExistsException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println("SE CREO A NATY");
		session.logIn("naty", "agua");
		CurrentProfileController profCont= app.getCurrentProfileController();
		assertTrue(profCont.getUsername().equals("naty"));
		assertTrue(profCont.getUserName().equals("natalia"));
		assertTrue(profCont.getSex().equals(true));
		assertTrue(profCont.getBirthday().equals(new Date(1994,3,13)));
		//assertTrue(profCont.getRating().equals(null));
		assertTrue(profCont.getCity().equals("lkj"));
		//assertTrue(profCont.getFriends().equals(null));
		//assertTrue(profCont.getBlockUsers().equals(null));
		//assertTrue(profCont.getFriendRequests().equals(null));
		//assertTrue(profCont.getGroups().equals(null));
		//assertTrue(profCont.getTrips().equals(null));
	}

}
