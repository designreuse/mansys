package com.cares.biz.service.impl;

import com.wicky.mansys.util.PasswordHelper;
import com.cares.biz.dao.UserDao;
import com.cares.biz.entity.RoleVO;
import com.cares.biz.service.IRoleService;
import com.cares.biz.service.IUserService;
import com.cares.biz.entity.UserVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * <p>User: Zhang Kaitao
 * <p>Date: 14-1-28
 * <p>Version: 1.0
 */
@Service("userService")
public class UserServiceImpl implements IUserService {

    @Autowired
    private UserDao userDao;
    @Autowired
    private IRoleService roleService;

    /**
     * 创建用户
     * @param user
     */
    public UserVO createUser(UserVO user) {
        //加密密码
        PasswordHelper.encryptPassword(user);
        List<RoleVO> roleList = roleService.findAll();
        roleList.stream().filter(role -> StringUtils.hasText(user.getRoleIds())).filter(role -> ("," + user.getRoleIds() + ",").contains("," + role.getId() + ",")).forEach(role -> user.getRoleList().add(role));
        return userDao.createUser(user);
    }

    @Override
    public UserVO updateUser(UserVO user) {
        List<RoleVO> roleList = roleService.findAll();
        roleList.stream().filter(role -> StringUtils.hasText(user.getRoleIds())).filter(role -> ("," + user.getRoleIds() + ",").contains("," + role.getId() + ",")).forEach(role -> user.getRoleList().add(role));
        return userDao.updateUser(user);
    }

    @Override
    public void deleteUser(Long userId) {
        userDao.deleteUser(userId);
    }

    /**
     * 修改密码
     * @param userId
     * @param newPassword
     */
    public void doChangePassword(Long userId, String newPassword) {
        UserVO user = findOne(userId);
        user.setPassword(newPassword);
        PasswordHelper.encryptPassword(user);
        updateUser(user);
    }

    @Override
    public UserVO findOne(Long userId) {
        return userDao.findOne(userId);
    }

    @Override
    public List<UserVO> findAll() {
        List<UserVO> list = userDao.findAll();
        return list;
    }

    /**
     * 根据用户名查找用户
     * @param username
     * @return
     */
    public UserVO findByUsername(String username) {
        UserVO user = userDao.findByUsername(username);
        return user;
    }

    /**
     * 根据用户名查找其角色
     * @param username
     * @return
     */
    public Set<String> findRoles(String username) {
        UserVO user = findByUsername(username);
        if(user == null) {
            return Collections.EMPTY_SET;
        }
        List<Long> list = user.getRoleList().stream().map(RoleVO::getId).collect(Collectors.toList());
        return roleService.findRoles(list.toArray(new Long[0]));
    }

    /**
     * 根据用户名查找其权限
     * @param username
     * @return
     */
    public Set<String> findPermissions(String username) {
        UserVO user = findByUsername(username);
        if(user == null) {
            return Collections.EMPTY_SET;
        }
        List<Long> list = user.getRoleList().stream().map(RoleVO::getId).collect(Collectors.toList());
        return roleService.findPermissions(list.toArray(new Long[0]));
    }

}
