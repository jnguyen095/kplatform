package com.test.domain;

import javax.persistence.*;
import java.sql.Timestamp;

@javax.persistence.Table(name = "tablename")
@Entity
public class TableNameEntity {
    private Long tableNameId;
    private AreaNameEntity areaName;
    private String tableName;
    private Boolean status;
    private Timestamp createdDate;
    private Timestamp modifiedDate;

    @Id
    @Column(name = "TableNameId")
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    public Long getTableNameId() {
        return tableNameId;
    }

    public void setTableNameId(Long tableNameId) {
        this.tableNameId = tableNameId;
    }

    @javax.persistence.Column(name = "TableName")
    @Basic
    public String getTableName() {
        return tableName;
    }

    public void setTableName(String tableName) {
        this.tableName = tableName;
    }

    @javax.persistence.Column(name = "Status")
    @Basic
    public Boolean getStatus() {
        return status;
    }

    public void setStatus(Boolean status) {
        this.status = status;
    }

    @javax.persistence.Column(name = "CreatedDate")
    @Basic
    public Timestamp getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(Timestamp createdDate) {
        this.createdDate = createdDate;
    }

    @javax.persistence.Column(name = "ModifiedDate")
    @Basic
    public Timestamp getModifiedDate() {
        return modifiedDate;
    }

    public void setModifiedDate(Timestamp modifiedDate) {
        this.modifiedDate = modifiedDate;
    }

    @ManyToOne
    @javax.persistence.JoinColumn(name = "AreaNameId")
    public AreaNameEntity getAreaName() {
        return areaName;
    }

    public void setAreaName(AreaNameEntity areaName) {
        this.areaName = areaName;
    }
}
