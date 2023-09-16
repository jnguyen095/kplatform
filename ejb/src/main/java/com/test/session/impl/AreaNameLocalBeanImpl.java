package com.test.session.impl;

import com.test.domain.AreaNameEntity;
import com.test.session.AreaNameLocalBean;

import javax.ejb.Stateless;
import javax.persistence.Query;

@Stateless(name = "AreaNameSessionEJB")
public class AreaNameLocalBeanImpl extends AbstractSessionBean<AreaNameEntity, Long> implements AreaNameLocalBean {
    @Override
    public Boolean isDuplicated(String areaName, Long id) {
        StringBuffer sql = new StringBuffer("FROM AreaNameEntity an WHERE an.areaName = :areaName");
        if(id != null){
            sql.append(" AND an.areaNameId <> :id");
        }
        Query query = entityManager.createQuery(sql.toString());
        query.setParameter("areaName", areaName);
        if(id != null){
            query.setParameter("id", id);
        }
        return query.getResultList().size() > 0;
    }
}
