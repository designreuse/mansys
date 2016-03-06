package com.wicky.biz.entity;

import javax.annotation.Generated;
import javax.persistence.metamodel.SetAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="Dali", date="2016-03-06T22:57:21.904+0800")
@StaticMetamodel(UserVO.class)
public class UserVO_ {
	public static volatile SingularAttribute<UserVO, Long> id;
	public static volatile SingularAttribute<UserVO, OrganizationVO> organization;
	public static volatile SingularAttribute<UserVO, String> username;
	public static volatile SingularAttribute<UserVO, String> password;
	public static volatile SingularAttribute<UserVO, String> salt;
	public static volatile SetAttribute<UserVO, RoleVO> roleList;
	public static volatile SingularAttribute<UserVO, Boolean> locked;
}
