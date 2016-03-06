package com.wicky.biz.service;


import java.util.List;
import java.util.Set;

import com.wicky.biz.entity.ResourceVO;

/**
 * <p>User: Zhang Kaitao
 * <p>Date: 14-1-28
 * <p>Version: 1.0
 */
public interface IResourceService {


    ResourceVO createResource(ResourceVO resource);
    ResourceVO updateResource(ResourceVO resource);
    void deleteResource(Long resourceId);

    ResourceVO findOne(Long resourceId);
    List<ResourceVO> findAll();

    /**
     * 得到资源对应的权限字符串
     * @param resourceIds
     * @return
     */
    Set<String> findPermissions(Set<Long> resourceIds);

    /**
     * 根据用户权限得到菜单
     * @param permissions
     * @return
     */
    List<ResourceVO> findMenus(Set<String> permissions);
}
