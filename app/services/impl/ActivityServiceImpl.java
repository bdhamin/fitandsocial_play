package services.impl;

import models.Activity;
import org.springframework.stereotype.Service;
import services.service.ActivityService;

/**
 * Created by mint on 5-9-14.
 */
@Service
public class ActivityServiceImpl extends DefaultServiceImpl<Long, Activity> implements ActivityService{

    public ActivityServiceImpl() {
        super(Activity.class);
    }
}
