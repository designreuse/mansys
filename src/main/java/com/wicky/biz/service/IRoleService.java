package com.wicky.biz.service;


import java.util.List;
import java.util.Set;

import com.wicky.biz.entity.RoleVO;

/**
 * <p>Date: 14-1-28
 * <p>Version: 1.0
 */
public interface IRoleService {


    RoleVO createRole(RoleVO role);
    RoleVO updateRole(RoleVO role);
    void deleteRole(Long roleId);

    RoleVO findOne(Long roleId);
    List<RoleVO> findAll();

    /**
     * 根据角色编号得到角色标识符列表
     * @param roleIds
     * @return
     */
    Set<String> findRoles(Long... roleIds);

    /**
     * 根据角色编号得到权限字符串列表
     * @param roleIds
     * @return
     */
    Set<String> findPermissions(Long[] roleIds);
}
