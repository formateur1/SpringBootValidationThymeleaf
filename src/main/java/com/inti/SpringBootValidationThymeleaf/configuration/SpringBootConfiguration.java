package com.inti.SpringBootValidationThymeleaf.configuration;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@Configuration
@EnableWebSecurity
public class SpringBootConfiguration
{
	@Autowired
	UserDetailsService userDetailsService;
	
	
	protected void configure(HttpSecurity http) throws Exception
	{
		http
		.authorizeRequests()
		.antMatchers("/creerUtilisateur", "/saveUser").permitAll()
		//.antMatchers("/admin/**").hasRole("ADMIN")
//		.antMatchers("/helloUser").hasRole("USER")
		.anyRequest().authenticated()
		.and()
		.formLogin();
		//.loginPage("/login").permitAll();
}
	
	
	protected void configure(AuthenticationManagerBuilder auth) throws Exception
	{
		auth.userDetailsService(userDetailsService).passwordEncoder(passwordEncoderB());
		
		auth.inMemoryAuthentication()
		.withUser("test").password(passwordEncoderB().encode("test")).roles("USER")
		.and()
		.withUser("admin1").password(passwordEncoderB().encode("admin1")).roles("ADMIN", "MANAGER");
	}
	
	@Bean
	public BCryptPasswordEncoder passwordEncoderB()
	{
		return new BCryptPasswordEncoder();
	}
}
