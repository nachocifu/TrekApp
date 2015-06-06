package src.domainUI_Controller;

import java.util.Collection;
import java.util.Date;

import controllers.GroupService;
import controllers.ProfileService;
import controllers.TripService;
import domain.Review;
import domainUI_Controller.TripUI;
import domain.Profile;


public class ProfileUI extends AbstractDomainUI {

	private Profile profile;
	private TripService tripService;
	private GroupService groupService;
	private ProfileService profileService;
	
	public ProfileUI(Profile profile){
		this.profile = profile;
		this.tripService = new TripService();
		this.groupService = new GroupService();
		this.profileService = new ProfileService();
	}
	
	public String getUsername(){
		return this.profile.getUsrName();
	}
	
	public String getUserName(){
		return this.profile.getName();
	}
	
	public String getSurname(){
		return this.profile.getSurname();
	}
	
	public Boolean getSex(){
		return this.profile.getSex();
	}
	
	public Date getBirthday(){
		return this.profile.getBirthDay();
	}
	
	public Double getRating(){
		return this.profile.getRating();
	}
	
	public String getCity(){
		return this.getCity();
	}
	
	public String getMail(){
		return this.profile.getEmail();
	}
	
	public Collection<Profile> getFriends(){
		return this. profile.getFriends();
	}
	
	public Collection<GroupUI> getGroups(){
		return this.groupService.getProfileGroupsUI(profile);
	}
	
	public Collection<PastTripUI> getPastTripsUI(){
		return this.tripService.getProfilePastTripsUI(profile);
	}
	
	public Collection<Review> getReviews(){
		return this.profile.getReviews();
	}
}
