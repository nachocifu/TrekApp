package domain;

public class ObjectAlreadyExistsException extends Exception {


    public ObjectAlreadyExistsException(String msg)
    {
        super(msg);
    }

    public ObjectAlreadyExistsException()
    {
        super();
    }
}
