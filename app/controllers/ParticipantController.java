package controllers;

import models.Activity;
import models.ActivityParticipant;
import models.Users;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import play.mvc.Result;
import services.service.ActivityParticipantService;
import services.service.ActivityService;
import services.service.UsersService;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by mint on 7-9-14.
 */
@Controller
public class ParticipantController {

    @Autowired
    UsersService usersService;
    @Autowired
    ActivityService activityService;
    @Autowired
    ActivityParticipantService activityParticipantService;


    public Result addParticipant(){

        List<Activity> activities = new ArrayList<Activity>();

        Users user = usersService.getById(324L);

        Activity activity1 = activityService.getById(333L);
//        Activity activity2 = activityService.getById(240L);
//        Activity activity3 = activityService.getById(132L);

        activities.add(activity1);
//        activities.add(activity2);
//        activities.add(activity3);


        ActivityParticipant activityParticipant = new ActivityParticipant();
        activityParticipant.setUser(user);

        activityParticipant.setActivities(activities);

        activityParticipantService.merge(activityParticipant);

        return play.mvc.Controller.ok();
    }
}
