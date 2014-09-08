package controllers;

import models.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import play.data.Form;
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


    private final Form<ActivityInformation> activityInformationForm = new Form<>(ActivityInformation.class);
    private final Form<ActivityLocation> activityLocationForm = new Form<>(ActivityLocation.class);



    public Result index(){
        return play.mvc.Controller.ok(views.html.activity.index.render(activityInformationForm, activityLocationForm));
    }

    public Result create(){

        Form<ActivityInformation> filledActivityInformation = activityInformationForm.bindFromRequest();
        Form<ActivityLocation> filledActivityLocation = activityLocationForm.bindFromRequest();

        ActivityParticipant activityParticipant = new ActivityParticipant();
        Activity activity = new Activity();
        ActivityInformation activityInformation = filledActivityInformation.get();
        ActivityLocation activityLocation = filledActivityLocation.get();
        List<ActivityParticipant> activityParticipantList = new ArrayList<>();
        Users user = usersService.getById(123L);
        if(user != null){

            activity.setUser(user);
            activityInformation.setActivity(activity);
            activityLocation.setActivity(activity);
            activity.setActivityInformation(activityInformation);
            activity.setActivityLocation(activityLocation);
            activityService.persist(activity);

//            List<Activity> activityList = activityService.getAll();
//            activityParticipant.setUser(user);
//            activityParticipant.setActivities(activityList);
//            activityParticipantList.add(activityParticipant);
//            activity.setActivityParticipants(activityParticipantList);

//            List<Activity> activityList = activityService.getAll();
//            activityParticipant.setUser(user);
//            activityParticipant.setActivities(activityList);
//            activityService.persist(activity);
        }

        return play.mvc.Controller.ok();
    }




}
