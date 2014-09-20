package controllers.api;

import models.Activity;
import models.ActivityParticipant;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import play.mvc.BodyParser;
import play.mvc.Result;
import services.service.ActivityParticipantService;
import services.service.ActivityService;
import services.service.UsersService;

import java.util.List;

/**
 * Created by mint on 12-9-14.
 */
@Controller
public class UserApiController{

    @Autowired
    UsersService usersService;
    @Autowired
    ActivityParticipantService activityParticipantService;
    @Autowired
    ActivityService activityService;


    @BodyParser.Of(BodyParser.Xml.class)
    public Result getAllActivities(){
        List<Activity> activities =  activityService.getAll();
        return play.mvc.Controller.ok(views.xml.userApi.activityxml.render(activities));
    }

    @BodyParser.Of(BodyParser.Xml.class)
    public Result getUserActivitiesSummary(Long id){
        List<Activity> activities = activityService.findActivitiesByUserId(id);
        List<ActivityParticipant> activityParticipants = activityParticipantService.getAllUserActivityParticipation(id);
        int total = 0;
        int created = 0;
        int participated = 0;

        if(activities.size() > 0){
            created = activities.size();
        }
        if(activityParticipants.size() > 0){
            participated = activityParticipants.size();
        }

        total = created + participated;

        return play.mvc.Controller.ok(views.xml.userApi.activitiesSummary.render(total, created, participated));
    }




}
