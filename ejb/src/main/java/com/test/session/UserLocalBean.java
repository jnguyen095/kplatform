package com.test.session;

import com.test.domain.UserEntity;

import javax.ejb.Local;

/**
 * Created with IntelliJ IDEA.
 * User: nkhang
 * Date: 1/8/16
 * Time: 10:20 PM
 * To change this template use File | Settings | File Templates.
 */
@Local
public interface UserLocalBean extends GenericSessionBean<UserEntity, Long> {
    Boolean isDuplicated(String userName, Long retailerId, Long userId);
    UserEntity findByUserNameAndActive(String userName, String retailerCode);
}
