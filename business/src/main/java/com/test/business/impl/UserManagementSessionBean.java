package com.test.business.impl;

import com.test.business.UserManagementLocalBean;
import com.test.domain.UserEntity;
import com.test.dto.UserDTO;
import com.test.session.UserLocalBean;
import com.test.utils.DozerSingletonMapper;
import org.apache.commons.lang.StringUtils;

import javax.ejb.DuplicateKeyException;
import javax.ejb.EJB;
import javax.ejb.ObjectNotFoundException;
import javax.ejb.Stateless;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Created with IntelliJ IDEA.
 * User: nkhang
 * Date: 1/8/16
 * Time: 10:18 PM
 * To change this template use File | Settings | File Templates.
 */
@Stateless(name = "UserManagementSessionEJB")
public class UserManagementSessionBean implements UserManagementLocalBean {

    @EJB
    private UserLocalBean userLocalBean;

    @Override
    public Object[] searchByProperties(Map<String, Object> properties, String sortExpression, String sortDirection, int firstItem, int maxPageItems) {
        Object[] objs = userLocalBean.searchByProperties(properties, sortExpression, sortDirection, firstItem, maxPageItems);
        List<UserEntity> userEntities = (List<UserEntity>)objs[1];
        List<UserDTO> userDTOs = new ArrayList<>();
        for(UserEntity userEntity : userEntities){
            userDTOs.add(DozerSingletonMapper.getInstance().map(userEntity, UserDTO.class));
        }
        Object[] result = new Object[2];
        result[0] = objs[0];
        result[1] = userDTOs;
        return result;
    }

    @Override
    public UserDTO saveOrUpdate(UserDTO pojo) throws DuplicateKeyException {
        UserEntity entity = DozerSingletonMapper.getInstance().map(pojo, UserEntity.class);
        Long userId = entity.getUserId();
        Timestamp now = new Timestamp(System.currentTimeMillis());
        if(entity.getRetailer().getRetailerId() == null){
            entity.setRetailer(null);
        }
        if(userId != null){
            try {
                UserEntity dbEntity = userLocalBean.findById(userId);
                entity.setCreatedDate(dbEntity.getCreatedDate());
                entity.setUpdatedDate(now);
                if(StringUtils.isBlank(entity.getPassword())){
                    entity.setPassword(dbEntity.getPassword());
                }
                entity = userLocalBean.update(entity);
            }catch (ObjectNotFoundException ex){
                throw new DuplicateKeyException("Not found user with Id: " + userId);
            }
        }else{
            entity.setCreatedDate(now);
            entity = userLocalBean.save(entity);
        }
        return DozerSingletonMapper.getInstance().map(entity, UserDTO.class);
    }

    @Override
    public UserDTO findById(Long userId) throws ObjectNotFoundException {
        UserEntity entity = userLocalBean.findById(userId);
        return DozerSingletonMapper.getInstance().map(entity, UserDTO.class);
    }

    @Override
    public Boolean isDuplicated(String userName, Long retailerid, Long userId) {
        return userLocalBean.isDuplicated(userName, retailerid, userId);
    }

    @Override
    public UserDTO findByUserName(String username, String retailerCode) throws ObjectNotFoundException {
        UserEntity entity = userLocalBean.findByUserNameAndActive(username, retailerCode);
        if(entity != null) {
            return DozerSingletonMapper.getInstance().map(entity, UserDTO.class);
        }
        return null;
    }
}
