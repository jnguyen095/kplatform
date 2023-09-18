package com.test.business.impl;

import com.test.business.TableNameManagementLocalBean;
import com.test.domain.AreaNameEntity;
import com.test.domain.TableNameEntity;
import com.test.dto.TableNameDTO;
import com.test.session.TableNameLocalBean;
import com.test.utils.DozerSingletonMapper;

import javax.ejb.DuplicateKeyException;
import javax.ejb.EJB;
import javax.ejb.ObjectNotFoundException;
import javax.ejb.Stateless;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Created by Khang Nguyen.
 * Email: khang.nguyen@banvien.com
 * Date: 9/22/2017
 * Time: 5:58 PM
 */
@Stateless(name = "TableNameManagementSessionEJB")
public class TableNameManagementSessionBean implements TableNameManagementLocalBean {
    @EJB
    private TableNameLocalBean tableNameLocalBean;


    @Override
    public Object[] searchByProperties(Map<String, Object> properties, String sortExpression, String sortDirection, int firstItem, int maxPageItems) {
        Object[] objs = tableNameLocalBean.searchByProperties(properties, sortExpression, sortDirection, firstItem, maxPageItems);
        List<TableNameEntity> tableNameEntities = (List<TableNameEntity>)objs[1];
        List<TableNameDTO> tableNameDTOS = new ArrayList<>();
        for(TableNameEntity tableNameEntity : tableNameEntities){
            tableNameDTOS.add(DozerSingletonMapper.getInstance().map(tableNameEntity, TableNameDTO.class));
        }
        Object[] result = new Object[2];
        result[0] = objs[0];
        result[1] = tableNameDTOS;
        return result;
    }

    @Override
    public TableNameDTO saveOrUpdate(TableNameDTO pojo) throws DuplicateKeyException, ObjectNotFoundException {
        TableNameEntity entity = new TableNameEntity();
        Long tableNameId = pojo.getTableNameId();
        Timestamp now = new Timestamp(System.currentTimeMillis());
        AreaNameEntity areaName = new AreaNameEntity();
        areaName.setAreaNameId(pojo.getAreaName().getAreaNameId());
        if(tableNameId != null){
            TableNameEntity currentEntity = tableNameLocalBean.findById(tableNameId);
            currentEntity.setModifiedDate(now);
            currentEntity.setTableName(pojo.getTableName());
            currentEntity.setStatus(pojo.getStatus());
            currentEntity.setAreaName(areaName);
            entity = tableNameLocalBean.update(currentEntity);
        }else{
            entity =  DozerSingletonMapper.getInstance().map(pojo, TableNameEntity.class);
            entity.setCreatedDate(now);
            entity.setModifiedDate(now);
            entity.setAreaName(areaName);
            entity = tableNameLocalBean.save(entity);
        }
        return DozerSingletonMapper.getInstance().map(entity, TableNameDTO.class);
    }

    @Override
    public TableNameDTO findById(Long tableNameId) throws ObjectNotFoundException {
        TableNameEntity entity = tableNameLocalBean.findById(tableNameId);
        return DozerSingletonMapper.getInstance().map(entity, TableNameDTO.class);
    }

    @Override
    public Boolean isDuplicated(String tableName, Long areaNameId, Long tableNameId) {
        return tableNameLocalBean.isDuplicated(tableName, areaNameId, tableNameId);
    }
}
