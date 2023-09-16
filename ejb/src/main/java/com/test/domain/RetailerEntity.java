package com.test.domain;

import javax.persistence.Basic;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import java.sql.Timestamp;

/**
 * Created with IntelliJ IDEA.
 * User: nkhang
 * Date: 7/15/15
 * Time: 11:04 AM
 * To change this template use File | Settings | File Templates.
 */
@javax.persistence.Table(name = "retailer")
@Entity
public class RetailerEntity {
    private Long retailerId;
    private String name;
    private String code;
    private Boolean status;
    private String address;
    private String phone;
    private Timestamp createdDate;
    private Timestamp modifiedDate;

    @javax.persistence.Column(name = "RetailerId")
    @javax.persistence.Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    public Long getRetailerId() {
        return retailerId;
    }

    public void setRetailerId(Long retailerId) {
        this.retailerId = retailerId;
    }

    @javax.persistence.Column(name = "Status")
    @javax.persistence.Basic
    public Boolean getStatus() {
        return status;
    }

    public void setStatus(Boolean status) {
        this.status = status;
    }

    @javax.persistence.Column(name = "Code")
    @javax.persistence.Basic
    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    @javax.persistence.Column(name = "Name")
    @javax.persistence.Basic
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @javax.persistence.Column(name = "Address")
    @javax.persistence.Basic
    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    @javax.persistence.Column(name = "Phone")
    @javax.persistence.Basic
    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
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
}
