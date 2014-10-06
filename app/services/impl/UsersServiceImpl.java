package services.impl;

import models.Users;
import org.springframework.stereotype.Service;
import services.service.UsersService;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

/**
 * Created by mint on 5-9-14.
 */
@Service
public class UsersServiceImpl extends DefaultServiceImpl<Long, Users> implements UsersService {

    @PersistenceContext
    EntityManager em;

    public UsersServiceImpl() {
        super(Users.class);
    }

    @Override
    public Users getUserByAuthenticationProvider(String authenticationProviderKey) {

        String hql = "SELECT u FROM Users u WHERE u.authenticationProvider.providerKey=:authenticationProviderKey";
        Query query = em.createQuery(hql);
        query.setParameter("authenticationProviderKey", authenticationProviderKey);
        return (Users)query.getResultList().get(0);
    }
}
