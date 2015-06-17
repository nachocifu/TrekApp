package domain;

@SuppressWarnings("serial")
public class InvalidPermissionException extends Exception {
	public InvalidPermissionException(String msg)
	{
		super(msg);
	}
	
	public InvalidPermissionException()
	{
		super();
	}

}
