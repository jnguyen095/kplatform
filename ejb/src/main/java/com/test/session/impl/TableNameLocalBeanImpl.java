package com.test.session.impl;

import com.test.domain.StreetEntity;
import com.test.domain.TableNameEntity;
import com.test.session.TableNameLocalBean;

import javax.ejb.Stateless;
import javax.persistence.Query;

@Stateless(name = "TableNameSessionEJB")
public class TableNameLocalBeanImpl extends AbstractSessionBean<TableNameEntity, Long> implements TableNameLocalBean {
    @Override
    public Boolean isDuplicated(String tableName, Long areaNameId, Long tableNameId) {
        StringBuffer sql = new StringBuffer("FROM TableNameEntity tn WHERE tn.tableName = :tableName AND tn.areaName.areaName.areaNameId = :areaNameId");
        if(tableNameId != null){
            sql.append(" AND tn.tableNameId <> :tableNameId");
        }
        Query query = entityManager.createQuery(sql.toString());
        query.setParameter("tableName", tableName);
        query.setParameter("areaNameId", areaNameId);
        if(tableNameId != null){
            query.setParameter("tableNameId", tableNameId);
        }
        return query.getResultList().size() > 0;
    }
}
