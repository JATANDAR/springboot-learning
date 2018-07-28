package au.com.acttab.spring.security;

import javax.sql.DataSource;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;

import au.com.acttab.dao.impl.UserDAOImpl;

/**
 * 
 * https://github.com/spring-guides/gs-securing-web/blob/master/complete/src/main/java/hello/WebSecurityConfig.java
 * http://www.mkyong.com/spring-security/spring-security-form-login-using-database/
 * https://stackoverflow.com/questions/36382970/allow-all-urls-but-one-in-spring-security
 * https://dzone.com/articles/securing-urls-using-springnbspsecurity
 * https://stackoverflow.com/questions/45354858/how-to-secure-only-one-url-with-spring-security-and-permit-all
 * 
 * https://spring.io/blog/2013/07/03/spring-security-java-config-preview-web-security/#web-security-hellowebsecurityconfiguration
 * https://spring.io/blog/2013/07/03/spring-security-java-config-preview-web-security/
 * 
 * http://www.mkyong.com/spring-boot/spring-boot-spring-security-thymeleaf-example/
 * http://www.baeldung.com/spring-boot-security-autoconfiguration
 * https://stackoverflow.com/questions/32757440/how-to-achieve-ldap-authentication-using-spring-securityspring-boot
 * https://medium.com/@lejinkr/ldap-authentication-with-spring-boot-7b2d3fd08277
 * 
 * 
 * https://github.com/arunasujith/spring-boot-ldap-sample/blob/master/src/main/java/org/sample/LdapSecurity.java
 * https://dzone.com/articles/spring-boot-application-connect-to-ldap-userstore
 * https://spring.io/guides/gs/authenticating-ldap/
 * 
 * https://www.boraji.com/spring-security-5-jdbc-based-authentication-example
 **/



@Configuration
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter{
	
	private DataSource dataSource;
	
	private UserDetailsService userDetailsService;
	
	@Override
	  public void configure(WebSecurity web) throws Exception {
	    web
	      .ignoring()
	         .antMatchers("/resources/**"); // #3
	  }
	
	/**
	 * 
	 * http://leaks.wanari.com/2017/11/28/how-to-make-custom-usernamepasswordauthenticationfilter-with-spring-security/
	 * https://docs.spring.io/spring-security/site/docs/4.2.2.RELEASE/guides/html5/form-javaconfig.html
	 * */
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		//logger.debug("Using default configure(HttpSecurity). If subclassed this will potentially override subclass configure(HttpSecurity).");

		
		http
        .authorizeRequests()
            .antMatchers("/add-todo", "/edit*/**","/delete*/**").authenticated()
            .antMatchers("/**").permitAll()
            .and()
			.formLogin().loginPage("/login")
			//.loginProcessingUrl("/login")
			.usernameParameter("emailAddress")
			.passwordParameter("password")
			.defaultSuccessUrl("/welcome", true)
            .failureUrl("/login")
			.permitAll()
			.and()
			.logout()
            .permitAll();
		/*http.authorizeRequests()
		.antMatchers("/admin").authenticated()
		.anyRequest().permitAll()
		.and()
		.csrf().disable();*/
		/*http.authorizeRequests()
			.antMatchers("/", "/welcome").
				.and()
			.formLogin().loginPage("/login").permitAll().and()
			.logout()
            .permitAll();*/
	}
	
	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		
		System.out.println("I am at configure auth");
		auth.userDetailsService(this.userDetailsService);
		//auth.jdbcAuthentication().dataSource(this.dataSource)
		//.usersByUsernameQuery("select user.email_address as user_name, user.password as password, user.enabled as enabled from users as user where user.email_address=?")
		//.authoritiesByUsernameQuery("select email_address as user_name, authority as authority from authorities where email_address=?");
	}
	
	public void setDataSource(DataSource ds) {
		this.dataSource = ds;
	}

	public UserDetailsService getUserDetailsService() {
		return userDetailsService;
	}

	public void setUserDetailsService(UserDetailsService userDetailsService) {
		this.userDetailsService = userDetailsService;
	}
	
	

}
