package com.test.controller.sysadmin;

import com.test.Constants;
import com.test.business.RetailerManagementLocalBean;
import com.test.command.RetailerCommand;
import com.test.dto.RetailerDTO;
import com.test.utils.RequestUtil;
import com.test.validator.RetailerValidator;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.support.ApplicationObjectSupport;
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
public class RetailerController extends ApplicationObjectSupport {

    @Autowired
    private RetailerManagementLocalBean retailerManagementLocalBean;
    @Autowired
    private RetailerValidator retailerValidator;

    @RequestMapping("/sysadmin/retailer/list.html")
    public ModelAndView list(@ModelAttribute(value = Constants.LIST_MODEL_KEY)RetailerCommand command, HttpServletRequest request){
        ModelAndView mav = new ModelAndView("sysadmin/retailer/retailer-list");
        executeSearch(command, request);
        mav.addObject(Constants.LIST_MODEL_KEY, command);
        return mav;
    }

    @RequestMapping("/sysadmin/retailer/edit.html")
    public ModelAndView edit(@ModelAttribute(value = Constants.FORM_MODEL_KEY) RetailerCommand command, BindingResult bindingResult){
        ModelAndView mav = new ModelAndView("sysadmin/retailer/retailer-edit");
        String crudaction = command.getCrudaction();
        if(StringUtils.isNotBlank(crudaction) && crudaction.equals(Constants.INSERT_UPDATE)){
            retailerValidator.validate(command, bindingResult);
            if(!bindingResult.hasErrors()){
                try {
                    retailerManagementLocalBean.saveOrUpdate(command.getPojo());
                    mav.addObject(Constants.MESSAGE_RESPONSE, this.getMessageSourceAccessor().getMessage("save.success"));
                    mav = new ModelAndView("redirect:/sysadmin/retailer/list.html");
                }catch (Exception e){
                    mav.addObject(Constants.MESSAGE_RESPONSE, this.getMessageSourceAccessor().getMessage("save.error"));
                }
            }
        }

        if(command.getPojo().getRetailerId() != null){
            try{
                RetailerDTO edit = retailerManagementLocalBean.findById(command.getPojo().getRetailerId());
                command.setPojo(edit);
            }catch (ObjectNotFoundException e){
                mav.addObject(Constants.MESSAGE_RESPONSE, this.getMessageSourceAccessor().getMessage("error.unexpected"));
            }
        }

        mav.addObject(Constants.FORM_MODEL_KEY, command);
        return mav;
    }

    private void executeSearch(RetailerCommand command, HttpServletRequest request) {
        RequestUtil.initSearchBean(request, command);
        Map<String, Object> properties = new HashMap<>();
        if(StringUtils.isBlank(command.getSortExpression())){
            command.setSortExpression("code");
            command.setSortDirection(Constants.SORT_ASC);
        }

        Object[] results = this.retailerManagementLocalBean.searchByProperties(properties, command.getSortExpression(), command.getSortDirection(), command.getFirstItem(), command.getMaxPageItems());
        command.setListResult((List<RetailerDTO>)results[1]);
        command.setTotalItems(Integer.valueOf(results[0].toString()));
    }
}
