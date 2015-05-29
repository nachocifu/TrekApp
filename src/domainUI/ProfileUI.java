package domainUI;

import domain.Profile;


public class ProfileUI extends AbstractDomainUI {

private Profile profile;
	
	public ProfileUI(Profile profile){
		this.profile = profile;
		}
	public String getUsername(){
		return this.profile.getUsrName();
	}
	
	public Integer getUserId(){
		return this.profile.getUsrId();
	}
	
	public String getUserName(){
		return this.profile.getName();
	}
	
	public String getSurname(){
		return this.profile.getSurname();
	}
	
	
}
