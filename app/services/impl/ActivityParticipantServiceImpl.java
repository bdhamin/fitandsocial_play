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
}
