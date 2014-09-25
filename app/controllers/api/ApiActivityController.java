package controllers.api;

import com.fasterxml.jackson.databind.JsonNode;
import com.google.gson.Gson;
import models.*;
import org.joda.time.DateTime;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import play.mvc.BodyParser;
import play.mvc.Result;
import services.service.ActivityParticipantService;
import services.service.ActivityService;
import services.service.UsersService;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

import static play.data.Form.form;
import static play.mvc.Controller.request;
import static play.mvc.Results.*;

/**
 * Created by mint on 17-9-14.
 */

@Controller
public class ApiActivityController {

    @Autowired
    private UsersService usersService;
    @Autowired
    private ActivityService activityService;
    @Autowired
    private ActivityParticipantService activityParticipantService;

    private final String KEY_ACTIVITY_ID = "activityId";
    private final String KEY_USER_ID = "userId";

    @BodyParser.Of(BodyParser.Json.class)
    public Result participate(){

        try {
            JsonNode jsonNode = request().body().asJson();
            long userId = jsonNode.findPath(KEY_USER_ID).longValue();
            long activityId = jsonNode.findPath(KEY_ACTIVITY_ID).longValue();

            if (activityId != 0 && activityId > 0 && userId != 0 && userId > 0) {

                    Users user = usersService.getById(userId);
                    Activity activity = activityService.getById(activityId);

                    if (user != null && activity != null) {
                        ActivityParticipant activityParticipant = new ActivityParticipant();
                        List<Activity> activities = new ArrayList<>();
                        activities.add(activity);

                        activityParticipant.setUser(user);
                        activityParticipant.setActivities(activities);

                        activityParticipantService.merge(activityParticipant);
                    }
                }
        }catch(Exception e){
            return internalServerError();
        }
        return ok();
    }

    /**
     * https://www.playframework.com/documentation/2.0/JavaJsonRequests
     * @return
     */

    @BodyParser.Of(BodyParser.Json.class)
    public Result createActivityFromJson(){
        try {
            JsonNode json = request().body().asJson();
            Gson gson = new Gson();
            ActivityInformation activityInformation = gson.fromJson(json.toString(), ActivityInformation.class);
            Activity activity = new Activity();
            ActivityLocation activityLocation = gson.fromJson(json.toString(), ActivityLocation.class);
            long userId = json.findPath("user").longValue();
            if (userId != 0 && userId > 0) {
                Users user = usersService.getById(userId);
                if (user != null) {
                    List<Activity> activities = new ArrayList<>();
                    activity.setUser(user);
                    activityInformation.setActivity(activity);
                    activityLocation.setActivity(activity);
                    activity.setActivityInformation(activityInformation);
                    activity.setActivityLocation(activityLocation);
                    activityService.persist(activity);
                    ActivityParticipant activityParticipant = new ActivityParticipant();
                    activityParticipant.setUser(user);
                    activities.add(activity);
                    activityParticipant.setActivities(activities);
                    activityParticipantService.merge(activityParticipant);
                }
            }
        }catch (Exception e){
            System.out.println(e.getMessage());
            return badRequest();
        }
        return ok();
    }

    @BodyParser.Of(BodyParser.Xml.class)
    public Result getUpcomingActivities(long id){
        List<Activity> activities = activityService.getUserUpcomingActivities(id);
        return ok(views.xml.userApi.activityxml.render(activities));
    }


    @BodyParser.Of(BodyParser.Xml.class)
    public Result getUserLastActivity(long id){
        Activity activity = activityService.getLastActivity(id);
        if(activity != null){
            return ok(views.xml.api.userLastActivity.render(activity));
        }else{
            return notFound();
        }

    }


    @BodyParser.Of(BodyParser.Json.class)
    public Result cancelParticipation(){
        try{
            JsonNode jsonNode = request().body().asJson();
            long activityId = jsonNode.findPath("activityId").longValue();
            long userId = jsonNode.findPath("userId").longValue();

            if(activityId != 0 && activityId > 0 && userId != 0 && userId > 0){
                ActivityParticipant participant = activityParticipantService.findActivityParticipation(userId, activityId);
                if(participant != null){
                    participant.setParticipationActive(false);
                    participant.setCancellationDate(new Date().getTime());
                    activityParticipantService.merge(participant);
                    return ok();
                }else{
                    return badRequest();
                }
            }else{
                return badRequest();
            }
        }catch (Exception e){
            return badRequest();
        }
    }
}