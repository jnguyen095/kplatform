package com.test.session.impl;

import com.test.domain.AreaNameEntity;
import com.test.session.AreaNameLocalBean;

import javax.ejb.Stateless;
import javax.persistence.Query;
import java.util.List;

@Stateless(name = "AreaNameSessionEJB")
public class AreaNameLocalBeanImpl extends AbstractSessionBean<AreaNameEntity, Long> implements AreaNameLocalBean {
    @Override
    public Boolean isDuplicated(String areaName, Long retailerId, Long areaNameId) {
        StringBuffer sql = new StringBuffer("FROM AreaNameEntity an WHERE an.areaName = :areaName AND an.retailer.retailerId = :retailerId");
        if(areaNameId != null){
            sql.append(" AND an.areaNameId <> :areaNameId");
        }
        Query query = entityManager.createQuery(sql.toString());
        query.setParameter("areaName", areaName);
        query.setParameter("retailerId", retailerId);
        if(areaNameId != null){
            query.setParameter("areaNameId", areaNameId);
        }
        return query.getResultList().size() > 0;
    }

    @Override
    public List<AreaNameEntity> findByRetailerAndStatus(Long retailerId, Boolean status) {
        StringBuffer sql = new StringBuffer("FROM AreaNameEntity an WHERE an.retailer.retailerId = :retailerId AND an.status = :status ORDER BY an.areaName ASC");
        Query query = entityManager.createQuery(sql.toString());
        query.setParameter("retailerId", retailerId);
        query.setParameter("status", status);
        return query.getResultList();
    }
}
