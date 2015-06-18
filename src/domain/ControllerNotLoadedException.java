package domain;

/**
 * Exception that will be thrown when the controller requiered is not properly loaded
 */
@SuppressWarnings("serial")
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
