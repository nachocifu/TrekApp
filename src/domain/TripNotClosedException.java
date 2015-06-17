package domain;

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
