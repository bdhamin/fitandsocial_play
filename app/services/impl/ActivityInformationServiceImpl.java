package services.impl;

import models.ActivityInformation;
import org.springframework.stereotype.Service;
import services.service.ActivityInformationService;

/**
 * Created by mint on 5-9-14.
 */
@Service
public class ActivityInformationServiceImpl extends DefaultServiceImpl<Long, ActivityInformation> implements ActivityInformationService{

    public ActivityInformationServiceImpl() {
        super(ActivityInformation.class);
    }
}
