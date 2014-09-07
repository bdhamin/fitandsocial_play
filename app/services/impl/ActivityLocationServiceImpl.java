package services.impl;

import models.ActivityLocation;
import org.springframework.stereotype.Service;
import services.service.ActivityLocationService;

/**
 * Created by mint on 5-9-14.
 */
@Service
public class ActivityLocationServiceImpl extends DefaultServiceImpl<Long, ActivityLocation> implements ActivityLocationService {

    public ActivityLocationServiceImpl() {
        super(ActivityLocation.class);
    }
}
