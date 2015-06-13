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
    
    public void addNewCost(Double newCostToAdd){
    	this.estimateCost += newCostToAdd;
    }

    public Date getStartDate() {
        return startDate;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    public Date getEndDate() {
        return endDate;
    }

    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }

    public void setId(Integer tripId) {
        this.tripId = tripId;
    }

    public Double getEstimateCost() {
        return estimateCost;
    }

    public void setEstimateCost(Double estimateCost) {
        this.estimateCost = estimateCost;
    }

    public String getTripDescription() {
        return tripDescription;
    }

    public void setTripDescription(String tripDescription) {
        this.tripDescription = tripDescription;
    }

    public String getOriginCity() {
        return originCity;
    }

    public void setOriginCity(String originCity) {
        this.originCity = originCity;
    }

    public String getEndCity() {
        return endCity;
    }

    public void setEndCity(String endCity) {
        this.endCity = endCity;
    }

    public TripStatus getTripStatus() {
        return tripStatus;
    }

    public void setTripStatus(TripStatus tripStatus) {
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
