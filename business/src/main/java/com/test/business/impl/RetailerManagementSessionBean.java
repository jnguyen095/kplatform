package com.test.business.impl;

import com.test.business.RetailerManagementLocalBean;
import com.test.domain.RetailerEntity;
import com.test.dto.RetailerDTO;
import com.test.session.RetailerLocalBean;
import com.test.utils.DozerSingletonMapper;

import javax.ejb.DuplicateKeyException;
import javax.ejb.EJB;
import javax.ejb.ObjectNotFoundException;
import javax.ejb.Stateless;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Stateless(name = "RetailerManagementSessionEJB")
public class RetailerManagementSessionBean implements RetailerManagementLocalBean {
    @EJB
    private RetailerLocalBean retailerLocalBean;
    @Override
    public Boolean isDuplicated(String retailerCode, Long id) {
        return retailerLocalBean.isDuplicated(retailerCode, id);
    }

    @Override
    public Object[] searchByProperties(Map<String, Object> properties, String sortExpression, String sortDirection, int firstItem, int maxPageItems) {
        Object[] objs = retailerLocalBean.searchByProperties(properties, sortExpression, sortDirection, firstItem, maxPageItems);
        List<RetailerEntity> tableNameEntities = (List<RetailerEntity>)objs[1];
        List<RetailerDTO> tableNameDTOS = new ArrayList<>();
        for(RetailerEntity tableNameEntity : tableNameEntities){
            tableNameDTOS.add(DozerSingletonMapper.getInstance().map(tableNameEntity, RetailerDTO.class));
        }
        Object[] result = new Object[2];
        result[0] = objs[0];
        result[1] = tableNameDTOS;
        return result;
    }

    @Override
    public RetailerDTO saveOrUpdate(RetailerDTO pojo) throws DuplicateKeyException, ObjectNotFoundException {
        RetailerEntity entity = new RetailerEntity();
        Long retailerId = pojo.getRetailerId();
        Timestamp now = new Timestamp(System.currentTimeMillis());
        if(retailerId != null){
            RetailerEntity currentEntity = retailerLocalBean.findById(retailerId);
            currentEntity.setModifiedDate(now);
            currentEntity.setCode(pojo.getCode());
            currentEntity.setName(pojo.getName());
            currentEntity.setAddress(pojo.getAddress());
            currentEntity.setPhone(pojo.getPhone());
            currentEntity.setStatus(pojo.getStatus());
            entity = retailerLocalBean.update(currentEntity);
        }else{
            entity =  DozerSingletonMapper.getInstance().map(pojo, RetailerEntity.class);
            entity.setCreatedDate(now);
            entity.setModifiedDate(now);
            entity = retailerLocalBean.save(entity);
        }
        return DozerSingletonMapper.getInstance().map(entity, RetailerDTO.class);
    }

    @Override
    public RetailerDTO findById(Long retailerId) throws ObjectNotFoundException {
        RetailerEntity entity = retailerLocalBean.findById(retailerId);
        return DozerSingletonMapper.getInstance().map(entity, RetailerDTO.class);
    }

    @Override
    public List<RetailerDTO> findAllActive() {
        List<RetailerEntity> tableNameEntities = retailerLocalBean.findAllActive();
        List<RetailerDTO> retailerDTOS = new ArrayList<>();
        for(RetailerEntity tableNameEntity : tableNameEntities){
            retailerDTOS.add(DozerSingletonMapper.getInstance().map(tableNameEntity, RetailerDTO.class));
        }
        return retailerDTOS;
    }
}
