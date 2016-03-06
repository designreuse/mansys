package com.wicky.biz.entity;

import javax.annotation.Generated;
import javax.persistence.metamodel.SetAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="Dali", date="2016-03-06T22:57:21.903+0800")
@StaticMetamodel(RoleVO.class)
public class RoleVO_ {
	public static volatile SingularAttribute<RoleVO, Long> id;
	public static volatile SingularAttribute<RoleVO, String> role;
	public static volatile SingularAttribute<RoleVO, String> description;
	public static volatile SingularAttribute<RoleVO, Boolean> available;
	public static volatile SetAttribute<RoleVO, UserVO> userList;
	public static volatile SetAttribute<RoleVO, ResourceVO> resourceList;
}
