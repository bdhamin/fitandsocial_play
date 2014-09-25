package services.service;

import models.ActivityParticipant;

import java.util.List;

/**
 * Created by mint on 5-9-14.
 */
public interface ActivityParticipantService extends DefaultService<Long, ActivityParticipant>{

    public List<ActivityParticipant> getAllUserActivityParticipation(long id);

    public ActivityParticipant findActivityParticipation(long userId, long activityId);

    public List<ActivityParticipant> getUserCancelledActivitiesTotal(long userId);

}
