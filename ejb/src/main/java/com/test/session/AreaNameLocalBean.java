package com.test.session;

import com.test.domain.AreaNameEntity;

import javax.ejb.Local;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: nkhang
 * Date: 7/16/15
 * Time: 5:07 PM
 * To change this template use File | Settings | File Templates.
 */
@Local
public interface AreaNameLocalBean extends GenericSessionBean<AreaNameEntity, Long> {

    Boolean isDuplicated(String tableName, Long retailerId, Long areaNameId);

    List<AreaNameEntity> findByRetailerAndStatus(Long retailerId, Boolean status);
}
