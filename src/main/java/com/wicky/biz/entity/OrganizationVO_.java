package com.wicky.biz.entity;

import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="Dali", date="2016-03-06T22:57:21.778+0800")
@StaticMetamodel(OrganizationVO.class)
public class OrganizationVO_ {
	public static volatile SingularAttribute<OrganizationVO, Long> id;
	public static volatile SingularAttribute<OrganizationVO, String> name;
	public static volatile SingularAttribute<OrganizationVO, Long> parentId;
	public static volatile SingularAttribute<OrganizationVO, String> parentIds;
	public static volatile SingularAttribute<OrganizationVO, Boolean> available;
}
