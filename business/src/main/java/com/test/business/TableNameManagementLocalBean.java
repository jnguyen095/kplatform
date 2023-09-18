package com.test.business;

import com.test.dto.TableNameDTO;

import javax.ejb.DuplicateKeyException;
import javax.ejb.Local;
import javax.ejb.ObjectNotFoundException;
import java.util.Map;

@Local
public interface TableNameManagementLocalBean {
    Object[] searchByProperties(Map<String,Object> properties, String sortExpression, String sortDirection, int firstItem, int maxPageItems);

    TableNameDTO saveOrUpdate(TableNameDTO pojo) throws DuplicateKeyException, ObjectNotFoundException;

    TableNameDTO findById(Long tableNameId) throws ObjectNotFoundException;

    Boolean isDuplicated(String tableName, Long areaNameId, Long tableNameId);
}
