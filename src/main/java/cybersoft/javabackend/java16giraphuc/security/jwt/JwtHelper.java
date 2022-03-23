package cybersoft.javabackend.java16giraphuc.security.jwt;

import java.util.Date;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

@Component
public class JwtHelper {

	public String generateJwtToken(Authentication authentication) {
		if (authentication == null) {
			return null;
		}
		UserDetails user = (UserDetails) authentication.getPrincipal();
		
		Date currentDate = new Date();
		
		return Jwts.builder()
				.setSubject(user.getUsername())
				.setIssuedAt(currentDate)
				.setExpiration(new Date(currentDate.getTime()+86400))
				.signWith(SignatureAlgorithm.HS512,"whatissecrect")
				.compact();
	}
}
