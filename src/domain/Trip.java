package domain;

import java.util.Date;

/**
 * A trip with its methods and its attributes
 */
public class Trip {

    private Date startDate;
    private Date endDate;
    private Integer groupId;
    private Integer tripId;
    private Double estimateCost;
    private UniversalString tripDescription;
    private String originCity;
    private String endCity;
    public TripStatus tripStatus;

    public Trip(Date startDate, Date endDate, Integer groupId, Integer tripId, Double estimateCost, UniversalString tripDescription, String originCity, String endCity){
        this.startDate = startDate;
        this.endDate = endDate;
        this.groupId = groupId;
        this.tripId = tripId;
        this.estimateCost = estimateCost;
        this.tripDescription = tripDescription;
        this.originCity = originCity;
        this.endCity = endCity;
        this.tripStatus = TripStatus.OPEN;
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

    public void setGroupId(Integer groupId) {
        this.groupId = groupId;
    }

    public Integer getId() {
        return tripId;
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

    public UniversalString getTripDescription() {
        return tripDescription;
    }

    public void setTripDescription(UniversalString tripDescription) {
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

    public Integer getGroupId(){
        return this.groupId;
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
