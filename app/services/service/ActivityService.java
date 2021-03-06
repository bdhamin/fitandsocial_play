package services.service;

import models.Activity;
import org.joda.time.DateTime;

import java.util.List;

/**
 * Created by mint on 5-9-14.
 */
public interface ActivityService extends DefaultService<Long, Activity>{

    public List<Activity> findActivitiesByUserId(long id);

    public List<Activity> activitiesList(String activityType, int distance, int durationMin, int durationMax, int radiusMin, int radiusMax, long startDate, long time);

    public List<Activity> activitiesList_withUser(String user, String activityType, int distance, int durationMin, int durationMax, int radiusMin, int radiusMax, long startDate, long time);

    public List<Activity> getUserUpcomingActivities(String userId);

    public Activity getLastActivity(String authenticationProviderKey);

    public long getActivityOwner(long activityId);






}
