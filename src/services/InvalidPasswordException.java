package services;

//me conviene tener un paquete con exceptions ? 
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
