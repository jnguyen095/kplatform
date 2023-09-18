package com.test.session;

import com.test.domain.RetailerEntity;

import javax.ejb.Local;
import java.util.List;

@Local
public interface RetailerLocalBean extends GenericSessionBean<RetailerEntity, Long>{
    Boolean isDuplicated(String retailerCode, Long id);

    List<RetailerEntity> findAllActive();
}
