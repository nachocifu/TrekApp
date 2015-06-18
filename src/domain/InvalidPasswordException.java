package domain;

/**
 * Exception thrown when a password is requested but it is not valid, it doesnt coincide with the 
 * password set by the profile.
 */
@SuppressWarnings("serial")
public class InvalidPasswordException extends Exception {
	
	public InvalidPasswordException(String msg)
	{
		super(msg);
	}
	
	public InvalidPasswordException()
	{
		super();
	}

}
