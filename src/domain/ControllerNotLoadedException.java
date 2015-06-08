package domain;

public class ControllerNotLoadedException extends Exception {
    public ControllerNotLoadedException(String msg)
    {
        super(msg);
    }

    public ControllerNotLoadedException()
    {
        super();
    }
}
