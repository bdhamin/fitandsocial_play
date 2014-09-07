package services.impl;

import models.UserNotification;
import org.springframework.stereotype.Service;
import services.service.UserNotificationService;

/**
 * Created by mint on 5-9-14.
 */
@Service
public class UserNotificationServiceImpl extends DefaultServiceImpl<Long, UserNotification> implements UserNotificationService {

    public UserNotificationServiceImpl() {
        super(UserNotification.class);
    }
}
