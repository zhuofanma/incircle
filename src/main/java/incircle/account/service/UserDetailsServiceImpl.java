package incircle.account.service;

import incircle.account.dao.AccountDao;
import incircle.account.model.Account;
import incircle.account.model.AccountRole;
import org.apache.log4j.Logger;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class UserDetailsServiceImpl implements UserDetailsService {
	final Logger log = Logger.getLogger(UserDetailsServiceImpl.class.getName());
	
	private AccountDao accountDao;
	
	public void setAccountDao(AccountDao accountDao) {
		this.accountDao = accountDao;
	}

	@Transactional(readOnly=true)
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
	
		Account account = accountDao.findByAccountName(username);
		System.out.println(account);
		List<GrantedAuthority> authorities = buildUserAuthority(account.getAccountRole());

		UserDetails userDetails =  buildUserForAuthentication(account, authorities);
		log.debug(userDetails.getPassword());
		return userDetails;
		
	}

	// Converts catalog.account.model.Account account to
	// org.springframework.security.core.userdetails.Account
	private User buildUserForAuthentication(Account account, List<GrantedAuthority> authorities) {
		User authUser = new User(account.getUsername(), account.getPassword(), account.isEnabled(), true, true, true, authorities);
		log.debug(authUser.getUsername() + " " + authUser.getPassword());
		return authUser;
	}

	private List<GrantedAuthority> buildUserAuthority(Set<AccountRole> accountRoles) {

		Set<GrantedAuthority> setAuths = new HashSet<GrantedAuthority>();

		// Build account's authorities
		for (AccountRole accountRole : accountRoles) {
			setAuths.add(new SimpleGrantedAuthority(accountRole.getRole()));
		}

		List<GrantedAuthority> Result = new ArrayList<GrantedAuthority>(setAuths);

		return Result;
	}

}