package services.impl;

import models.GeneralUserInformation;
import org.springframework.stereotype.Service;
import services.service.GeneralUserInformationService;

/**
 * Created by mint on 5-9-14.
 */
@Service
public class GeneralUserInformationServiceImpl extends DefaultServiceImpl<Long, GeneralUserInformation> implements GeneralUserInformationService{


    public GeneralUserInformationServiceImpl() {
        super(GeneralUserInformation.class);
    }
}
