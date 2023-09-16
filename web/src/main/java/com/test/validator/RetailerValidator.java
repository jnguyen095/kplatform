package com.test.validator;

import com.test.business.RetailerManagementLocalBean;
import com.test.business.TableNameManagementLocalBean;
import com.test.command.RetailerCommand;
import com.test.command.TableNameCommand;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

/**
 * Created with IntelliJ IDEA.
 * User: nkhang
 * Date: 12/12/15
 * Time: 8:07 PM
 * To change this template use File | Settings | File Templates.
 */
@Component
public class RetailerValidator implements Validator {

    @Autowired
    private RetailerManagementLocalBean retailerManagementLocalBean;

    @Override
    public boolean supports(Class<?> aClass) {
        return RetailerCommand.class.isAssignableFrom(aClass);
    }

    @Override
    public void validate(Object o, Errors errors) {
        RetailerCommand command = (RetailerCommand)o;
        validateRequired(command, errors);
        checkUniqueCode(command, errors);
    }

    private void checkUniqueCode(RetailerCommand command, Errors errors) {
        String retailerCode = command.getPojo().getCode();
        Long id = command.getPojo().getRetailerId();
        if(StringUtils.isNotBlank(retailerCode)){
            Boolean isDuplicated = retailerManagementLocalBean.isDuplicated(retailerCode, id);
            if(isDuplicated){
                errors.rejectValue("pojo.code", "duplicated.message", new Object[]{retailerCode}, null);
            }
        }
    }

    private void validateRequired(RetailerCommand command, Errors errors) {
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "pojo.code", "validator.required");
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "pojo.name", "validator.required");
    }
}
