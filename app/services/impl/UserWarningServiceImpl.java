package services.impl;

import models.UserWarning;
import org.springframework.stereotype.Service;
import services.service.UserWarningService;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 * Created by mint on 24-9-14.
 */
@Service
public class UserWarningServiceImpl extends DefaultServiceImpl<Long, UserWarning>  implements UserWarningService{

    @PersistenceContext
    private EntityManager em;

    public UserWarningServiceImpl() {
        super(UserWarning.class);
    }
}
