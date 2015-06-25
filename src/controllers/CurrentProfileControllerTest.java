package controllers;

import static org.junit.Assert.*;

import java.rmi.ServerException;
import java.util.Date;

import org.junit.Test;

import domain.ControllerNotLoadedException;
import domain.SessionNotActiveException;
import domain.UserNameAlreadyExistsException;

public class CurrentProfileControllerTest {

	@SuppressWarnings("deprecation")
	@Test
	public void test() throws SessionNotActiveException, ControllerNotLoadedException, ServerException, UserNameAlreadyExistsException{
		Application app= Application.getInstance();
		Session session = Session.getInstance();
		app.registerUser("naty", "natalia", "navas", new Date(1994, 3, 13), true, "agua", "lkj", "lkj@gmail.com");
		session.logIn("naty", "agua");
		CurrentProfileController profCont= app.getCurrentProfileController();
		assertTrue(profCont.getUsername().equals("naty"));
		assertTrue(profCont.getUserName().equals("natalia"));
		assertTrue(profCont.getSex().equals(true));
		assertTrue(profCont.getBirthday().equals(new Date(1994,3,13)));
		assertTrue(profCont.getCity().equals("lkj"));
	}
	

}
