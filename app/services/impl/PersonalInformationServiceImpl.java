package services.impl;

import models.PersonalInformation;
import org.springframework.stereotype.Service;
import services.service.PersonalInformationService;

/**
 * Created by mint on 5-9-14.
 */
@Service
public class PersonalInformationServiceImpl extends DefaultServiceImpl<Long, PersonalInformation> implements PersonalInformationService {

    public PersonalInformationServiceImpl() {
        super(PersonalInformation.class);
    }
}
