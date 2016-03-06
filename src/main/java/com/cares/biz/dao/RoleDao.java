package com.cares.biz.dao;

import com.cares.biz.entity.RoleVO;

import java.util.List;

/**
 * Created by YangChao on 2015/6/2.
 */
public interface RoleDao extends AbstractDao<RoleVO, Long>{
    RoleVO createRole(RoleVO role);
    RoleVO updateRole(RoleVO role);
    void deleteRole(Long roleId);

    RoleVO findOne(Long roleId);
    List<RoleVO> findAll();

}
