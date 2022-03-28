package cybersoft.javabackend.java16giraphuc.security.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import cybersoft.javabackend.java16giraphuc.security.jwt.JwtAuthorizationFilter;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {
	@Autowired
	private UserDetailsService userDetailsService;
	
	@Autowired
	private JwtAuthorizationFilter filter;

	@Bean
	public PasswordEncoder getPasswordEncoder() {
		return new BCryptPasswordEncoder();
	}
	@Override
	@Bean
	public AuthenticationManager authenticationManagerBean() throws Exception {
		return super.authenticationManagerBean();
	}

	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		auth.userDetailsService(userDetailsService).passwordEncoder(getPasswordEncoder());
	}

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		//CORS
		http.cors();
		//SESSION STATELESS
		http.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);
		//CSRF
		http.csrf().disable();
		//API AUTHENTICATION
		http.antMatcher("/api/v1/**").authorizeRequests()
		.antMatchers("/api/v1/auth/login").permitAll()
		.antMatchers("/api/v1/users").permitAll()
		.anyRequest().authenticated();
		//API FILTER
		http.addFilterBefore(filter, UsernamePasswordAuthenticationFilter.class);
	}
	
	

}
