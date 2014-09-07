package services.impl;

import models.AuthenticationProvider;
import org.springframework.stereotype.Service;
import services.service.AuthenticationProviderService;

/**
 * Created by mint on 5-9-14.
 */
@Service
public class AuthenticationProviderServiceImpl extends DefaultServiceImpl<Long, AuthenticationProvider> implements AuthenticationProviderService{

    public AuthenticationProviderServiceImpl() {
        super(AuthenticationProvider.class);
    }
}
