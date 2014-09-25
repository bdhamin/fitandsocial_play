package controllers.api;

import models.Activity;
import models.ActivityParticipant;
import models.ActivityType;
import models.Users;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import play.mvc.BodyParser;
import play.mvc.Result;
import services.service.ActivityParticipantService;
import services.service.ActivityService;
import services.service.UsersService;

import java.util.ArrayList;
import java.util.List;

import static play.mvc.Results.badRequest;
import static play.mvc.Results.ok;

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
        return ok(views.xml.userApi.activityxml.render(activities));
    }

    @BodyParser.Of(BodyParser.Xml.class)
    public Result getUserActivitiesSummary(Long id){
        List<Activity> activities = activityService.findActivitiesByUserId(id);
        List<ActivityParticipant> activityParticipants = activityParticipantService.getAllUserActivityParticipation(id);
        List<ActivityParticipant> cancelledActivities = activityParticipantService.getUserCancelledActivitiesTotal(id);



        int total = 0;
        int created = 0;
        int participated = 0;
        int cancelled = 0;

        if(activities.size() > 0){
            created = activities.size();
        }
        if(activityParticipants.size() > 0){
            participated = activityParticipants.size();
        }

        if(cancelledActivities.size() > 0){
            cancelled = cancelled+cancelledActivities.size();
        }

        total = created + participated + cancelled;

        return ok(views.xml.userApi.activitiesSummary.render(total, created, participated, cancelled));
    }


    @BodyParser.Of(BodyParser.Xml.class)
    public Result loadUserProfile(long uId){
        if(uId != 0 && uId > 0){
            Users user = usersService.getById(uId);
            if(user != null){
                return ok(views.xml.api.userProfile.render(user));
            }else{
                return badRequest();
            }
        }
        return badRequest();
    }




}
