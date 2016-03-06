package com.wicky.biz.web.taglib;

import com.wicky.biz.entity.OrganizationVO;
import com.wicky.biz.entity.ResourceVO;
import com.wicky.biz.entity.RoleVO;
import com.wicky.biz.service.IOrganizationService;
import com.wicky.biz.service.IResourceService;
import com.wicky.biz.service.IRoleService;
import com.wicky.mansys.util.SpringUtils;

import org.springframework.util.CollectionUtils;

import java.util.Collection;
import java.util.Set;

/**
 * <p>Date: 14-2-15
 * <p>Version: 1.0
 */
public class Functions {

    public static boolean in(Iterable iterable, Object element) {
        return iterable != null && CollectionUtils.contains(iterable.iterator(), element);
    }

    public static String organizationName(Long organizationId) {
        OrganizationVO organization = null;
        try {
            organization = getOrganizationService().findOne(organizationId);
        } catch (Exception e) {
            e.printStackTrace();
        }
        if(organization == null) {
            return "";
        }
        return organization.getName();
    }

    public static String organizationNames(Collection<Long> organizationIds) {
        if(CollectionUtils.isEmpty(organizationIds)) {
            return "";
        }

        StringBuilder s = new StringBuilder();
        for(Long organizationId : organizationIds) {
            OrganizationVO organization = null;
            try {
                organization = getOrganizationService().findOne(organizationId);
            } catch (Exception e) {
                e.printStackTrace();
            }
            if(organization == null) {
                return "";
            }
            s.append(organization.getName());
            s.append(",");
        }

        if(s.length() > 0) {
            s.deleteCharAt(s.length() - 1);
        }
        return s.toString();
    }
    public static String roleName(Long roleId) {
        RoleVO role = getRoleService().findOne(roleId);
        if(role == null) {
            return "";
        }
        return role.getDescription();
    }

    public static String roleNames(Collection<Long> roleIds) {
        if(CollectionUtils.isEmpty(roleIds)) {
            return "";
        }

        StringBuilder s = new StringBuilder();
        for(Long roleId : roleIds) {
            RoleVO role = getRoleService().findOne(roleId);
            if(role == null) {
                return "";
            }
            s.append(role.getDescription());
            s.append(",");
        }
        if(s.length() > 0) {
            s.deleteCharAt(s.length() - 1);
        }
        return s.toString();
    }
    public static String resourceName(Long resourceId) {
        ResourceVO resource = getResourceService().findOne(resourceId);
        if(resource == null) {
            return "";
        }
        return resource.getName();
    }
    public static String resourceNames(Set<ResourceVO> resourceList) {
        if(CollectionUtils.isEmpty(resourceList)) {
            return "";
        }

        StringBuilder s = new StringBuilder();
        for(ResourceVO resource : resourceList) {
            if(resource == null) {
                return "";
            }
            s.append(resource.getName());
            s.append(",");
        }

        if(s.length() > 0) {
            s.deleteCharAt(s.length() - 1);
        }
        return s.toString();
    }

    private static IOrganizationService organizationService;
    private static IRoleService roleService;
    private static IResourceService resourceService;

    public static IOrganizationService getOrganizationService() {
        if(organizationService == null) {
            organizationService = SpringUtils.getBean(IOrganizationService.class);
        }
        return organizationService;
    }

    public static IRoleService getRoleService() {
        if(roleService == null) {
            roleService = SpringUtils.getBean(IRoleService.class);
        }
        return roleService;
    }

    public static IResourceService getResourceService() {
        if(resourceService == null) {
            resourceService = SpringUtils.getBean(IResourceService.class);
        }
        return resourceService;
    }
}

