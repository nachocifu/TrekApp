package domain;

import java.util.Date;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

/**
 * A trip with its methods and its attributes
 */
@DatabaseTable( tableName = "Trip")
public class Trip {
    @DatabaseField
    private Date startDate;

    @DatabaseField
    private Date endDate;

    @DatabaseField( generatedId = true)
    private Integer tripId;

    @DatabaseField
    private Double estimateCost;

    @DatabaseField
    private String tripDescription;

    @DatabaseField
    private String originCity;

    @DatabaseField
    private String endCity;

    @DatabaseField
    public TripStatus tripStatus;

    public Trip(){
    }

    public Trip(Date startDate, Date endDate, Double estimateCost, String tripDescription, String originCity, String endCity){
        this.startDate = startDate;
        this.endDate = endDate;
        this.estimateCost = estimateCost;
        this.tripDescription = tripDescription;
        this.originCity = originCity;
        this.endCity = endCity;
        this.tripStatus = TripStatus.OPEN;
        }
    
    /**
     * @return the amount of the days the trip will take
     */
    public int getTripDurationInDays(){
    	return (int)( (this.endDate.getTime() - startDate.getTime()) / (1000 * 60 * 60 * 24));
    }
    
    /**
     * @param newCostToAdd to the overall trips cost
     */
    public void addNewCost(Double newCostToAdd){
    	this.estimateCost += newCostToAdd;
    }

    /**
     * @return the date when the trip starts
     */
    public Date getStartDate() {
        return startDate;
    }

    /**
     * @param startDate that will be set as the day when the trip begins
     */
    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    /**
     * @return the date when the trip ends
     */
    public Date getEndDate() {
        return endDate;
    }

    /**
     * @param endDate that will be set as the last day of the trip
     */
    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }

    /**
     * @param tripId
     */
    public void setId(Integer tripId) {
        this.tripId = tripId;
    }

    /**
     * @return the estimated cost for the trip
     */
    public Double getEstimateCost() {
        return estimateCost;
    }

    /**
     * @param estimateCost that will be set as the estimated cost of the trip
     */
    public void setEstimateCost(Double estimateCost) {
        this.estimateCost = estimateCost;
    }

    /**
     * @return a string with a trip description
     */
    public String getTripDescription() {
        return tripDescription;
    }

    /**
     * @param tripDescription that will be set as the trips description
     */
    public void setTripDescription(String tripDescription) {
        this.tripDescription = tripDescription;
    }

    /**
     * @return the city where the trip is starting off
     */
    public String getOriginCity() {
        return originCity;
    }

    /**
     * @param originCity that will be set as the trips begining city
     */
    public void setOriginCity(String originCity) {
        this.originCity = originCity;
    }

    /**
     * @return the city where the trip ends
     */
    public String getEndCity() {
        return endCity;
    }

    /**
     * @param endCity that will be set as the city where the trip ends
     */
    public void setEndCity(String endCity) {
        this.endCity = endCity;
    }

    /**
     * @return the trips status, the return values could be CLOSED, OPEN or IN_PROGRESS
     */
    public TripStatus getTripStatus() {
        return tripStatus;
    }

    /** 
     * @param tripStatus to be set
     * @throws IllegalArgumentException will be thrown if the status update trying to be realized is not possible
     * for example if the trip is finished the staus cannot be furtherly modified, if the trip is in progress it cannot 
     * be set to open
     */
    public void setTripStatus(TripStatus tripStatus) {
    	if(this.tripStatus == TripStatus.CLOSED && ((tripStatus == TripStatus.OPEN) || (tripStatus == TripStatus.IN_PROGRESS))){
    		throw new IllegalArgumentException("Cannot set the status to Open or In Progress because the Trip is already closed");
    	} else if((tripStatus == TripStatus.IN_PROGRESS) && this.tripStatus == TripStatus.OPEN){
    		throw new IllegalArgumentException("Cannot set the status to Open when the Trip is In Progress");
    	}
        this.tripStatus = tripStatus;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((tripId == null) ? 0 : tripId.hashCode());
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        Trip other = (Trip) obj;
        if (tripId == null) {
            if (other.tripId != null)
                return false;
        } else if (!tripId.equals(other.tripId))
            return false;
        return true;
    }






}
