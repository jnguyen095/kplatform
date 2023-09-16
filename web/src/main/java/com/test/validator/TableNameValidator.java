package com.test.validator;

import com.test.business.TableNameManagementLocalBean;
import com.test.business.UserGroupManagementLocalBean;
import com.test.command.TableNameCommand;
import com.test.command.UserGroupCommand;
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
public class TableNameValidator implements Validator {

    @Autowired
    private TableNameManagementLocalBean tableNameManagementLocalBean;

    @Override
    public boolean supports(Class<?> aClass) {
        return TableNameCommand.class.isAssignableFrom(aClass);
    }

    @Override
    public void validate(Object o, Errors errors) {
        TableNameCommand command = (TableNameCommand)o;
        validateRequired(command, errors);
        checkUniqueCode(command, errors);
    }

    private void checkUniqueCode(TableNameCommand command, Errors errors) {
        String tableName = command.getPojo().getTableName();
        Long id = command.getPojo().getTableNameId();
        if(StringUtils.isNotBlank(tableName)){
            Boolean isDuplicated = tableNameManagementLocalBean.isDuplicated(tableName, id);
            if(isDuplicated){
                errors.rejectValue("pojo.tableName", "duplicated.message", new Object[]{tableName}, null);
            }
        }
    }

    private void validateRequired(TableNameCommand command, Errors errors) {
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "pojo.status", "validator.required");
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "pojo.tableName", "validator.required");
    }
}
