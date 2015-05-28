package src.domain;

import java.util.Date;

/**
 * A trip with its methods and its attributes
 */
public class Trip {
	
	private Date start_date;
	private Date end_date;
	private Integer group_id;
	private Integer trip_id;
	private Integer estimate_cost;
	private UniversalString trip_description;
	private String origin_city;
	private String end_city;
	private enum Trip_status {OPEN, IN_PROGRESS, CLOSED};
	private Trip_status trip_status;
	
	public Trip(Date start_date, Date end_date, Integer group_id, Integer trip_id, Integer estimate_cost, UniversalString trip_description, String origin_city, String end_city){
		this.start_date = start_date;
		this.end_date = end_date;
		this.group_id = group_id;
		this.trip_id = trip_id;
		this.estimate_cost = estimate_cost;
		this.trip_description = trip_description;
		this.origin_city = origin_city;
		this.end_city = end_city;
		this.trip_status = Trip_status.OPEN;
		}

	public Date getStart_date() {
		return start_date;
	}

	public void setStart_date(Date start_date) {
		this.start_date = start_date;
	}

	public Date getEnd_date() {
		return end_date;
	}

	public void setEnd_date(Date end_date) {
		this.end_date = end_date;
	}

	public Integer getGroup_id() {
		return group_id;
	}

	public void setGroup_id(Integer group_id) {
		this.group_id = group_id;
	}

	public Integer getId() {
		return trip_id;
	}

	public void setId(Integer trip_id) {
		this.trip_id = trip_id;
	}

	public Integer getEstimate_cost() {
		return estimate_cost;
	}

	public void setEstimate_cost(Integer estimate_cost) {
		this.estimate_cost = estimate_cost;
	}

	public UniversalString getTrip_description() {
		return trip_description;
	}

	public void setTrip_description(UniversalString trip_description) {
		this.trip_description = trip_description;
	}

	public String getOrigin_city() {
		return origin_city;
	}

	public void setOrigin_city(String origin_city) {
		this.origin_city = origin_city;
	}

	public String getEnd_city() {
		return end_city;
	}

	public void setEnd_city(String end_city) {
		this.end_city = end_city;
	}

	public Trip_status getTrip_status() {
		return trip_status;
	}

	public void setTrip_status(Trip_status trip_status) {
		this.trip_status = trip_status;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((trip_id == null) ? 0 : trip_id.hashCode());
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
		if (trip_id == null) {
			if (other.trip_id != null)
				return false;
		} else if (!trip_id.equals(other.trip_id))
			return false;
		return true;
	}
	
	
	
	

	
}
