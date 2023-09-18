package com.test.controller.admin;

import com.test.Constants;
import com.test.business.CategoryManagementLocalBean;
import com.test.command.CategoryCommand;
import com.test.dto.CategoryDTO;
import com.test.utils.FileUtils;
import com.test.utils.RequestUtil;
import org.apache.commons.io.FileDeleteStrategy;
import org.apache.commons.lang.StringUtils;
import org.springframework.context.support.ApplicationObjectSupport;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import javax.ejb.EJB;
import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created with IntelliJ IDEA.
 * User: nkhang
 * Date: 10/13/15
 * Time: 10:30 AM
 * To change this template use File | Settings | File Templates.
 */
@Controller
public class CategoryController extends ApplicationObjectSupport{
    @EJB
    private CategoryManagementLocalBean categoryManagementLocalBean;

    @RequestMapping("/admin/category/list.html")
    public ModelAndView list(@ModelAttribute(value = Constants.LIST_MODEL_KEY)CategoryCommand command, HttpServletRequest request){
        ModelAndView mav = new ModelAndView("admin/category/category-list");
        executeSearch(command, request);
        mav.addObject("name", "Khang Nguyen");
        mav.addObject(Constants.LIST_MODEL_KEY, command);
        return mav;
    }

    @RequestMapping("/admin/category/edit.html")
    public ModelAndView edit(@ModelAttribute("command")CategoryCommand command, @RequestParam(value = "fileUpload", required = false) MultipartFile file, HttpServletRequest request){
        ModelAndView mav = new ModelAndView("admin/category/category-edit");
        if(command.getCrudaction() != null && command.getCrudaction().equals("upload-file")){
            try {
                parseExcelFileToObject(file, request);
            }catch (Exception ex){
                logger.error(ex.getStackTrace(), ex);
            }
        }

        return mav;
    }

    private void parseExcelFileToObject(MultipartFile uploadFile, HttpServletRequest req) throws IOException, ParseException {
        SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy_(HH.mm a)");
        String date = sdf.format(System.currentTimeMillis());
        String folder = "catgroup_" + date;
        String jbossTempFolderPath = System.getProperty("jboss.server.temp.dir");
        File file = new File(jbossTempFolderPath + File.separator + folder);
        if (!file.exists()) {
            file.mkdirs();
        }

        File giftFolder = new File(file.getPath() + File.separator + "catgroup");
        if (!giftFolder.exists()) {
            giftFolder.mkdirs();
        }

        String tempFolder = giftFolder.getPath() + File.separator + req.getSession().getId();
        FileUtils.writeMultipartFileToTempDir(tempFolder, uploadFile);
        String fileName = uploadFile.getOriginalFilename();
        String importFilePath = tempFolder + File.separator + fileName;
        FileDeleteStrategy.FORCE.delete(file);
    }

    private void executeSearch(CategoryCommand command, HttpServletRequest request) {
        RequestUtil.initSearchBean(request, command);
        Map<String, Object> properties = new HashMap<String, Object>();
        if(StringUtils.isBlank(command.getSortExpression())){
            command.setSortExpression("name");
        }
        command.setSortDirection(Constants.SORT_ASC);
        Object[] results = this.categoryManagementLocalBean.searchByProperties(properties, command.getSortExpression(), command.getSortDirection(), command.getFirstItem(), command.getMaxPageItems());
        command.setListResult((List<CategoryDTO>)results[1]);
        command.setTotalItems(Integer.valueOf(results[0].toString()));
    }
}
