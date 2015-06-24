package domain;

public class GroupNameAlreadyExistsException extends Exception {


	public GroupNameAlreadyExistsException(String msg)
    {
        super(msg);
    }

    public GroupNameAlreadyExistsException()
    {
        super();
    }
}
