package com.wicky.biz.service.impl;

import com.wicky.biz.dao.ResourceDao;
import com.wicky.biz.entity.ResourceVO;
import com.wicky.biz.service.IResourceService;

import org.apache.shiro.authz.permission.WildcardPermission;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * <p>User: Zhang Kaitao
 * <p>Date: 14-2-14
 * <p>Version: 1.0
 */
@Service("resourceService")
public class ResourceServiceImpl implements IResourceService {
    @Autowired
    private ResourceDao resourceDao;

    @Override
    public ResourceVO createResource(ResourceVO resource) {
        return resourceDao.createResource(resource);
    }

    @Override
    public ResourceVO updateResource(ResourceVO resource) {
        return resourceDao.updateResource(resource);
    }

    @Override
    public void deleteResource(Long resourceId) {
        resourceDao.deleteResource(resourceId);
    }

    @Override
    public ResourceVO findOne(Long resourceId) {
        return resourceDao.findOne(resourceId);
    }

    @Override
    public List<ResourceVO> findAll() {
        return resourceDao.findAll();
    }

    @Override
    public Set<String> findPermissions(Set<Long> resourceIds) {
        Set<String> permissions = new HashSet<>();
        for (Long resourceId : resourceIds) {
            ResourceVO resource = findOne(resourceId);
            if (resource != null && !StringUtils.isEmpty(resource.getPermission())) {
                permissions.add(resource.getPermission());
            }
        }
        return permissions;
    }

    @Override
    public List<ResourceVO> findMenus(Set<String> permissions) {
        List<ResourceVO> allResources = findAll();
        List<ResourceVO> menus = new ArrayList<>();
        for (ResourceVO resource : allResources) {
            if (resource.isRootNode()) {
                continue;
            }
            if (!resource.getType().equals("menu")) {
                continue;
            }
            if (!hasPermission(permissions, resource)) {
                continue;
            }
            menus.add(resource);
        }
        return menus;
    }

    private boolean hasPermission(Set<String> permissions, ResourceVO resource) {
        if (StringUtils.isEmpty(resource.getPermission())) {
            return true;
        }
        for (String permission : permissions) {
            WildcardPermission p1 = new WildcardPermission(permission);
            WildcardPermission p2 = new WildcardPermission(resource.getPermission());
            if (p1.implies(p2) || p2.implies(p1)) {
                return true;
            }
        }
        return false;
    }
}
