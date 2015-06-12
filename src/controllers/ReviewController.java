package controllers;

import java.util.Collection;
import java.util.HashSet;

import repository.AbstractRepository;
import repository.GroupRepository;
import repository.TripRepository;
import repository.UserRepository;
import domain.Profile;
import domain.Review;
import domain.Session;
import domain.SessionNotActiveException;


//NO SE USA, NO ES NECESARIA YA QUE LAS REVIEWS COMO OBJETOS NO TIENEN NADA QUE ESCONDER DEL FRONT, TIENEN SOLO GETTERS Y NO SE PUEDEN MODIFICAR UNA VEZ CREADAS
public class ReviewController extends AbstractController<Review>{

    public ReviewController(UserRepository profileRepo,
            TripRepository tripRepo, GroupRepository groupRepo) {
        super(profileRepo, tripRepo, groupRepo);
        // TODO Auto-generated constructor stub
    }

    @Override
    protected AbstractRepository<Review> getRepository() {
        // TODO Auto-generated method stub
        return null;
    }

    /**
     * Generate list of controllers from list of T objects
     * @param list
     * @return response List of controllers
     * @throws SessionNotActiveException
     */
    protected static HashSet<ReviewController> generateListOfControllers(Collection<Review> list) throws SessionNotActiveException{
        HashSet<ReviewController> response = new  HashSet<ReviewController>();
        Application app = Application.getInstance();
        String currentUser = Session.getInstance().getUserName();
        ReviewController controller;

        for(Review each: list){
            if(each.s getUsrName().equals(currentUser))
                controller = app.getCurrentProfileController();
            else
                controller = app.getProfileController();

            controller.load(each);
            response.add(controller);
        }
        return response;
    }
}
