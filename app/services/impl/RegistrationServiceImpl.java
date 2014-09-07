package services.impl;

import models.Registration;
import org.springframework.stereotype.Service;
import services.service.RegistrationService;

/**
 * Created by mint on 4-9-14.
 */

@Service
public class RegistrationServiceImpl extends DefaultServiceImpl<Long, Registration> implements RegistrationService{

    public RegistrationServiceImpl() {
        super(Registration.class);
    }
}
