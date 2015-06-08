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


}
