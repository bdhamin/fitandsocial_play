package services.impl;

import models.UserGCM;
import org.springframework.stereotype.Service;
import services.service.GCMUserService;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 * Created by mint on 9-10-14.
 */

@Service
public class GCMUserServiceImpl extends DefaultServiceImpl<Long, UserGCM> implements GCMUserService{

    @PersistenceContext
    private EntityManager em;

    public GCMUserServiceImpl() {
        super(UserGCM.class);
    }
}
