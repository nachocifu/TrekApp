package domain;

/**
 * The status a trip has, it can either be OPEN if the trip hasnt yet started, IN_PROGRESS if the trip is
 * currently taking place, or CLOSED if the trip has already finished
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

    /**
     * @param s will be compared with the trip status
     * @return a TripStatus value either OPEN, IN_PROGRESS or CLOSED
     */
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
