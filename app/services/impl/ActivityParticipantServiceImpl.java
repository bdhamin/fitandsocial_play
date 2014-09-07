package services.impl;

import models.ActivityParticipant;
import org.springframework.stereotype.Service;
import services.service.ActivityParticipantService;

/**
 * Created by mint on 5-9-14.
 */
@Service
public class ActivityParticipantServiceImpl extends DefaultServiceImpl<Long, ActivityParticipant> implements ActivityParticipantService{

    public ActivityParticipantServiceImpl() {
        super(ActivityParticipant.class);
    }
}
