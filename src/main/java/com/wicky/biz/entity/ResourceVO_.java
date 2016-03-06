package com.wicky.biz.entity;

import javax.annotation.Generated;
import javax.persistence.metamodel.SetAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="Dali", date="2016-03-06T22:57:21.901+0800")
@StaticMetamodel(ResourceVO.class)
public class ResourceVO_ {
	public static volatile SingularAttribute<ResourceVO, Long> id;
	public static volatile SingularAttribute<ResourceVO, String> name;
	public static volatile SingularAttribute<ResourceVO, String> type;
	public static volatile SingularAttribute<ResourceVO, String> url;
	public static volatile SingularAttribute<ResourceVO, String> permission;
	public static volatile SingularAttribute<ResourceVO, Long> parentId;
	public static volatile SingularAttribute<ResourceVO, String> parentIds;
	public static volatile SingularAttribute<ResourceVO, Boolean> available;
	public static volatile SetAttribute<ResourceVO, RoleVO> roleList;
}
