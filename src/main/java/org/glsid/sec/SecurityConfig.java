package org.glsid.sec;





import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;


@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(securedEnabled=true)
public class SecurityConfig extends WebSecurityConfigurerAdapter{

	@Autowired
	protected void globalConfig(AuthenticationManagerBuilder auth, DataSource dataSource) throws Exception {
		/*
		auth.inMemoryAuthentication().withUser("admin").password("1234").roles("ADMIN","CLIENT","EMPLOYE");
		auth.inMemoryAuthentication().withUser("client").password("12345").roles("CLIENT");
		auth.inMemoryAuthentication().withUser("employe").password("123456").roles("EMPLOYE");
		*/
		
		auth.jdbcAuthentication()
			.dataSource(dataSource)
		.usersByUsernameQuery("select username as principal, password as credentials,true from users where username=?")
		.authoritiesByUsernameQuery("select users_username as principal, roles_role as role from users_roles where users_username= ?")
		.rolePrefix("ROLE_");
			
			
	}
	
@Override
protected void configure(HttpSecurity http) throws Exception {
	
	 
	  http
	 
		.csrf().disable()
		.authorizeRequests()
			.antMatchers("/css/**","/js/**","/images/**").permitAll()
			.anyRequest()
				.authenticated()
					.and()
		.formLogin()
			//.loginPage("/login")
			.permitAll()
			.defaultSuccessUrl("/jsondoc-ui.html");

					
					

	
	
}


}
