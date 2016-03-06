package com.cares.biz.service.impl;

import com.cares.biz.dao.OrganizationDao;
import com.cares.biz.service.IOrganizationService;
import com.cares.biz.entity.OrganizationVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OrganizationServiceImpl implements IOrganizationService {
    @Autowired
    private OrganizationDao organizationDao;


    @Override
    public OrganizationVO createOrganization(OrganizationVO organization) throws Exception {
        return organizationDao.createOrganization(organization);
    }

    @Override
    public OrganizationVO updateOrganization(OrganizationVO organization) throws Exception {
        return organizationDao.updateOrganization(organization);
    }

    @Override
    public void deleteOrganization(Long organizationId) throws Exception {
        organizationDao.deleteOrganization(organizationId);
    }

    @Override
    public OrganizationVO findOne(Long organizationId) throws Exception{
        return organizationDao.findOne(organizationId);
    }

    @Override
    public List<OrganizationVO> findAll() {
        return organizationDao.findAll();
    }

    @Override
    public List<OrganizationVO> findAllWithExclude(OrganizationVO excludeOraganization) {
        return organizationDao.findAllWithExclude(excludeOraganization);
    }

    @Override
    public void doMove(OrganizationVO source, OrganizationVO target) {
        organizationDao.move(source, target);
    }
}
