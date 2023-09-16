package com.test.security;


import com.test.business.UserManagementLocalBean;
import com.test.dto.UserDTO;
import com.test.security.util.MyUserDetail;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserCache;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import javax.ejb.ObjectNotFoundException;
import java.util.ArrayList;
import java.util.List;


/**
 * @author Nguyen Nhu Khang
 * 
 */

public class MyUserDetailService implements UserDetailsService {
    private transient final Logger log = Logger.getLogger(getClass());

	@Autowired
	private UserManagementLocalBean userManagementLocalBean;
	protected UserCache userCache = null;

    /**
	 * Creates new instance of MyUserDetailService
	 */
	public MyUserDetailService() {

	}

	/**
	 * Set UserCache
	 *
	 * @param userCache
	 *            user cache to set
	 */
	public void setUserCache(UserCache userCache) {
		this.userCache = userCache;
	}

	/**
	 * Locates the user based on the username. In the actual implementation, the
	 * search may possibly be case insensitive, or case insensitive depending on
	 * how the implementaion instance is configured. In this case, the
	 * <code>UserDetails</code> object that comes back may have a username
	 * that is of a different case than what was actually requested..
	 *
	 * @param username
	 *            the username presented to the {@link
	 *            org.springframework.security.authentication.dao.DaoAuthenticationProvider}
	 * @return a fully populated user record (never <code>null</code>)
	 * @throws org.springframework.security.core.userdetails.UsernameNotFoundException
	 *             if the user could not be found or the user has no
	 *             GrantedAuthority
	 * @throws org.springframework.dao.DataAccessException
	 *             if user could not be found for a repository-specific reason
	 */
	public UserDetails loadUserByUsername(String username)
			throws UsernameNotFoundException, DataAccessException {
		try {
			UserDTO account = userManagementLocalBean.findByUserName(username);
			if(account != null) {
				List<GrantedAuthority> authorities = new ArrayList<>();
				authorities.add(new SimpleGrantedAuthority("LOGON"));
				authorities.add(new SimpleGrantedAuthority(account.getUserGroup().getCode()));
				MyUserDetail loginUser = new MyUserDetail(username, account.getPassword(), true, true, true, true, authorities);
				return loginUser;
			}else{
				throw new UsernameNotFoundException("Not found user with login: " + username);
			}
		}catch (ObjectNotFoundException ex){
			throw new UsernameNotFoundException("Not found user with login: " + username);
		}
	}

	public void setUserManagementLocalBean(UserManagementLocalBean userManagementLocalBean) {
		this.userManagementLocalBean = userManagementLocalBean;
	}

}
