package controllers;

import java.util.Collection;
import java.util.Date;
import java.util.HashSet;

import repository.AbstractRepository;
import repository.UserRepository;
import domain.Group;
import domain.InvalidPasswordException;
import domain.Profile;
import domainUI_Controller.GroupUI;
import domainUI_Controller.ProfileUI;
import domainUI_Controller.TripUI;

public class ProfileService{

    private UserRepository repo;

    /**
     * @param user repository
     */
    public ProfileService(UserRepository repo){
        this.repo=repo;
    }


    /**
     * Add a new user to the database
     * @param usrName
     * @param name
     * @param surname
     * @param brthDay, the users age should be between 18 and 85
     * @param sex
     * @param pass, should have at least 5 letters/digits
     * @throws IllegalArgumentException when the password of birthday are not valid
     */
    @SuppressWarnings("deprecation")
    public boolean newUser(String usrName, String name, String surname, Date brthDay, boolean sex, String pass, String city, String email)throws IllegalArgumentException{
        if(brthDay.getYear() < 1930 || brthDay.getYear() > 1997)
            throw new IllegalArgumentException("Year is not valid, insert a valid year");
        if(pass.length() <5)
            throw new IllegalArgumentException("The password must contain at least 5 letters/digits");

        Profile user= new Profile(usrName, name, surname, brthDay, sex, pass, city, email);
        return repo.add(user);
    }

    /**
     * @param usrId of the user to be deleted from the database
     */
    public void delete(ProfileUI loggedUser){
        repo.delete(loggedUser.getUsrId());
    }

    /**
     * @param usrId
     * @param pass
     * @return true when the login is successful
     * @throws InvalidPasswordException when the password does not coincide with the users password
     */
    public boolean logIn(String usrName, String pass) throws InvalidPasswordException{
        Profile user = repo.getById(usrName);
        if(!user.cmpPassword(pass))
            throw new InvalidPasswordException("Password or username not valid");
        return true;
    }

    public Collection<ProfileUI> getProfileFriendsUI(Profile profile){
        Collection<ProfileUI> friends = new HashSet<ProfileUI>();
        for (Profile friend : profile.getFriends()) {
            friends.add(new ProfileUI(friend));
        }
        return friends;
    }

    public ProfileUI getLoggedProfileUI(String usrName){
        return new ProfileUI(repo.getById(usrName));
    }

    public ProfileUI getProfileUI(Profile profile){
        return new ProfileUI(profile);
    }


}
