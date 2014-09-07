package services.impl;

import models.GeneralNotification;
import org.springframework.stereotype.Service;
import services.service.GeneralNotificationService;

/**
 * Created by mint on 5-9-14.
 */
@Service
public class GeneralNotificationServiceImpl extends DefaultServiceImpl<Long, GeneralNotification> implements GeneralNotificationService {

    public GeneralNotificationServiceImpl() {
        super(GeneralNotification.class);
    }
}
