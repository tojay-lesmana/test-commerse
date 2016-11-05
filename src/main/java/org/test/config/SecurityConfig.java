package org.test.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.encrypt.Encryptors;
import org.springframework.security.crypto.encrypt.TextEncryptor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.rememberme.TokenBasedRememberMeServices;
import org.springframework.security.web.csrf.CsrfFilter;
import org.springframework.web.filter.CharacterEncodingFilter;
import org.test.service.LoginService;

@Configuration
@EnableWebSecurity
class SecurityConfig extends WebSecurityConfigurerAdapter{
	
	@Override
	protected void configure(AuthenticationManagerBuilder auth)
			throws Exception {
		auth.eraseCredentials(true).userDetailsService(loginService()).passwordEncoder(passwordEncoder());
	}
	
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http
		    .csrf().disable()
		    .authorizeRequests()
		        .antMatchers(
		        		"/",
		        		"/css/**",
		        		"/js/**",
		        		"/images/**",
		        		"/fonts/**",
		        		"/less/**",
		        		"/signin/**").permitAll() 
		        .antMatchers("/admin","/admin/**").hasRole("admin") 
		        .and()
		    .formLogin()  
		        .loginPage("/signin") 
		        .loginProcessingUrl("/signin/authenticate")
		        .failureUrl("/signin?error=1")
		        .usernameParameter("username")
		        .passwordParameter("password")
		        .permitAll()
		        .and()
			.logout()
				.logoutUrl("/logout")
				.deleteCookies("JSESSIONID")
				.deleteCookies("remember-me")
				.logoutSuccessUrl("/")
				.and()
			.rememberMe().rememberMeServices(rememberMeServices());
		
		CharacterEncodingFilter filter = new CharacterEncodingFilter();
        filter.setEncoding("UTF-8");
        filter.setForceEncoding(true);
        http.addFilterBefore(filter, CsrfFilter.class); 
		
	}
	
	@Override
	public void configure(WebSecurity web) throws Exception {
		web.ignoring().antMatchers("/resources/**");
	}
	
	@Bean
	public LoginService loginService() {
		return new LoginService();
	}

	@Bean
	public TokenBasedRememberMeServices rememberMeServices() {
		return new TokenBasedRememberMeServices("remember-me", loginService());
	}

	@Bean
	public PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}
	
	@Bean
	public TextEncryptor textEncryptor() {
		return Encryptors.noOpText();
	}
	
}