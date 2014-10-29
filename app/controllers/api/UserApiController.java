package controllers.api;

import com.fasterxml.jackson.databind.JsonNode;
import com.google.gson.Gson;
import com.google.gson.JsonObject;
import models.*;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import play.mvc.BodyParser;
import play.mvc.Result;
import services.service.ActivityParticipantService;
import services.service.ActivityService;
import services.service.GeneralUserInformationService;
import services.service.UsersService;

import java.util.ArrayList;
import java.util.List;

import static play.mvc.Controller.request;
import static play.mvc.Results.badRequest;
import static play.mvc.Results.internalServerError;
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
    @Autowired
    GeneralUserInformationService generalUserInformationService;


    @BodyParser.Of(BodyParser.Xml.class)
    public Result getAllActivities(){
        List<Activity> activities =  activityService.getAll();
        return ok(views.xml.userApi.activityxml.render(activities));
    }

    @BodyParser.Of(BodyParser.Xml.class)
    public Result getUserActivitiesSummary(String id){

        int total = 0;
        int created = 0;
        int participated = 0;
        int cancelled = 0;

        if(id != null && !id.trim().isEmpty()){

            Users user = usersService.getUserByAuthenticationProvider(id);
            if(user != null){
                List<Activity> activities = activityService.findActivitiesByUserId(user.getId());
                List<ActivityParticipant> activityParticipants = activityParticipantService.getAllUserActivityParticipation(user.getId());
                List<ActivityParticipant> cancelledActivities = activityParticipantService.getUserCancelledActivitiesTotal(user.getId());

                if(activities.size() > 0){
                    created = activities.size();
                }
                if(activityParticipants.size() > 0){
                    participated = activityParticipants.size();
                }

                if(cancelledActivities.size() > 0){
                    cancelled = cancelled+cancelledActivities.size();
                }
            }
        }

        total = created + participated + cancelled;

        return ok(views.xml.userApi.activitiesSummary.render(total, created, participated, cancelled));
    }


    @BodyParser.Of(BodyParser.Xml.class)
    public Result loadUserProfile(String uId){

        if(uId != null && !uId.trim().isEmpty()){
            Users user = usersService.getUserByAuthenticationProvider(uId);
            String datePattern = "dd-MM-yyyy";
            String formattedDate = DateTimeFormat.forPattern(datePattern).print(user.getUserHistory().getActiveSince());
            System.out.println(formattedDate);
            if(user != null){
                return ok(views.xml.api.userProfile.render(user, formattedDate));
            }else{
                return badRequest();
            }
        }
        return badRequest();
    }

    @BodyParser.Of(BodyParser.Json.class)
    public Result updateUserProfile(String authenticationID){
        if(!authenticationID.trim().isEmpty()){
            JsonNode jsonNode = request().body().asJson();
            Gson gson = new Gson();
            try{
                Users user = usersService.getUserByAuthenticationProvider(authenticationID);
                if(user != null){
                    GeneralUserInformation generalUserInformation = generalUserInformationService.getById(user.getUserInformation().getId());
                    if(generalUserInformation != null){
                        GeneralUserInformation gui = gson.fromJson(jsonNode.toString(), GeneralUserInformation.class);
                        generalUserInformation.setNickname(gui.getNickname());
                        generalUserInformation.setActivitiesOfInterest(gui.getActivitiesOfInterest());
                        generalUserInformation.setDetails(gui.getDetails());
                        user.setUserInformation(generalUserInformation);
                        usersService.merge(user);
                    }
                }
            }catch (Exception e){
                System.out.println(e.getMessage());
                System.out.println(e.getCause());
                return internalServerError();
            }
        }
        return ok();
    }

}
