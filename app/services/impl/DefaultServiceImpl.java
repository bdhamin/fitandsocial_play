package services.impl;

import org.springframework.transaction.annotation.Transactional;
import services.service.DefaultService;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.util.List;

/**
 * Created by mint on 4-9-14.
 */

public class DefaultServiceImpl<I, T> implements DefaultService<I, T> {

    @PersistenceContext
    protected EntityManager em;

    private final Class<T> entClass;

    public DefaultServiceImpl(Class<T> entClass){
        this.entClass = entClass;
    }

    @Transactional
    @Override
    public void persist(T o) {
        em.persist(o);
    }

    @Transactional
    @Override
    public T merge(T o){
        return em.merge(o);
    }

    @Transactional
    @Override
    public void deleteById(I id) {
        T entity = em.find(entClass, id);
        em.remove(entity);
    }

    @Override
    public List<T> getAll() {
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<T> query = cb.createQuery(entClass);
        Root<T> root = query.from(entClass);
        return em.createQuery(query.select(root)).getResultList();
    }

    @Override
    public T getById(I id) {
        return em.find(entClass, id);
    }
}
