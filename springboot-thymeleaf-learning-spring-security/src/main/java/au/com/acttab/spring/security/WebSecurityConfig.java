package au.com.acttab.spring.security;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

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
 **/



@Configuration
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter{
	
	@Override
	  public void configure(WebSecurity web) throws Exception {
	    web
	      .ignoring()
	         .antMatchers("/resources/**"); // #3
	  }
	
	
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		//logger.debug("Using default configure(HttpSecurity). If subclassed this will potentially override subclass configure(HttpSecurity).");

		http
        .authorizeRequests()
            .antMatchers("/add-todo", "/edit*/**").authenticated()
            .antMatchers("/**").permitAll()
            .and()
			.formLogin().loginPage("/login").permitAll().and()
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

}
