package com.test.security.util;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;

import java.util.ArrayList;
import java.util.List;

public class SecurityUtils {
	/**
	 * This method to retrieve the current online User Detail 
	 * @return the current online MyUserDetail object
	 */
	public static MyUserDetail getPrincipal() {
		return (MyUserDetail) (SecurityContextHolder
				.getContext()).getAuthentication().getPrincipal();
	}
	/**
	 * This getLoginUserId() is only used for doing jUnit test case only
	 * @return the current login name for online user
	 */
	public static Long getRetailerId() {
		return getPrincipal().getRetailerId();
	}

    public static String getRetailerCode(){
        return getPrincipal().getRetailerCode();
    }

    public static String getRetailerName(){
        return getPrincipal().getRetailerName();
    }

	public static String getLoginName(){
    	return getPrincipal().getLoginName();
	}

	public static boolean userHasAuthority(String roleCode) {
		List<GrantedAuthority> list = (List<GrantedAuthority>)(SecurityContextHolder.getContext().getAuthentication().getAuthorities());
		List<GrantedAuthority> authories = list;
    	for (GrantedAuthority authority : authories) {
    		if (authority.getAuthority().equals(roleCode)) {
    			return true;
    		}
    	}
    	return false;
	}

    public static List<String> getAuthorities(){
        List<String> results = new ArrayList<String>();
        List<GrantedAuthority> authorities = (List<GrantedAuthority>)(SecurityContextHolder.getContext().getAuthentication().getAuthorities());
        for (GrantedAuthority authority : authorities) {
            results.add(authority.getAuthority());
        }
        return results;
    }

}