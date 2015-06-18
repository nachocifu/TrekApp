package domain;

@SuppressWarnings("serial")
public class SessionNotActiveException extends Exception {

    public SessionNotActiveException(String msg)
    {
        super(msg);
    }

    public SessionNotActiveException()
    {
        super();
    }
}
