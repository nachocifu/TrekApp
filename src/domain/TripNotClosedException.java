package domain;

/**
 * Exception that is being thrown when a trip is being used as if it has already been finished but in fact
 * it is still OPEN or IN-PROGRESS
 */
@SuppressWarnings("serial")
public class TripNotClosedException extends Exception{
	public TripNotClosedException(String msg)
	{
		super(msg);
	}
	
	public TripNotClosedException()
	{
		super();
	}
}
