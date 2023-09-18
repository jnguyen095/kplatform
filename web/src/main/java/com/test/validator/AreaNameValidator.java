package com.test.validator;

import com.test.business.AreaNameManagementLocalBean;
import com.test.command.AreaNameCommand;
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
public class AreaNameValidator implements Validator {

    @Autowired
    private AreaNameManagementLocalBean areaNameManagementLocalBean;

    @Override
    public boolean supports(Class<?> aClass) {
        return AreaNameCommand.class.isAssignableFrom(aClass);
    }

    @Override
    public void validate(Object o, Errors errors) {
        AreaNameCommand command = (AreaNameCommand)o;
        validateRequired(command, errors);
        checkUniqueCode(command, errors);
    }

    private void checkUniqueCode(AreaNameCommand command, Errors errors) {
        String areaName = command.getPojo().getAreaName();
        Long areaNameId = command.getPojo().getAreaNameId();
        Long retailerId = command.getPojo().getRetailer().getRetailerId();
        if(StringUtils.isNotBlank(areaName)){
            Boolean isDuplicated = areaNameManagementLocalBean.isDuplicated(areaName, retailerId, areaNameId);
            if(isDuplicated){
                errors.rejectValue("pojo.areaName", "duplicated.message", new Object[]{areaName}, null);
            }
        }
    }

    private void validateRequired(AreaNameCommand command, Errors errors) {
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "pojo.status", "validator.required");
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "pojo.areaName", "validator.required");
    }
}
