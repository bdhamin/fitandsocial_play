package controllers.api;

import models.GCM;
import models.UserGCM;
import models.Users;
import org.jboss.netty.handler.codec.http.HttpResponseStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import play.mvc.Result;
import services.service.GCMService;
import services.service.UsersService;
import utils.GCM.SendGCMToDevice;

import static play.mvc.Results.internalServerError;
import static play.mvc.Results.ok;

/**
 * Created by mint on 9-10-14.
 */

@Controller
public class GCMDeviceRegistration {

    @Autowired
    private UsersService usersService;
    @Autowired
    private GCMService gcmService;

    public Result registerDevice(String authenticationProviderId, String deviceRegistrationId){
        try {
            Users user = usersService.getUserByAuthenticationProvider(authenticationProviderId);
            GCM gcm = new GCM();
            UserGCM userGCM = new UserGCM();
            gcm.setDeviceRegistrationId(deviceRegistrationId);
            userGCM.setDeviceRegistrationId(deviceRegistrationId);
            userGCM.setUser(user);
            user.setUserGCM(userGCM);
            usersService.merge(user);
            gcmService.persist(gcm);
            new SendGCMToDevice(deviceRegistrationId, authenticationProviderId);
            return ok();
        }catch (Exception e){
            System.out.println("Error Creating GCM!!");
            System.out.println(e.getMessage());
            System.out.println(e.getCause());
            return internalServerError();
        }
    }
}
