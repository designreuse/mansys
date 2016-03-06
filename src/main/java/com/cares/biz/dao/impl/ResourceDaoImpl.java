package com.cares.biz.dao.impl;

import com.cares.biz.dao.ResourceDao;
import com.cares.biz.entity.ResourceVO;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Set;

@Repository
public class ResourceDaoImpl extends AbstractDaoImpl<ResourceVO, Long> implements ResourceDao {

    protected ResourceDaoImpl() {
        super(ResourceVO.class);
    }

    @Override
    public ResourceVO createResource(ResourceVO resource) {
        save(resource);
        return resource;
    }

    @Override
    public ResourceVO updateResource(ResourceVO resource) {
        update(resource);
        return resource;
    }

    @Override
    public void deleteResource(Long resourceId) {
        delete(findOne(resourceId));
    }

    @Override
    public ResourceVO findOne(Long resourceId) {
        return findById(resourceId);
    }

    @Override
    public Set<String> findPermissions(Set<Long> resourceIds) {
        return null;
    }

    @Override
    public List<ResourceVO> findMenus(Set<String> permissions) {
        return null;
    }
}
