package com.test.domain;

import javax.persistence.*;
import java.sql.Timestamp;

/**
 * Created with IntelliJ IDEA.
 * User: nkhang
 * Date: 10/31/15
 * Time: 12:44 PM
 * To change this template use File | Settings | File Templates.
 */
@javax.persistence.Table(name = "us3r")
@Entity
public class UserEntity {
    private Long userId;
    private UserGroupEntity userGroup;
    private RetailerEntity retailer;
    private String userName;
    private String password;
    private String email;
    private String phone;
    private Timestamp createdDate;
    private Timestamp updatedDate;
    private Boolean status;

    @javax.persistence.Column(name = "Us3rID")
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    @ManyToOne
    @javax.persistence.JoinColumn(name = "UserGroupID")
    public UserGroupEntity getUserGroup() {
        return userGroup;
    }

    public void setUserGroup(UserGroupEntity userGroup) {
        this.userGroup = userGroup;
    }

    @javax.persistence.Column(name = "UserName")
    @Basic
    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    @javax.persistence.Column(name = "Password")
    @Basic
    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @javax.persistence.Column(name = "Email")
    @Basic
    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @javax.persistence.Column(name = "Phone")
    @Basic
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
    public Timestamp getUpdatedDate() {
        return updatedDate;
    }

    public void setUpdatedDate(Timestamp updatedDate) {
        this.updatedDate = updatedDate;
    }

    @javax.persistence.Column(name = "Status")
    @Basic
    public Boolean getStatus() {
        return status;
    }

    public void setStatus(Boolean status) {
        this.status = status;
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
