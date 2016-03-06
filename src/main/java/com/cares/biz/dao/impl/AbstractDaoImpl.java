package com.cares.biz.dao.impl;

import com.cares.biz.dao.AbstractDao;
import org.hibernate.criterion.DetachedCriteria;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate4.HibernateTemplate;

import java.io.Serializable;
import java.util.List;

public abstract class AbstractDaoImpl<E, I extends Serializable> implements AbstractDao<E, I> {

    private Class<E> entityClass;

    protected AbstractDaoImpl(Class<E> entityClass) {
        this.entityClass = entityClass;
    }

    @Autowired
    private HibernateTemplate hibernateTemplate;

    @Override
    public E findById(I id) {
        return hibernateTemplate.get(entityClass, id);
    }

    @Override
    public void save(E e) {
        hibernateTemplate.save(e);
    }

    public void update(E e) {
        hibernateTemplate.merge(e);
    }

    @Override
    public void delete(E e) {
        hibernateTemplate.delete(e);
    }

    @Override
    public List<E> findByCriteria(DetachedCriteria criteria) {
        hibernateTemplate.setCacheQueries(true);
        return (List<E>) hibernateTemplate.findByCriteria(criteria);
    }

    @Override
    public List<E> findAll() {
        DetachedCriteria criteria = DetachedCriteria.forClass(entityClass);
        return findByCriteria(criteria);
    }

}
