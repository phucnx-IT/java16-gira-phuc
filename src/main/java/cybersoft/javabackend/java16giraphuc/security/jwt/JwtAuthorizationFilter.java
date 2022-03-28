package cybersoft.javabackend.java16giraphuc.security.jwt;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import io.jsonwebtoken.Jwts;

@Component
public class JwtAuthorizationFilter extends OncePerRequestFilter {

	@Autowired
	private JwtHelper helper;

	@Autowired
	private UserDetailsService service;

	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
			throws ServletException, IOException {
		String token = helper.token(request);
		if (helper.validate(token)) {
			String username = helper.getUserName(token);
			UserDetails userDetails = service.loadUserByUsername(username);
			Authentication auth = new UsernamePasswordAuthenticationToken(username, null, userDetails.getAuthorities());
			SecurityContextHolder.getContext().setAuthentication(auth);
			filterChain.doFilter(request, response);
		}else {
			filterChain.doFilter(request, response);
		}
	}
}
