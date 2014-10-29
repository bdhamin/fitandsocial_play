package services.impl;

import models.GCM;
import org.springframework.stereotype.Service;
import services.service.GCMService;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 * Created by mint on 9-10-14.
 */
@Service
public class GCMServiceImpl extends DefaultServiceImpl<Long, GCM> implements GCMService {

    @PersistenceContext
    private EntityManager em;

    public GCMServiceImpl() {
        super(GCM.class);
    }
}
