package controllers;

import models.*;
import org.h2.engine.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import play.data.Form;
import play.mvc.BodyParser;
import play.mvc.Result;
import services.service.*;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by mint on 6-9-14.
 */

@Controller
public class ActivityController {

    @Autowired
    UsersService usersService;
    @Autowired
    ActivityService activityService;
    @Autowired
    ActivityInformationService activityInformationService;
    @Autowired
    ActivityLocationService activityLocationService;
    @Autowired
    ActivityParticipantService activityParticipantService;

    private final Form<ActivityInformation> activityInformationForm = new Form<ActivityInformation>(ActivityInformation.class);
    private final Form<ActivityLocation> activityLocationForm = new Form<ActivityLocation>(ActivityLocation.class);

    public Result index(){
        return play.mvc.Controller.ok(views.html.activity.index.render(activityInformationForm, activityLocationForm));
    }

    public Result create(){
        Form<ActivityInformation> filledActivityInformation = activityInformationForm.bindFromRequest();
        Form<ActivityLocation> filledActivityLocation = activityLocationForm.bindFromRequest();
        Activity activity = new Activity();
        ActivityInformation activityInformation = filledActivityInformation.get();
        ActivityLocation activityLocation = filledActivityLocation.get();
        List<Activity> activities = new ArrayList<Activity>();
        Users user = usersService.getById(321L);
        if(user != null){
            activity.setUser(user);
            activityInformation.setActivity(activity);
            activityLocation.setActivity(activity);
            activity.setActivityInformation(activityInformation);
            activity.setActivityLocation(activityLocation);
            activities.add(activity);
            ActivityParticipant activityParticipant = new ActivityParticipant();
            activityParticipant.setUser(user);
            activityParticipant.setActivities(activities);
            activityService.persist(activity);
            activityParticipantService.merge(activityParticipant);
        }
        return play.mvc.Controller.ok();
    }

    public Result show(){
        List<Activity> activities =  activityService.getAll();
        return play.mvc.Controller.ok(views.html.activity.activities.render(activities));
    }







//341, 344


}