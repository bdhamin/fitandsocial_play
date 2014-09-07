package controllers;

import models.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import play.data.Form;
import play.mvc.Result;
import services.service.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

/**
 * Created by mint on 5-9-14.
 */
@Controller
public class UsersController {


    @Autowired
    UsersService usersService;
    @Autowired
    PersonalInformationService personalInformationService;
    @Autowired
    GeneralUserInformationService generalUserInformationService;
    @Autowired
    UserHistoryService userHistoryService;
    @Autowired
    ActivityTypeService activityTypeService;
    @Autowired
    AuthenticationProviderService authenticationProviderService;

    private static final Form<PersonalInformation> personalInformationForm = Form.form(PersonalInformation.class);
    private static final Form<GeneralUserInformation> generalUserInformationForm = Form.form(GeneralUserInformation.class);
    private static final Form<ActivityType> activityTypeForm = Form.form(ActivityType.class);

    public Result index(){
        return play.mvc.Controller.ok(views.html.users.index.render(personalInformationForm, generalUserInformationForm, activityTypeForm));
    }

    public Result save(){

        Form<PersonalInformation> personalInformationFilledForm = personalInformationForm.bindFromRequest();
        Form<GeneralUserInformation> generalUserInformationFilledForm = generalUserInformationForm.bindFromRequest();
        Form<ActivityType> activityTypeFilledForm = activityTypeForm.bindFromRequest();

        PersonalInformation personalInformation = personalInformationFilledForm.get();
        GeneralUserInformation generalUserInformation = generalUserInformationFilledForm.get();

        Users user = new Users();
        personalInformation.setUser(user);
        generalUserInformation.setUser(user);
        user.setPersonalInformation(personalInformation);
        user.setUserInformation(generalUserInformation);
        usersService.persist(user);

        List<String> activitiesOfInterest = activitiesType(activityTypeFilledForm.get().getActivityType());
        for(String s : activitiesOfInterest){
            ActivityType at = new ActivityType();
            at.setGeneralUserInformation(generalUserInformation);
            at.setActivityType(s);
            activityTypeService.persist(at);
        }

//        AuthenticationProvider authenticationProvider = new AuthenticationProvider();
//        authenticationProvider.setProviderKey(createRandom());
//        authenticationProvider.setProviderType("Facebook");
//        authenticationProvider.setUser(user);
//        authenticationProviderService.persist(authenticationProvider);

        UserHistory userHistory = new UserHistory();
        userHistory.setUser(user);
        userHistoryService.persist(userHistory);

        return play.mvc.Controller.ok();
    }


    private long createRandom(){

        Random random = new Random(8);
        return random.nextLong();
    }



    private List<String> activitiesType(String activitiesType){
        String comma = ",";
        return new ArrayList(Arrays.asList(activitiesType.split(comma)));
    }







}
