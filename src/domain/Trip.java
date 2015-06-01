package src.domain;

import java.util.Date;

/**
 * A trip with its methods and its attributes
 */
public class Trip {

	private Date startDate;
	private Date endDate;
	private Integer groupId;
	private Integer tripId;
	private Integer estimateCost;
	private UniversalString tripDescription;
	private String originCity;
	private String endCity;
	public enum tripStatus {OPEN, IN_PROGRESS, CLOSED};
	private tripStatus tripStatus;

	
	public Trip(Date startDate, Date endDate, Integer groupId, Integer tripId, Integer estimateCost, UniversalString tripDescription, String originCity, String endCity){
		this.startDate = startDate;
		this.endDate = endDate;
		this.groupId = groupId;
		this.tripId = tripId;
		this.estimateCost = estimateCost;
		this.tripDescription = tripDescription;
		this.originCity = originCity;
		this.endCity = endCity;
		this.tripStatus = tripStatus.OPEN;
		}

	public Date getstartDate() {
		return startDate;
	}

	public void setstartDate(Date startDate) {
		this.startDate = startDate;
	}

	public Date getendDate() {
		return endDate;
	}

	public void setendDate(Date endDate) {
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

	public Integer getestimateCost() {
		return estimateCost;
	}

	public void setestimateCost(Integer estimateCost) {
		this.estimateCost = estimateCost;
	}

	public UniversalString gettripDescription() {
		return tripDescription;
	}

	public void settripDescription(UniversalString tripDescription) {
		this.tripDescription = tripDescription;
	}

	public String getoriginCity() {
		return originCity;
	}

	public void setoriginCity(String originCity) {
		this.originCity = originCity;
	}

	public String getendCity() {
		return endCity;
	}

	public void setendCity(String endCity) {
		this.endCity = endCity;
	}

	public tripStatus getTripStatus() {
		return tripStatus;
	}

	public void setTripStatus(tripStatus tripStatus) {
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
