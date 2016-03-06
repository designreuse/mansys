package com.cares.biz.service.impl;

import com.cares.biz.dao.RoleDao;
import com.cares.biz.entity.ResourceVO;
import com.cares.biz.service.IResourceService;
import com.cares.biz.service.IRoleService;
import com.cares.biz.entity.RoleVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;

import javax.annotation.Resource;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * <p>User: Zhang Kaitao
 * <p>Date: 14-1-28
 * <p>Version: 1.0
 */
@Service("roleService")
public class RoleServiceImpl implements IRoleService {

    @Autowired
    private RoleDao roleDao;
    @Resource(name = "resourceService")
    private IResourceService resourceService;

    public RoleVO createRole(RoleVO role) {
        if(StringUtils.hasText(role.getResourceIds())) {
            List<ResourceVO> resourceList = resourceService.findAll();
            resourceList.stream().filter(resourceVO -> ("," + role.getResourceIds() + ",").contains("," + resourceVO.getId() + ",")).forEach(resourceVO -> role.getResourceList().add(resourceVO));
        }
        return roleDao.createRole(role);
    }

    public RoleVO updateRole(RoleVO role) {
        if(StringUtils.hasText(role.getResourceIds())) {
            List<ResourceVO> resourceList = resourceService.findAll();
            resourceList.stream().filter(resourceVO -> ("," + role.getResourceIds() + ",").contains("," + resourceVO.getId() + ",")).forEach(resourceVO -> role.getResourceList().add(resourceVO));
        }
        return roleDao.updateRole(role);
    }

    public void deleteRole(Long roleId) {
        roleDao.deleteRole(roleId);
    }

    @Override
    public RoleVO findOne(Long roleId) {
        RoleVO role = roleDao.findOne(roleId);
        return role;
    }

    @Override
    public List<RoleVO> findAll() {
        List<RoleVO> list = roleDao.findAll();
        return list;
    }

    @Override
    public Set<String> findRoles(Long... roleIds) {
        Set<String> roles = new HashSet<>();
        for(Long roleId : roleIds) {
            RoleVO role = findOne(roleId);
            if(role != null) {
                roles.add(role.getRole());
            }
        }
        return roles;
    }

    @Override
    public Set<String> findPermissions(Long[] roleIds) {
        Set<Long> resourceIds = new HashSet<>();
        for(Long roleId : roleIds) {
            RoleVO role = findOne(roleId);
            resourceIds.addAll(role.getResourceList().stream().map(ResourceVO::getId).collect(Collectors.toList()));
        }
        return resourceService.findPermissions(resourceIds);
    }
}
