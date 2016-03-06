package com.wicky.biz.dao.impl;

import com.wicky.biz.dao.OrganizationDao;
import com.wicky.biz.entity.OrganizationVO;

import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class OrganizationDaoImpl extends AbstractDaoImpl<OrganizationVO, Long> implements OrganizationDao {

    protected OrganizationDaoImpl() {
        super(OrganizationVO.class);
    }

    @Override
    public OrganizationVO createOrganization(OrganizationVO organization) throws Exception {
        save(organization);
        return organization;
    }

    @Override
    public OrganizationVO updateOrganization(OrganizationVO organization) throws Exception {
        update(organization);
        return organization;
    }

    @Override
    public void deleteOrganization(Long organizationId) throws Exception {
        delete(findOne(organizationId));
    }

    @Override
    public OrganizationVO findOne(Long organizationId) {
        return findById(organizationId);
    }

    @Override
    public List<OrganizationVO> findAllWithExclude(OrganizationVO excludeOraganization) {
        DetachedCriteria criteria = DetachedCriteria.forClass(OrganizationVO.class);
        criteria.add(Restrictions.ne("id", excludeOraganization.getId()));
        criteria.add(Restrictions.sqlRestriction("parent_ids not like '" + excludeOraganization.makeSelfAsParentIds() + "%'"));
        return findByCriteria(criteria);
    }

    @Override
    public void move(OrganizationVO source, OrganizationVO target) {
        OrganizationVO organization = findOne(source.getId());
        organization.setParentId(target.getId());
        organization.setParentIds(target.getParentIds());
        update(organization);
        DetachedCriteria criteria = DetachedCriteria.forClass(OrganizationVO.class);
        criteria.add(Restrictions.like("parentIds", source.makeSelfAsParentIds() + "%"));
        List<OrganizationVO> result = findByCriteria(criteria);
        for (OrganizationVO org : result) {
            org.setParentIds(target.makeSelfAsParentIds() + org.getParentIds().substring(source.makeSelfAsParentIds().length()));
            update(org);
        }
    }
}
