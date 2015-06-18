package domain;

@SuppressWarnings("serial")
public class UserNameAlreadyExistsException extends Exception {

    public UserNameAlreadyExistsException(String msg)
    {
        super(msg);
    }

    public UserNameAlreadyExistsException()
    {
        super();
    }

}
