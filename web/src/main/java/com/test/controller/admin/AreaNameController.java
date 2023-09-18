package com.test.controller.admin;

import com.test.Constants;
import com.test.business.AreaNameManagementLocalBean;
import com.test.command.AreaNameCommand;
import com.test.dto.AreaNameDTO;
import com.test.dto.RetailerDTO;
import com.test.security.util.SecurityUtils;
import com.test.utils.RequestUtil;
import com.test.validator.AreaNameValidator;
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
public class AreaNameController extends ApplicationObjectSupport {

    @Autowired
    private AreaNameManagementLocalBean areaNameManagementLocalBean;
    @Autowired
    AreaNameValidator areaNameValidator;

    @RequestMapping("/admin/area-name/list.html")
    public ModelAndView list(@ModelAttribute(value = Constants.LIST_MODEL_KEY)AreaNameCommand command, HttpServletRequest request){
        ModelAndView mav = new ModelAndView("admin/area-name/area-list");
        executeSearch(command, request);
        mav.addObject(Constants.LIST_MODEL_KEY, command);
        return mav;
    }

    @RequestMapping("/admin/area-name/edit.html")
    public ModelAndView edit(@ModelAttribute(value = Constants.FORM_MODEL_KEY)AreaNameCommand command, BindingResult bindingResult){
        ModelAndView mav = new ModelAndView("admin/area-name/area-edit");
        String crudaction = command.getCrudaction();
        command.getPojo().setRetailer(new RetailerDTO(SecurityUtils.getRetailerId()));
        if(StringUtils.isNotBlank(crudaction) && crudaction.equals(Constants.INSERT_UPDATE)){
            areaNameValidator.validate(command, bindingResult);
            if(!bindingResult.hasErrors()){
                try {
                    areaNameManagementLocalBean.saveOrUpdate(command.getPojo());
                    mav.addObject(Constants.MESSAGE_RESPONSE, this.getMessageSourceAccessor().getMessage("save.success"));
                    mav = new ModelAndView("redirect:/admin/area-name/list.html");
                }catch (Exception e){
                    mav.addObject(Constants.MESSAGE_RESPONSE, this.getMessageSourceAccessor().getMessage("save.error"));
                }
            }
        }

        if(command.getPojo().getAreaNameId() != null){
            try{
                AreaNameDTO edit = areaNameManagementLocalBean.findById(command.getPojo().getAreaNameId());
                command.setPojo(edit);
            }catch (ObjectNotFoundException e){
                mav.addObject(Constants.MESSAGE_RESPONSE, this.getMessageSourceAccessor().getMessage("error.unexpected"));
            }
        }

        mav.addObject(Constants.FORM_MODEL_KEY, command);
        return mav;
    }

    private void executeSearch(AreaNameCommand command, HttpServletRequest request){
        RequestUtil.initSearchBean(request, command);
        Map<String, Object> properties = new HashMap<String, Object>();
        if(StringUtils.isBlank(command.getSortExpression())){
            command.setSortExpression("createdDate");
            command.setSortDirection(Constants.SORT_DESC);
        }

        Object[] results = this.areaNameManagementLocalBean.searchByProperties(properties, command.getSortExpression(), command.getSortDirection(), command.getFirstItem(), command.getMaxPageItems());
        command.setListResult((List<AreaNameDTO>)results[1]);
        command.setTotalItems(Integer.valueOf(results[0].toString()));
    }

}
