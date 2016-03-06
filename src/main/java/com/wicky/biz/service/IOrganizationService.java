package com.wicky.biz.service;


import java.util.List;

import com.wicky.biz.entity.OrganizationVO;

/**
 * <p>User: Zhang Kaitao
 * <p>Date: 14-1-28
 * <p>Version: 1.0
 */
public interface IOrganizationService {
    OrganizationVO createOrganization(OrganizationVO organization) throws Exception;

    OrganizationVO updateOrganization(OrganizationVO organization) throws Exception;

    void deleteOrganization(Long organizationId) throws Exception;

    OrganizationVO findOne(Long organizationId) throws Exception;

    List<OrganizationVO> findAll();

    List<OrganizationVO> findAllWithExclude(OrganizationVO excludeOraganization);

    void doMove(OrganizationVO source, OrganizationVO target);
}
