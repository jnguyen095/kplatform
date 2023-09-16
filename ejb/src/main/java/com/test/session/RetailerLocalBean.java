package com.test.session;

import com.test.domain.RetailerEntity;

import javax.ejb.Local;

@Local
public interface RetailerLocalBean extends GenericSessionBean<RetailerEntity, Long>{
    Boolean isDuplicated(String retailerCode, Long id);
}
