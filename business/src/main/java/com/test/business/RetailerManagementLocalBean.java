package com.test.business;

import com.test.dto.RetailerDTO;

import javax.ejb.DuplicateKeyException;
import javax.ejb.Local;
import javax.ejb.ObjectNotFoundException;
import java.util.List;
import java.util.Map;

@Local
public interface RetailerManagementLocalBean {
    Boolean isDuplicated(String retailerCode, Long id);

    Object[] searchByProperties(Map<String, Object> properties, String sortExpression, String sortDirection, int firstItem, int maxPageItems);

    RetailerDTO saveOrUpdate(RetailerDTO pojo) throws DuplicateKeyException, ObjectNotFoundException;

    RetailerDTO findById(Long retailerId) throws ObjectNotFoundException;

    List<RetailerDTO> findAllActive();
}
