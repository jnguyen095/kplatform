/**
 * 
 */
package com.test.security;

import com.test.security.util.SecurityUtils;
import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.DefaultRedirectStrategy;
import org.springframework.security.web.RedirectStrategy;
import org.springframework.security.web.authentication.logout.LogoutSuccessHandler;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @author Nguyen Nhu Khang
 *
 */
public class MyLogoutSuccessHandler implements LogoutSuccessHandler {
    private transient final Logger logger = Logger.getLogger(getClass());
	private RedirectStrategy redirectStrategy = new DefaultRedirectStrategy();
	private String logoutSuccessUrl = "/login.html?action=logout";

	/**
	 * @param logoutSuccessUrl the logoutSuccessUrl to set
	 */
	public void setLogoutSuccessUrl(String logoutSuccessUrl) {
		this.logoutSuccessUrl = logoutSuccessUrl;
	}
	public void onLogoutSuccess(HttpServletRequest request,
			HttpServletResponse response, Authentication authentication)
			throws IOException, ServletException {
        String myLocalLogoutSuccessUrl = this.logoutSuccessUrl;
		Object obj = request.getSession().getAttribute("retailerCode");
		if(obj != null){
			String retailerCode = (String)obj;
			if(retailerCode.length() > 1) {
				myLocalLogoutSuccessUrl = "/" + retailerCode + "/login.html?action=logout";
			}
		}
        SecurityContextHolder.clearContext();
        request.getSession(true); //Create new session
        redirectStrategy.sendRedirect(request, response, myLocalLogoutSuccessUrl);
	}
}
