package controllers;

import static org.junit.Assert.*;

import java.rmi.ServerException;
import java.util.Date;

import org.junit.Test;

import domain.UserNameAlreadyExistsException;

public class SessionTest {

	@SuppressWarnings("deprecation")
	@Test
	public void test() throws ServerException, UserNameAlreadyExistsException {
		
		Application apl = Application.getInstance();
		apl.registerUser("username", "name", "surname", new Date(1993,12,18), true, "123456", "Buenos Aires", "email@itba.edu.ar" ); 
		Session session=Session.getInstance();
		assertFalse(session.logIn("username", "invalidpassword"));
		assertTrue(session.logIn("username", "123456"));
		assertTrue(session.isActive());
		assertTrue(session.getUserName().equals("username"));
		
	}

}
