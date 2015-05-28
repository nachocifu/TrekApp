package src.domainUI;

import java.util.Date;

import src.domain.UniversalString;

/**
 * 
 * An object only to get info and show it on the interface
 *
 */
public class TripUI extends AbstractDomainUI {
	
	private Date start_date;
	private Date end_date;
	private Integer estimate_cost;
	private UniversalString trip_description;
	private String origin_city;
	private String end_city;
	private enum Trip_status {OPEN, IN_PROGRESS, CLOSED};
	private Trip_status trip_status;
	
	public TripUI(Date start_date, Date end_date, Integer estimate_cost, UniversalString trip_description, String origin_city, String end_city){
		this.start_date = start_date;
		this.end_date = end_date;
		this.estimate_cost = estimate_cost;
		this.trip_description = trip_description;
		this.origin_city = origin_city;
		this.end_city = end_city;
		this.trip_status = Trip_status.OPEN;
		}

	public Date getStart_date() {
		return start_date;
	}

	public Date getEnd_date() {
		return end_date;
	}

	public Integer getEstimate_cost() {
		return estimate_cost;
	}

	public UniversalString getTrip_description() {
		return trip_description;
	}

	public String getOrigin_city() {
		return origin_city;
	}

	public String getEnd_city() {
		return end_city;
	}

	public Trip_status getTrip_status() {
		return trip_status;
	}
	
	
	
}
