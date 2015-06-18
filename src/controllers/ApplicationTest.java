package controllers;

import static org.junit.Assert.*;

import java.rmi.ServerException;
import java.util.Date;

import org.junit.Test;

import domain.SessionNotActiveException;
import domain.UserNameAlreadyExistsException;

public class ApplicationTest {

	//Application apl = Application.getInstance(); 
	
	@SuppressWarnings("deprecation")
	@Test(expected=UserNameAlreadyExistsException.class)
	public void testUserNameAlreadyExists() throws ServerException, UserNameAlreadyExistsException {
		Application apl = Application.getInstance(); 
		apl.registerUser("username", "name", "surname", new Date(1993,12,18), true, "123456", "Buenos Aires", "email@itba.edu.ar" ); 
		apl.registerUser("username", "name", "surname", new Date(1993,12,18), true, "123456", "Buenos Aires", "email@itba.edu.ar" );
	}
	/*
	/*@SuppressWarnings("deprecation")
	@Test
	public void testValidatePassword() throws ServerException, UserNameAlreadyExistsException{
		//Application apl = Application.getInstance(); 
		apl.registerUser("username", "name", "surname", new Date(1993,12,18), true, "123456", "Buenos Aires", "email@itba.edu.ar" ); 
		assertTrue(apl.validate("username", "123456"));
		//assertFalse(apl.validate("username", "falsePassword"));
	}*/
	
	/*@Test(expected=SessionNotActiveException.class)
	public void testSessionNotActive() throws SessionNotActiveException
	{
		apl.getCurrentProfileController();
	}
	*/
}
