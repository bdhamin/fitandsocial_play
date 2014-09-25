package services.impl;

import models.ActivityParticipant;
import org.springframework.stereotype.Service;
import services.service.ActivityParticipantService;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.util.List;

/**
 * Created by mint on 5-9-14.
 */
@Service
public class ActivityParticipantServiceImpl extends DefaultServiceImpl<Long, ActivityParticipant> implements ActivityParticipantService{

    @PersistenceContext
    private EntityManager em;

    public ActivityParticipantServiceImpl() {
        super(ActivityParticipant.class);
    }

    @Override
    public List<ActivityParticipant> getAllUserActivityParticipation(long id) {

        Query query = em.createQuery("SELECT ap FROM ActivityParticipant ap where ap.user = " + id);
        return (List<ActivityParticipant>) query.getResultList();
    }

    @Override
    public ActivityParticipant findActivityParticipation(long userId, long activityId) {

        String hql = "SELECT ap FROM ActivityParticipant ap JOIN ap.activities ac WHERE ap.user.id=:userId AND ac.id=:activityId AND ap.isParticipationActive=:isActive";

        Query query = em.createQuery(hql);
        query.setParameter("userId", userId);
        query.setParameter("activityId", activityId);
        query.setParameter("isActive", true);

        List<ActivityParticipant> activityParticipants = (List<ActivityParticipant>) query.getResultList();
        if(activityParticipants.size() > 0){
            return activityParticipants.get(0);
        }else{
            return null;
        }
    }

    @Override
    public List<ActivityParticipant> getUserCancelledActivitiesTotal(long userId) {

        String hql = "SELECT ap FROM ActivityParticipant ap WHERE ap.user.id =:userId AND ap.isParticipationActive=:isActive";
        Query query = em.createQuery(hql);
        query.setParameter("userId", userId);
        query.setParameter("isActive", false);
        return (List<ActivityParticipant>) query.getResultList();
    }
}
