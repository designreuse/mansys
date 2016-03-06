package com.wicky.biz.dao;

import org.hibernate.criterion.DetachedCriteria;

import java.io.Serializable;
import java.util.List;

public interface AbstractDao<E, I extends Serializable> {

    E findById(I id);
    void save(E e);
    void delete(E e);
    List<E> findByCriteria(DetachedCriteria criterion);
    List<E> findAll();
    void update(E e);
}
