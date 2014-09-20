package services.service;

import models.ActivityInformation;
import org.joda.time.DateTime;

import java.util.List;

/**
 * Created by mint on 5-9-14.
 */
public interface ActivityInformationService extends DefaultService<Long, ActivityInformation>{

    public List<ActivityInformation> activitiesList(String activityType, int distance, int duration, int radius, DateTime startDate, long time);


}
