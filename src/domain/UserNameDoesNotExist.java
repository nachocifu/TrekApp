package domain;

public class UserNameDoesNotExist extends Exception {

	public UserNameDoesNotExist(String msg)
	{
		super(msg);
	}
	
	public UserNameDoesNotExist()
	{
		super();
	}
}
