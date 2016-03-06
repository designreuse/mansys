package com.wicky.biz.dao;

import java.util.List;

import com.wicky.biz.entity.OrganizationVO;

/**
 * Created by YangChao on 2015/6/2.
 */
public interface OrganizationDao extends AbstractDao<OrganizationVO, Long>{
    OrganizationVO createOrganization(OrganizationVO organization) throws Exception;
    OrganizationVO updateOrganization(OrganizationVO organization) throws Exception;
    void deleteOrganization(Long organizationId) throws Exception;

    OrganizationVO findOne(Long organizationId);

    List<OrganizationVO> findAllWithExclude(OrganizationVO excludeOraganization);

    void move(OrganizationVO source, OrganizationVO target);
}
