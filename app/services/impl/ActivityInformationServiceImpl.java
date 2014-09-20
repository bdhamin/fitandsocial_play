package services.impl;

import models.Activity;
import models.ActivityInformation;
import org.joda.time.DateTime;
import org.springframework.stereotype.Service;
import services.service.ActivityInformationService;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.util.List;

/**
 * Created by mint on 5-9-14.
 */
@Service
public class ActivityInformationServiceImpl extends DefaultServiceImpl<Long, ActivityInformation> implements ActivityInformationService{

    @PersistenceContext
    EntityManager em;


    public ActivityInformationServiceImpl() {
        super(ActivityInformation.class);
    }

    @Override
    public List<ActivityInformation> activitiesList(String activityType, int distance, int duration, int radius, DateTime startDate, long time) {

        Query query = em.createQuery("SELECT a FROM ActivityInformation a WHERE " +
                "a.activityInformation.type= " + activityType + " AND a.activityInformation.distance= " + distance +
                " AND a.activityInformation.duration =" + duration + " AND a.activityInformation.activityTime= " + time);
        return (List<ActivityInformation>) query.getResultList();
    }
}
