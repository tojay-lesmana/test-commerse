package org.test.service;

import java.util.Collections;
import java.util.Date;

import javax.annotation.PostConstruct;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.authentication.event.AuthenticationSuccessEvent;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.test.jpa.model.Account;
import org.test.jpa.repo.AccountRepository;

@Service
public class LoginService  implements UserDetailsService, ApplicationListener<AuthenticationSuccessEvent> {

	Logger log = LoggerFactory.getLogger(this.getClass());
	
	@Autowired
	private PasswordEncoder passwordEncoder;
	
	@Autowired
	private AccountRepository accountRepository;
	
	@PostConstruct
	public void initialize() {
		long size = accountRepository.count();
		if (size == 0) {
			Account account = new Account("aji.lesmana@yahoo.co.id", passwordEncoder.encode("test"), "Admin", "092312313213123");			
			account.setAddress("Jakarta");
			account.setROLE_ACCOUNT("admin");
			accountRepository.save(account);
		}
	}
	
	@Override
	public void onApplicationEvent(AuthenticationSuccessEvent event) {
		String userName = ((UserDetails) event.getAuthentication()
				.getPrincipal()).getUsername();
		Account account = accountRepository.findByEmail(userName);		
		account.setLastLogin(new Date());
		accountRepository.save(account);
	}

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		Account account = accountRepository.findByEmail(username);
		if (account == null) {
			throw new UsernameNotFoundException("user not found");
		}
		return createUser(account);
	}
	
	/**
	 * @param account
	 */
	public void signin(Account account) {
		SecurityContextHolder.getContext().setAuthentication(
				authenticate(account));
	}

	/**
	 * @param account
	 * @return
	 */
	private Authentication authenticate(Account account) {
		return new UsernamePasswordAuthenticationToken(createUser(account),
				null, Collections.singleton(createAuthority(account)));
	}

	/**
	 * @param account
	 * @return
	 */
	private org.springframework.security.core.userdetails.User createUser(
			Account account) {
		return new org.springframework.security.core.userdetails.User(
				account.getEmail(), account.getPassword(),
				Collections.singleton(createAuthority(account)));
	}

	/**
	 * @param account
	 * @return
	 */
	private GrantedAuthority createAuthority(Account account) {
		return new SimpleGrantedAuthority(account.getROLE_ACCOUNT());
	}

}
