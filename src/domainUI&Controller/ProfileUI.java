package src.domainUI&Controller;

import java.util.Collection;
import java.util.Date;

import src.domain.Review;
import src.domain.Trip;
import src.domain.Group;
import src.domain.Profile;


public class ProfileUI extends AbstractDomainUI {

private Profile profile;
	
	public ProfileUI(Profile profile){
		this.profile = profile;
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
	
	public Collection<Group> getGroups(){
		return this.profile.getGroups();
	}
	
	public Collection<Trip> getPastTrips(){
		return this.profile.getTrips();
	}
	
	public Collection<Review> getReviews(){
		return this.profile.getReviews();
	}
}
