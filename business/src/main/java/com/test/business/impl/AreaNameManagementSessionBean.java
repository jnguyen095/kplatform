package com.test.business.impl;

import com.test.business.AreaNameManagementLocalBean;
import com.test.domain.AreaNameEntity;
import com.test.dto.AreaNameDTO;
import com.test.session.AreaNameLocalBean;
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
@Stateless(name = "AreaNameManagementSessionEJB")
public class AreaNameManagementSessionBean implements AreaNameManagementLocalBean {
    @EJB
    private AreaNameLocalBean areaNameLocalBean;


    @Override
    public Object[] searchByProperties(Map<String, Object> properties, String sortExpression, String sortDirection, int firstItem, int maxPageItems) {
        Object[] objs = areaNameLocalBean.searchByProperties(properties, sortExpression, sortDirection, firstItem, maxPageItems);
        List<AreaNameEntity> areaNameEntities = (List<AreaNameEntity>)objs[1];
        List<AreaNameDTO> areaNameDTOS = new ArrayList<>();
        for(AreaNameEntity areaNameEntity : areaNameEntities){
            areaNameDTOS.add(DozerSingletonMapper.getInstance().map(areaNameEntity, AreaNameDTO.class));
        }
        Object[] result = new Object[2];
        result[0] = objs[0];
        result[1] = areaNameDTOS;
        return result;
    }

    @Override
    public AreaNameDTO saveOrUpdate(AreaNameDTO pojo) throws DuplicateKeyException, ObjectNotFoundException {
        AreaNameEntity entity = new AreaNameEntity();
        Long areaNameId = pojo.getAreaNameId();
        Timestamp now = new Timestamp(System.currentTimeMillis());
        if(areaNameId != null){
            AreaNameEntity currentEntity = areaNameLocalBean.findById(areaNameId);
            currentEntity.setModifiedDate(now);
            currentEntity.setAreaName(pojo.getAreaName());
            currentEntity.setStatus(pojo.getStatus());
            entity = areaNameLocalBean.update(currentEntity);
        }else{
            entity =  DozerSingletonMapper.getInstance().map(pojo, AreaNameEntity.class);
            entity.setCreatedDate(now);
            entity.setModifiedDate(now);
            entity = areaNameLocalBean.save(entity);
        }
        return DozerSingletonMapper.getInstance().map(entity, AreaNameDTO.class);
    }

    @Override
    public AreaNameDTO findById(Long areaNameId) throws ObjectNotFoundException {
        AreaNameEntity entity = areaNameLocalBean.findById(areaNameId);
        return DozerSingletonMapper.getInstance().map(entity, AreaNameDTO.class);
    }

    @Override
    public Boolean isDuplicated(String areaName, Long id) {
        return areaNameLocalBean.isDuplicated(areaName, id);
    }

    @Override
    public List<AreaNameDTO> findAllByStatus(Boolean status) {
        List<AreaNameEntity> areaNameEntities = areaNameLocalBean.findByProperty("status", status);
        List<AreaNameDTO> areaNameDTOS = new ArrayList<>();
        for(AreaNameEntity areaNameEntity : areaNameEntities){
            areaNameDTOS.add(DozerSingletonMapper.getInstance().map(areaNameEntity, AreaNameDTO.class));
        }
        return areaNameDTOS;
    }
}
