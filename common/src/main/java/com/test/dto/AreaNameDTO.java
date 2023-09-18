package com.test.dto;

import java.io.Serializable;
import java.sql.Timestamp;

public class AreaNameDTO implements Serializable {
    private Long areaNameId;
    private RetailerDTO retailer;
    private String areaName;
    private Boolean status;
    private Timestamp createdDate;
    private Timestamp updatedDate;

    public Long getAreaNameId() {
        return areaNameId;
    }

    public void setAreaNameId(Long areaNameId) {
        this.areaNameId = areaNameId;
    }

    public String getAreaName() {
        return areaName;
    }

    public void setAreaName(String areaName) {
        this.areaName = areaName;
    }

    public Boolean getStatus() {
        return status;
    }

    public void setStatus(Boolean status) {
        this.status = status;
    }

    public Timestamp getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(Timestamp createdDate) {
        this.createdDate = createdDate;
    }

    public Timestamp getUpdatedDate() {
        return updatedDate;
    }

    public void setUpdatedDate(Timestamp updatedDate) {
        this.updatedDate = updatedDate;
    }

    public RetailerDTO getRetailer() {
        return retailer;
    }

    public void setRetailer(RetailerDTO retailer) {
        this.retailer = retailer;
    }
}
