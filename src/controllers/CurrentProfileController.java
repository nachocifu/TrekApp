package controllers;

import domain.Session;
import domain.SessionNotActiveException;
import repository.UserRepository;

public class CurrentProfileController extends ProfileController {

    public CurrentProfileController(UserRepository repo) {
        super(repo);
        // TODO Auto-generated constructor stub
    }



    public Boolean load() throws SessionNotActiveException{
        return this.load(Session.getInstance().getUserName());
    }

    public Boolean load(String username) throws SessionNotActiveException{
        if(!Session.getInstance().isActive())
            throw new SessionNotActiveException("ERROR || You must log in before operating.");
        if(!Session.getInstance().getUserName().equals(username))
            throw new IllegalArgumentException("ERROR || You can only load you own profile.");

        return this.setProfile( username);


        /* #### TODOS LOS METODOS EN ESTE SON SETTERSSS,,,NO HAY GETTERS YA QUE SE HEREDAN#######*/
        /* TODOS los metodos, al principio lo primero que hacen es validar que la session este abierta.*/
    }
}
