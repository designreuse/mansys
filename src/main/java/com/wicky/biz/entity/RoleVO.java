package com.wicky.biz.entity;

import org.hibernate.annotations.*;
import org.hibernate.annotations.Cache;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;

import com.wicky.biz.entity.common.BaseEntity;

import javax.persistence.*;
import javax.persistence.Entity;
import javax.persistence.Table;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * <p>Date: 14-1-28
 * <p>Version: 1.0
 */
@Entity
@Table(name = "sys_role")
@Cacheable
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
public class RoleVO extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id; //编号
    @Column(name = "role")
    private String role; //角色标识 程序中判断使用,如"admin"
    @Column(name = "description")
    private String description; //角色描述,UI界面显示使用
    @Column(name = "available")
    private Boolean available = Boolean.FALSE; //是否可用,如果不可用将不会添加给用户
    @ManyToMany(mappedBy = "roleList")
    private Set<UserVO> userList;
    @ManyToMany
    @JoinTable(
            name = "sys_role_resource",
            joinColumns = @JoinColumn(name = "role_id"),
            inverseJoinColumns = @JoinColumn(name = "resource_id")
    )
    @Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
    private Set<ResourceVO> resourceList; //拥有的资源

    @Transient
    private String resourceIds;

    public RoleVO() {
    }

    public RoleVO(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Set<UserVO> getUserList() {
        return userList;
    }

    public void setUserList(Set<UserVO> userList) {
        this.userList = userList;
    }

    public Set<ResourceVO> getResourceList() {
        if(resourceList == null) {
            resourceList = new HashSet<>();
        }
        return resourceList;
    }

    public void setResourceList(Set<ResourceVO> resourceList) {
        this.resourceList = resourceList;
    }

    public Boolean getAvailable() {
        return available;
    }

    public void setAvailable(Boolean available) {
        this.available = available;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        RoleVO role = (RoleVO) o;

        if (id != null ? !id.equals(role.id) : role.id != null) return false;

        return true;
    }

    public String getResourceIds() {
        return resourceIds;
    }

    public void setResourceIds(String resourceIds) {
        this.resourceIds = resourceIds;
    }

    @Override
    public int hashCode() {
        return id != null ? id.hashCode() : 0;
    }

}
