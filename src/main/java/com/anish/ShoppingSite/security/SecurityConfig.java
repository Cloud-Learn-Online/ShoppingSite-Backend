package com.anish.ShoppingSite.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

	@Override
	protected void configure(HttpSecurity http) throws Exception {
//		http.csrf().disable().authorizeRequests().anyRequest().authenticated().and().formLogin()
//				.loginPage("http://localhost:4200/home").loginProcessingUrl("file:///home/anish/Desktop/my.html").and()
//				.logout().logoutUrl("app-logOut").logoutSuccessUrl("file:///home/anish/Desktop/my.html");
		http.csrf().disable().authorizeRequests().anyRequest().permitAll();
	}

	@Autowired
	public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
		auth.inMemoryAuthentication().withUser("admin").password("{noop}password").roles("USER");
	}
}
