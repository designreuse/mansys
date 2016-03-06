package com.cares.biz.dao.impl;

import com.cares.biz.dao.UserDao;
import com.cares.biz.entity.UserVO;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

@Repository
public class UserDaoImpl extends AbstractDaoImpl<UserVO, Long> implements UserDao {

    protected UserDaoImpl() {
        super(UserVO.class);
    }

    @Override
    public UserVO createUser(UserVO user) {
        save(user);
        return user;
    }

    @Override
    public UserVO updateUser(UserVO user) {
        update(user);
        return user;
    }

    @Override
    public void deleteUser(Long userId) {
        delete(findOne(userId));
    }


    @Override
    public UserVO findOne(Long userId) {
        return findById(userId);
    }

    @Override
    public UserVO findByUsername(String username) {
        DetachedCriteria criteria = DetachedCriteria.forClass(UserVO.class);
        criteria.add(Restrictions.eq("username", username));
        return findByCriteria(criteria).get(0);
    }

}
