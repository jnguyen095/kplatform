package com.test.controller.admin;

import com.test.Constants;
import com.test.business.AreaNameManagementLocalBean;
import com.test.business.TableNameManagementLocalBean;
import com.test.command.TableNameCommand;
import com.test.command.UserCommand;
import com.test.dto.TableNameDTO;
import com.test.security.util.SecurityUtils;
import com.test.utils.RequestUtil;
import com.test.validator.TableNameValidator;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.support.ApplicationObjectSupport;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.ejb.ObjectNotFoundException;
import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
public class TableNameController extends ApplicationObjectSupport {

    @Autowired
    private TableNameManagementLocalBean tableNameManagementLocalBean;
    @Autowired
    private AreaNameManagementLocalBean areaNameManagementLocalBean;
    @Autowired
    TableNameValidator tableNameValidator;

    @RequestMapping("/admin/table-name/list.html")
    public ModelAndView list(@ModelAttribute(value = Constants.LIST_MODEL_KEY)TableNameCommand command, HttpServletRequest request){
        ModelAndView mav = new ModelAndView("admin/table-name/table-list");
        executeSearch(command, request);
        mav.addObject(Constants.LIST_MODEL_KEY, command);
        return mav;
    }

    @RequestMapping("/admin/table-name/edit.html")
    public ModelAndView edit(@ModelAttribute(value = Constants.FORM_MODEL_KEY)TableNameCommand command, BindingResult bindingResult){
        ModelAndView mav = new ModelAndView("admin/table-name/table-edit");
        String crudaction = command.getCrudaction();

        if(StringUtils.isNotBlank(crudaction) && crudaction.equals(Constants.INSERT_UPDATE)){
            tableNameValidator.validate(command, bindingResult);
            if(!bindingResult.hasErrors()){
                try {
                    tableNameManagementLocalBean.saveOrUpdate(command.getPojo());
                    mav.addObject(Constants.MESSAGE_RESPONSE, this.getMessageSourceAccessor().getMessage("save.success"));
                    mav = new ModelAndView("redirect:/admin/table-name/list.html");
                }catch (Exception e){
                    mav.addObject(Constants.MESSAGE_RESPONSE, this.getMessageSourceAccessor().getMessage("save.error"));
                }
            }
        }

        if(command.getPojo().getTableNameId() != null){
            try{
                TableNameDTO edit = tableNameManagementLocalBean.findById(command.getPojo().getTableNameId());
                command.setPojo(edit);
            }catch (ObjectNotFoundException e){
                mav.addObject(Constants.MESSAGE_RESPONSE, this.getMessageSourceAccessor().getMessage("error.unexpected"));
            }
        }
        mav.addObject("areaNames", areaNameManagementLocalBean.findAllByStatus(SecurityUtils.getRetailerId(), Boolean.TRUE));
        mav.addObject(Constants.FORM_MODEL_KEY, command);
        return mav;
    }

    private void executeSearch(TableNameCommand command, HttpServletRequest request){
        RequestUtil.initSearchBean(request, command);
        Map<String, Object> properties = new HashMap<String, Object>();
        if(StringUtils.isBlank(command.getSortExpression())){
            command.setSortExpression("createdDate");
            command.setSortDirection(Constants.SORT_DESC);
        }

        Object[] results = this.tableNameManagementLocalBean.searchByProperties(properties, command.getSortExpression(), command.getSortDirection(), command.getFirstItem(), command.getMaxPageItems());
        command.setListResult((List<TableNameDTO>)results[1]);
        command.setTotalItems(Integer.valueOf(results[0].toString()));
    }

}
