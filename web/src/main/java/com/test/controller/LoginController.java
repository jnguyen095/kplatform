package com.test.controller;

import org.springframework.context.support.ApplicationObjectSupport;
import org.springframework.stereotype.Controller;
import org.springframework.web.HttpRequestHandler;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;

/**
 * Created with IntelliJ IDEA.
 * User: nkhang
 * Date: 12/11/15
 * Time: 10:44 PM
 * To change this template use File | Settings | File Templates.
 */
@Controller
public class LoginController extends ApplicationObjectSupport{

    @RequestMapping(value={"/login.html"})
    public ModelAndView login(HttpServletRequest request){
        ModelAndView mav = new ModelAndView("/login");
        request.getSession().setAttribute("retailerCode", "");
        return mav;
    }

    @RequestMapping(value={"/{retailerCode}/login.html"})
    public ModelAndView retailerLogin(@PathVariable(value = "retailerCode") final String retailerCode, HttpServletRequest request){
        ModelAndView mav = new ModelAndView("/login");
        request.getSession().setAttribute("retailerCode", retailerCode);

        return mav;
    }
}
