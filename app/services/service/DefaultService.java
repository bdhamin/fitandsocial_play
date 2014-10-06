package services.service;

import models.Users;

import java.util.List;

/**
 * Created by mint on 4-9-14.
 */
public interface DefaultService <I, T>{

    public void persist(T o);
    public T merge(T o);
    public void deleteById(I id);
    public List<T> getAll();
    public T getById(I id);

}
