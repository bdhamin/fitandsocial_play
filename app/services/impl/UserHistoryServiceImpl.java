package services.impl;

import models.UserHistory;
import org.springframework.stereotype.Service;
import services.service.UserHistoryService;

/**
 * Created by mint on 5-9-14.
 */
@Service
public class UserHistoryServiceImpl extends DefaultServiceImpl<Long, UserHistory> implements UserHistoryService{

    public UserHistoryServiceImpl() {
        super(UserHistory.class);
    }
}
