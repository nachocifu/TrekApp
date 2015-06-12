package domain;

/**
 * Types of plans available to a user
 * @author nacho
 *
 */

public enum TripStatus {
    OPEN("open"), IN_PROGRESS("in_progress"), CLOSED("closed");

    private String name;


    public String getName() {
        return name;
    }

    TripStatus(String name){
        this.name = name;
    }

    public static TripStatus fromString(String s){
        if(s.equals(TripStatus.OPEN.getName()))
            return TripStatus.OPEN;
        if(s.equals(TripStatus.IN_PROGRESS.getName()))
            return TripStatus.IN_PROGRESS;
        if(s.equals(TripStatus.CLOSED.getName()))
            return TripStatus.CLOSED;
        return null;
    }
}
