package com.projects.business_trip_management.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter{
	
	@Autowired
	public PasswordEncoder passwordEncoder;
	
	@Autowired
	private UserDetailsService userDetail;
	
	@Autowired
	public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
		auth.userDetailsService(userDetail).passwordEncoder(passwordEncoder);
	}
	
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http.csrf().disable();
		http
		    .authorizeRequests()
		        .antMatchers("/").authenticated()
		        .antMatchers("/proposes").authenticated()
		        .antMatchers("/proposes/*").authenticated()
		        .antMatchers("/business-trips").authenticated()
		        .antMatchers("/business-trips/*").authenticated()
		        .antMatchers("/notifications").authenticated()
		        .antMatchers("/notifications/*").authenticated()
		        .antMatchers("/thresholds").authenticated()
		        .antMatchers("/thresholds/*").authenticated()
		        .antMatchers("/payment/*").authenticated()
		        .antMatchers("/payment/*").authenticated()
		        .and()
		    .formLogin()
		        .loginPage("/login")
		            .usernameParameter("username")
		            .passwordParameter("password")
		            .failureUrl("/login?error")
		            .defaultSuccessUrl("/")
		        .and()
		    .logout().permitAll().logoutSuccessUrl("/");
	}
	
}
