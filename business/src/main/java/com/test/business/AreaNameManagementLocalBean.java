package com.test.business;

import com.test.dto.AreaNameDTO;

import javax.ejb.DuplicateKeyException;
import javax.ejb.Local;
import javax.ejb.ObjectNotFoundException;
import java.util.List;
import java.util.Map;

@Local
public interface AreaNameManagementLocalBean {
    Object[] searchByProperties(Map<String, Object> properties, String sortExpression, String sortDirection, int firstItem, int maxPageItems);

    AreaNameDTO saveOrUpdate(AreaNameDTO pojo) throws DuplicateKeyException, ObjectNotFoundException;

    AreaNameDTO findById(Long areaNameId) throws ObjectNotFoundException;

    Boolean isDuplicated(String areaName, Long retailerId, Long areaNameId);

    List<AreaNameDTO> findAllByStatus(Long retailerId, Boolean status);
}
