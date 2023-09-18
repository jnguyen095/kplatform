package com.test.domain;

import javax.persistence.*;
import java.sql.Timestamp;

@Table(name = "areaname")
@Entity
public class AreaNameEntity {
    private Long areaNameId;
    private RetailerEntity retailer;
    private String areaName;
    private Boolean status;
    private Timestamp createdDate;
    private Timestamp modifiedDate;

    @Id
    @Column(name = "AreaNameId")
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    public Long getAreaNameId() {
        return areaNameId;
    }

    public void setAreaNameId(Long areaNameId) {
        this.areaNameId = areaNameId;
    }

    @Column(name = "AreaName")
    @Basic
    public String getAreaName() {
        return areaName;
    }

    public void setAreaName(String areaName) {
        this.areaName = areaName;
    }

    @Column(name = "Status")
    @Basic
    public Boolean getStatus() {
        return status;
    }

    public void setStatus(Boolean status) {
        this.status = status;
    }

    @Column(name = "CreatedDate")
    @Basic
    public Timestamp getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(Timestamp createdDate) {
        this.createdDate = createdDate;
    }

    @Column(name = "ModifiedDate")
    @Basic
    public Timestamp getModifiedDate() {
        return modifiedDate;
    }

    public void setModifiedDate(Timestamp modifiedDate) {
        this.modifiedDate = modifiedDate;
    }

    @ManyToOne
    @javax.persistence.JoinColumn(name = "RetailerId")
    public RetailerEntity getRetailer() {
        return retailer;
    }

    public void setRetailer(RetailerEntity retailer) {
        this.retailer = retailer;
    }
}
