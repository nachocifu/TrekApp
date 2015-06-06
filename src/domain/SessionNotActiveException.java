package domain;

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
