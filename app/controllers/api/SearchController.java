package controllers.api;

import models.Activity;
import models.ActivityInformation;
import org.joda.time.DateTime;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import play.mvc.BodyParser;
import play.mvc.Result;
import services.service.ActivityInformationService;
import services.service.ActivityService;
import utils.DateAndTimePrintFormatter;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

/**
 * Created by mint on 15-9-14.
 */

@Controller
public class SearchController {


    @Autowired
    private ActivityService activityService;

    @BodyParser.Of(BodyParser.Xml.class)
    public Result search(String activityType, int distance, int durationMin, int durationMax, int radiusMin, int radiusMax, String startDate, String time){

        long activityStartDate = DateAndTimePrintFormatter.convertStringToTimeStamp(startDate);
        long activityStartTime = DateAndTimePrintFormatter.convertTimeToLong(time);
        List<Activity> activities = activityService.activitiesList(activityType, distance, durationMin, durationMax, radiusMin, radiusMax, activityStartDate, activityStartTime);
        return play.mvc.Controller.ok(views.xml.api.searchResults.render(activities));

    }



}
