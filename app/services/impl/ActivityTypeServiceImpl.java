package services.impl;

import models.ActivityType;
import org.springframework.stereotype.Service;
import services.service.ActivityTypeService;

/**
 * Created by mint on 5-9-14.
 */
@Service
public class ActivityTypeServiceImpl extends DefaultServiceImpl<Long, ActivityType> implements ActivityTypeService {

    public ActivityTypeServiceImpl() {
        super(ActivityType.class);
    }
}
