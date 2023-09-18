package com.test.session;

import com.test.domain.CategoryEntity;
import com.test.domain.TableNameEntity;

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
public interface TableNameLocalBean extends GenericSessionBean<TableNameEntity, Long> {

    Boolean isDuplicated(String tableName, Long areaNameId, Long tableNameId);
}
