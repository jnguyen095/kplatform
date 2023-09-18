package com.test.session.impl;

import com.test.domain.UserEntity;
import com.test.session.UserLocalBean;
import org.apache.commons.lang.StringUtils;

import javax.ejb.Stateless;
import javax.persistence.Query;

/**
 * Created with IntelliJ IDEA.
 * User: nkhang
 * Date: 1/8/16
 * Time: 10:20 PM
 * To change this template use File | Settings | File Templates.
 */
@Stateless(name = "UserSessionEJB")
public class UserSessionBean extends AbstractSessionBean<UserEntity, Long> implements UserLocalBean {
    @Override
    public Boolean isDuplicated(String userName, Long retailerId, Long userId) {
        StringBuffer sql = new StringBuffer("FROM UserEntity ug WHERE ug.userName = :userName AND ug.retailer.retailerId = :retailerId");
        if(userId != null){
            sql.append(" AND ug.userId <> :userId");
        }
        Query query = entityManager.createQuery(sql.toString());
        query.setParameter("userName", userName);
        query.setParameter("retailerId", retailerId);
        if(userId != null){
            query.setParameter("userId", userId);
        }
        return query.getResultList().size() > 0;
    }

    @Override
    public UserEntity findByUserNameAndActive(String userName, String retailerCode) {
        StringBuffer sql = new StringBuffer("FROM UserEntity u WHERE u.userName = :userName AND u.status = :status");
        if(StringUtils.isNotBlank(retailerCode)){
            sql.append(" AND u.retailer.code = :retailerCode");
        }else{
            sql.append(" AND u.retailer.retailerId IS NULL ");
        }
        Query query = entityManager.createQuery(sql.toString());
        query.setParameter("userName", userName);
        query.setParameter("status", Boolean.TRUE);
        if(StringUtils.isNotBlank(retailerCode)){
            query.setParameter("retailerCode", retailerCode);
        }
        Object ob = query.getSingleResult();
        if(ob != null){
            return (UserEntity)ob;
        }
        return null;
    }
}
