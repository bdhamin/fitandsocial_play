package controllers.api;

import com.fasterxml.jackson.databind.JsonNode;
import com.google.gson.Gson;
import models.*;
import org.hibernate.Hibernate;
import org.joda.time.DateTime;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import play.mvc.BodyParser;
import play.mvc.Result;
import services.service.ActivityParticipantService;
import services.service.ActivityService;
import services.service.GCMService;
import services.service.UsersService;
import utils.GCM.GCMContent;
import utils.GCM.SendToGCM;

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
    @Autowired
    private GCMService gcmService;

    private final String KEY_ACTIVITY_ID = "activityId";
    private final String KEY_USER_ID = "userId";
    private final String API_KEY = "AIzaSyCM-_FX_DzLKHpVO3lizgLI1VNgn8OmCjw";

    @BodyParser.Of(BodyParser.Json.class)
    public Result participate(){
        try {
            //TODO: check for null values
            JsonNode jsonNode = request().body().asJson();
            String authenticationKey = jsonNode.findPath(KEY_USER_ID).textValue();

            Users users = usersService.getUserByAuthenticationProvider(authenticationKey);

            long userId = users.getId();
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
                    long activityOwnerId = activityService.getActivityOwner(activityId);
                    if(activityOwnerId > 0){
                        Users u = usersService.getById(activityOwnerId);
                        if(u != null){
                            sendNotification(createContent(u.getUserGCM().getDeviceRegistrationId(), user.getUserInformation().getNickname(), u.getAuthenticationProvider().getProviderKey()));
                        }
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
            System.out.println("Create Activity Here!!!!!");
            JsonNode json = request().body().asJson();
            Gson gson = new Gson();
            ActivityInformation activityInformation = gson.fromJson(json.toString(), ActivityInformation.class);
            Activity activity = new Activity();
            ActivityLocation activityLocation = gson.fromJson(json.toString(), ActivityLocation.class);
            String authenticationKey = json.findPath("user").textValue();
            Users user = usersService.getUserByAuthenticationProvider(authenticationKey);
            System.out.println("Create Activity Here!!!!! " + user.getId());
            if (user.getId() != null && user.getId() > 0) {
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
    public Result getUpcomingActivities(String authenticationProviderKey){
        List<Activity> activities = activityService.getUserUpcomingActivities(authenticationProviderKey);
//        for(Activity activity : activities) {
//            System.out.println(activity.getActivityInformation().getTitle() + " has " + activity.getActivityParticipants().size() + " participants.");
//            System.out.println(activity.getActivityInformation().getTitle() + " participants are: ");
//            for (ActivityParticipant participant : activity.getActivityParticipants()) {
//                System.out.println(participant.getUser().getUserInformation().getNickname());
//            }
//        }
        return ok(views.xml.userApi.activityxml.render(activities));
    }


    @BodyParser.Of(BodyParser.Xml.class)
    public Result getUserLastActivity(String authenticationProviderKey){
        Activity activity = activityService.getLastActivity(authenticationProviderKey);
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
            String authenticationProviderKey = jsonNode.findPath("userId").textValue();
            Users user = usersService.getUserByAuthenticationProvider(authenticationProviderKey);
            if(activityId != 0 && activityId > 0 && user.getId() != null && user.getId() > 0){
                ActivityParticipant participant = activityParticipantService.findActivityParticipation(user.getId(), activityId);
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

    private GCMContent createContent(String registrationDeviceId, String userName, String authenticationId){
        GCMContent content = new GCMContent();
        content.addRegId(registrationDeviceId);
        content.createData("New Subscriber!", "The user " + userName + " has subscribed to your activity!", authenticationId);
        return content;
    }

    private void sendNotification(GCMContent gcmContent){
        SendToGCM.post(API_KEY, gcmContent);
    }
}