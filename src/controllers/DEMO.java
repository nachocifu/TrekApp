package controllers;

import java.rmi.ServerException;
import java.util.Date;

import repositoryMem.GroupRepository;
import repositoryMem.ProfileRepository;
import repositoryMem.TripRepository;
import domain.ControllerNotLoadedException;
import domain.GroupNameAlreadyExistsException;
import domain.SessionNotActiveException;
import domain.UserNameAlreadyExistsException;

public class DEMO {

    public static void main(String[] args) throws SessionNotActiveException, ControllerNotLoadedException, GroupNameAlreadyExistsException {
        Application app = Application.getInstance();
        Session session = Session.getInstance();
        TripRepository tripRepo = new TripRepository(null, null);
        GroupRepository groupRepo = new GroupRepository(null, null);
        ProfileRepository userRepo = new ProfileRepository(null, null);
        CollectionAndSearchController

        try {

            app.registerUser("naty", "natalia", "navas", new Date(1994, 3, 13), true, "agua", "lkj", "lkj@gmail.com");
            app.registerUser("nacho", "ignacio", "cifuentes", new Date(1994, 3, 13), false, "agua", "lkj", "lkj@gmail.com");
            System.out.println("SE CREO A NATY");
            session.logIn("naty", "agua");
            System.out.println(session.getUserName());


            CurrentProfileController naty = app.getCurrentProfileController();
            ProfileController nacho = app.getAProfileController(profile);


            System.out.println("se levanta user: " + naty.getUsername());
            MyGroupController group1 = app.registerGroup("groupName", naty, 3, 3, "ciudad");




        } catch (ServerException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (UserNameAlreadyExistsException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

}
