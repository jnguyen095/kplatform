package com.test.session.impl;

import com.test.domain.RetailerEntity;
import com.test.session.RetailerLocalBean;

import javax.ejb.Stateless;
import javax.persistence.Query;
import java.util.List;

@Stateless(name = "RetailerSessionEJB")
public class RetailerSessionBean extends AbstractSessionBean<RetailerEntity, Long> implements RetailerLocalBean {
    @Override
    public Boolean isDuplicated(String retailerCode, Long id) {
        StringBuffer sql = new StringBuffer("FROM RetailerEntity r WHERE r.code = :code");
        if(id != null){
            sql.append(" AND r.retailerId <> :id");
        }
        Query query = entityManager.createQuery(sql.toString());
        query.setParameter("code", retailerCode);
        if(id != null){
            query.setParameter("id", id);
        }
        return query.getResultList().size() > 0;
    }

    @Override
    public List<RetailerEntity> findAllActive() {
        StringBuffer sql = new StringBuffer("FROM RetailerEntity r WHERE r.status = :status");
        Query query = entityManager.createQuery(sql.toString());
        query.setParameter("status", Boolean.TRUE);
        return query.getResultList();
    }
}
