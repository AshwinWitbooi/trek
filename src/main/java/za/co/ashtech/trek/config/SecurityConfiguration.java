package za.co.ashtech.trek.config;

import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import za.co.ashtech.trek.service.TrekUserDetailsService;

@Configuration
@EnableWebSecurity
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {
	
	
	@Value( "${security.unsecure}" )
	private String ignoreURLs;
	
	
	@Override
    public void configure(WebSecurity web) throws Exception {
        web
          .ignoring()
          .antMatchers(ignoreURLs.split(","));
    }

    // Secure the end points with HTTP Basic authentication
    @Override
    protected void configure(HttpSecurity http) throws Exception {
    	http
    	.requiresChannel()
    	.and()
		.authorizeRequests()
			.antMatchers("/trek/v1/*").hasAnyRole("USER","ADMIN")
			.antMatchers("/trek/v1/admin/*").hasRole("ADMIN")
			.anyRequest()
			.fullyAuthenticated()
			.and()
			.httpBasic()
			.and().csrf().disable();
    }
    
    @Autowired
    public void configAuthentication(AuthenticationManagerBuilder auth) throws Exception {
    	auth.userDetailsService(userDetailsService());
    }
    
    @Bean
    public PasswordEncoder delegatingPasswordEncoder() throws NoSuchAlgorithmException {

    	 return new BCryptPasswordEncoder(10, SecureRandom.getInstanceStrong());
    }

    @Bean
	public TrekUserDetailsService userDetailsService() {
		return new TrekUserDetailsService();
	}
}
