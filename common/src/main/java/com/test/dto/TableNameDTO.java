package com.test.dto;

import java.io.Serializable;
import java.sql.Timestamp;

public class TableNameDTO implements Serializable {
    private Long tableNameId;
    private AreaNameDTO areaName;
    private String tableName;
    private Boolean status;
    private Timestamp createdDate;
    private Timestamp updatedDate;

    public Boolean getStatus() {
        return status;
    }

    public void setStatus(Boolean status) {
        this.status = status;
    }

    public Long getTableNameId() {
        return tableNameId;
    }

    public void setTableNameId(Long tableNameId) {
        this.tableNameId = tableNameId;
    }

    public String getTableName() {
        return tableName;
    }

    public void setTableName(String tableName) {
        this.tableName = tableName;
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

    public AreaNameDTO getAreaName() {
        return areaName;
    }

    public void setAreaName(AreaNameDTO areaName) {
        this.areaName = areaName;
    }
}
