package controllers.api;

import com.fasterxml.jackson.databind.JsonNode;
import com.google.gson.Gson;
import models.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import play.mvc.BodyParser;
import play.mvc.Result;
import services.service.ActivityParticipantService;
import services.service.ActivityService;
import services.service.UsersService;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import static play.data.Form.form;
import static play.mvc.Controller.request;
import static play.mvc.Results.badRequest;
import static play.mvc.Results.ok;

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


    public Result getDataFromUrl(){

        Map<String, String[]> formData = request().body().asFormUrlEncoded();

        try {
            String userId = formData.get(KEY_ACTIVITY_ID)[0];
            String activityId = formData.get(KEY_USER_ID)[0];

            if (activityId != null && !activityId.trim().isEmpty() && userId != null && !userId.trim().isEmpty()) {
                long uId = Long.valueOf(userId);
                long aId = Long.valueOf(activityId);

                if (uId != 0 && uId > 0 && aId != 0 && aId > 0) {
                    Users user = usersService.getById(uId);
                    Activity activity = activityService.getById(aId);

                    if (user != null && activity != null) {
                        ActivityParticipant activityParticipant = new ActivityParticipant();
                        List<Activity> activities = new ArrayList<>();
                        activities.add(activity);

                        activityParticipant.setUser(user);
                        activityParticipant.setActivities(activities);

                        activityParticipantService.merge(activityParticipant);
                    }
                }
            }
        }catch(Exception e){
            return play.mvc.Controller.internalServerError();
        }
        return play.mvc.Controller.ok();
    }

    /**
     * https://www.playframework.com/documentation/2.0/JavaJsonRequests
     * @return
     */

    @BodyParser.Of(BodyParser.Json.class)
    public Result createActivityFromJson(){
        try {
            JsonNode json = request().body().asJson();
            System.out.println(json);
            Gson gson = new Gson();
            ActivityInformation activityInformation = gson.fromJson(json.toString(), ActivityInformation.class);
            Activity activity = new Activity();
            ActivityLocation activityLocation = gson.fromJson(json.toString(), ActivityLocation.class);
            long userId = json.findPath("user").longValue();
            if (userId != 0 && userId > 0) {
                Users user = usersService.getById(userId);
                if (user != null) {
                    activity.setUser(user);
                    activityInformation.setActivity(activity);
                    activityLocation.setActivity(activity);
                    activity.setActivityInformation(activityInformation);
                    activity.setActivityLocation(activityLocation);
                    activityService.persist(activity);
                }
            }
        }catch (Exception e){
            System.out.println("Something went wrong!!");
            System.out.println(e.getMessage());
            return badRequest();
        }
        return ok();


    }




}
