package com.wicky.biz.entity;

import org.hibernate.annotations.*;
import org.hibernate.annotations.Cache;

import com.wicky.biz.entity.common.BaseEntity;

import javax.persistence.*;
import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * <p>Date: 14-1-28
 * <p>Version: 1.0
 */
@Entity
@Table(name = "sys_organization")
@Cacheable
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
public class OrganizationVO extends BaseEntity  {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id; //编号
    @Column(name = "name")
    private String name; //组织机构名称
    @Column(name = "parent_id")
    private Long parentId; //父编号
    @Column(name = "parent_ids")
    private String parentIds; //父编号列表，如1/2/
    @Column(name = "available")
    private Boolean available = Boolean.FALSE;

    public OrganizationVO(Long id) {
        this.id = id;
    }

    public OrganizationVO() {
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        OrganizationVO that = (OrganizationVO) o;

        if (id != null ? !id.equals(that.id) : that.id != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        return id != null ? id.hashCode() : 0;
    }

    @Override
    public String toString() {
        return "Organization{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", parentId=" + parentId +
                ", parentIds='" + parentIds + '\'' +
                ", available=" + available +
                '}';
    }
}
