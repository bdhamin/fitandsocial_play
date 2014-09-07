package services.impl;

import models.Users;
import org.springframework.stereotype.Service;
import services.service.UsersService;

/**
 * Created by mint on 5-9-14.
 */
@Service
public class UsersServiceImpl extends DefaultServiceImpl<Long, Users> implements UsersService {

    public UsersServiceImpl() {
        super(Users.class);
    }
}
