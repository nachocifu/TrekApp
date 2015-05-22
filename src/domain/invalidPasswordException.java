package domain;

public class invalidPasswordException extends Exception {
	
	public invalidPasswordException(String msg)
	{
		super(msg);
	}
	
	public invalidPasswordException()
	{
		super();
	}

}
