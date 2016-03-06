package com.cares.biz.service;


import com.cares.biz.entity.UserVO;

import java.util.List;
import java.util.Set;

/**
 * <p>Date: 14-1-28
 * <p>Version: 1.0
 */
public interface IUserService {

    /**
     * 创建用户
     * @param user
     */
    UserVO createUser(UserVO user);

    UserVO updateUser(UserVO user);

    void deleteUser(Long userId);

    /**
     * 修改密码
     * @param userId
     * @param newPassword
     */
    void doChangePassword(Long userId, String newPassword);


    UserVO findOne(Long userId);

    List<UserVO> findAll();

    /**
     * 根据用户名查找用户
     * @param username
     * @return
     */
    UserVO findByUsername(String username);

    /**
     * 根据用户名查找其角色
     * @param username
     * @return
     */
    Set<String> findRoles(String username);

    /**
     * 根据用户名查找其权限
     * @param username
     * @return
     */
    Set<String> findPermissions(String username);

}
