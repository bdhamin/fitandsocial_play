package services.impl;

import javassist.expr.Cast;
import models.Activity;
import models.Users;
import org.joda.time.DateTime;
import org.springframework.stereotype.Service;
import services.service.ActivityService;
import utils.DateAndTimePrintFormatter;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.util.Date;
import java.util.List;

/**
 * Created by mint on 5-9-14.
 */
@Service
public class ActivityServiceImpl extends DefaultServiceImpl<Long, Activity> implements ActivityService{

    @PersistenceContext
    private EntityManager em;


    public ActivityServiceImpl() {
        super(Activity.class);
    }

    @Override
    public List<Activity> findActivitiesByUserId(long id) {

        Query query = em.createQuery("SELECT a FROM Activity a WHERE a.user = " + id + " AND a.isOwner = true");
        return (List<Activity>) query.getResultList();
    }

    @Override
    public List<Activity> activitiesList(String activityType, int distance, int durationMin, int durationMax, int radiusMin, int radiusMax, long startDate, long time) {

//        String hql = "SELECT a FROM Activity a WHERE a.activityInformation.type = :activityType AND a.activityInformation.distance = :distance" +
//                " AND (a.activityInformation.durationMin >= :durationMin AND a.activityInformation.durationMax <= :durationMax)" +
//                " AND a.activityInformation.activityDate = :startDate AND a.activityInformation.activityTime = :startTime";

            String hql = "SELECT a FROM Activity a WHERE a.activityInformation.type = :activityType AND a.activityInformation.distance = :distance" +
            " AND( (a.activityInformation.durationMin >= :durationMin AND a.activityInformation.durationMax <= :durationMax) " +
            " OR a.activityInformation.durationMin = :durationMax OR a.activityInformation.durationMax = :durationMin)" +
            " AND a.activityInformation.activityDate = :startDate AND a.activityInformation.activityTime = :startTime";

        Query query = em.createQuery(hql);
        query.setParameter("activityType", activityType);
        query.setParameter("distance", distance);
        query.setParameter("durationMin", durationMin);
        query.setParameter("durationMax", durationMax);
        query.setParameter("startDate", startDate);
        query.setParameter("startTime", time);

        return (List<Activity>) query.getResultList();
    }

    @Override
    public List<Activity> getUserUpcomingActivities(String authenticationProviderId) {
        long currentDate = DateAndTimePrintFormatter.getCurrentDateAsLong();

        Users user = getUserByAuthenticationProviderId(authenticationProviderId);

        if(user != null){
        long userId = user.getId();
        String hql = "SELECT a FROM Activity a JOIN a.activityParticipants ap WHERE a.activityInformation.activityDate >= :currentDate " +
                     "AND ap.user.id=:userId AND ap.isParticipationActive=:isActive";

        Query query = em.createQuery(hql);
        query.setParameter("userId", userId);
        query.setParameter("currentDate", currentDate);
        query.setParameter("isActive", true);
            return (List<Activity>) query.getResultList();
        }else{
            return null;
        }

    }

    private Users getUserByAuthenticationProviderId(String authenticationKey) {

        String hql = "SELECT u FROM Users u WHERE u.authenticationProvider.providerKey=:authenticationKey";
        Query query = em.createQuery(hql);
        query.setParameter("authenticationKey", authenticationKey);
        return (Users)query.getResultList().get(0);
    }

    @Override
    public Activity getLastActivity(String authenticationProviderKey) {

        Users user = getUserByAuthenticationProviderId(authenticationProviderKey);
        if(user != null){
            long userId = user.getId();
            String hql = "SELECT a FROM Activity a JOIN a.activityParticipants ac WHERE ac.user.id = :userId";

            Query query = em.createQuery(hql);
            query.setParameter("userId", userId);
            List<Activity> activityList =  (List<Activity>) query.getResultList();
            if(activityList.size() > 0){
                return activityList.get(0);
            }else{
                return null;
            }
        }else {
            return null;
        }
    }
}
