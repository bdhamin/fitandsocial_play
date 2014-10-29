package controllers.api;

import com.fasterxml.jackson.databind.JsonNode;
import com.google.gson.Gson;
import models.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import play.mvc.BodyParser;
import play.mvc.Result;
import services.service.*;
import utils.GCM.SendGCMToDevice;

import static play.mvc.Controller.request;
import static play.mvc.Results.badRequest;
import static play.mvc.Results.internalServerError;
import static play.mvc.Results.ok;

/**
 * Created by mint on 1-10-14.
 */
@Controller
public class CreateUser {

    @Autowired
    private UsersService usersService;
    @Autowired
    private GCMService gcmService;


    @BodyParser.Of(BodyParser.Json.class)
    public Result createAccount(){
        String registrationID = "";


        try {
            JsonNode json = request().body().asJson();
            Gson gson = new Gson();

            Users user = new Users();
            GeneralUserInformation generalUserInfo = gson.fromJson(json.toString(), GeneralUserInformation.class);
            PersonalInformation personalInformation = gson.fromJson(json.toString(), PersonalInformation.class);
            AuthenticationProvider authenticationProvider = gson.fromJson(json.toString(), AuthenticationProvider.class);

//            UserGCM userGCM = gson.fromJson(json.toString(), UserGCM.class);
//            GCM gcm = gson.fromJson(json.toString(), GCM.class);

            UserHistory userHistory = new UserHistory();

            generalUserInfo.setUser(user);
            personalInformation.setUser(user);
            userHistory.setUser(user);
            authenticationProvider.setUser(user);
//            userGCM.setUser(user);

            user.setUserInformation(generalUserInfo);
            user.setPersonalInformation(personalInformation);
            user.setUserHistory(userHistory);
            user.setAuthenticationProvider(authenticationProvider);
//            user.setUserGCM(userGCM);
            usersService.persist(user);
//            gcmService.persist(gcm);
//            registrationID = userGCM.getDeviceRegistrationId();

        }catch (Exception e){
            return internalServerError();
        }
//        new SendGCMToDevice(registrationID);
        return ok();
    }
}