package com.wicky.biz.entity;

import org.hibernate.annotations.*;
import org.hibernate.annotations.Cache;

import com.wicky.biz.entity.common.BaseEntity;

import javax.persistence.*;
import javax.persistence.Entity;
import javax.persistence.Table;
import java.util.Set;

/**
 * <p>Date: 14-1-28
 * <p>Version: 1.0
 */
@Entity
@Table(name = "sys_resource")
@Cacheable
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
public class ResourceVO extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id; //编号
    @Column(name = "name")
    private String name; //资源名称
    @Column(name = "type")
    private String type; //资源类型
    @Column(name = "url")
    private String url; //资源路径
    @Column(name = "permission")
    private String permission; //权限字符串
    @Column(name = "parent_id")
    private Long parentId; //父编号
    @Column(name = "parent_ids")
    private String parentIds; //父编号列表
    @Column(name = "available")
    private Boolean available = Boolean.FALSE;
    @ManyToMany(mappedBy = "resourceList")
    private Set<RoleVO> roleList;


    public enum ResourceType {
        menu("菜单"), button("按钮");
        private final String info;
        ResourceType(String info) {
            this.info = info;
        }
        public String getInfo() {
            return info;
        }
    }


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getPermission() {
        return permission;
    }

    public void setPermission(String permission) {
        this.permission = permission;
    }

    public Long getParentId() {
        return parentId;
    }

    public void setParentId(Long parentId) {
        this.parentId = parentId;
    }

    public String getParentIds() {
        return parentIds;
    }

    public void setParentIds(String parentIds) {
        this.parentIds = parentIds;
    }

    public Boolean getAvailable() {
        return available;
    }

    public void setAvailable(Boolean available) {
        this.available = available;
    }

    public boolean isRootNode() {
        return parentId == 0;
    }

    public String makeSelfAsParentIds() {
        return getParentIds() + getId() + "/";
    }


    public Set<RoleVO> getRoleList() {
        return roleList;
    }

    public void setRoleList(Set<RoleVO> roleList) {
        this.roleList = roleList;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        ResourceVO resource = (ResourceVO) o;

        if (id != null ? !id.equals(resource.id) : resource.id != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        return id != null ? id.hashCode() : 0;
    }

    @Override
    public String toString() {
        return "Resource{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", type=" + type +
                ", permission='" + permission + '\'' +
                ", parentId=" + parentId +
                ", parentIds='" + parentIds + '\'' +
                ", available=" + available +
                '}';
    }
}
