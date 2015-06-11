package controllers;

import repository.GroupRepository;
import repository.TripRepository;
import repository.UserRepository;

public class MyTripController extends TripController {

    public MyTripController(UserRepository profileRepo,
            TripRepository tripRepo, GroupRepository groupRepo) {
        super(profileRepo, tripRepo, groupRepo);
    }


}
