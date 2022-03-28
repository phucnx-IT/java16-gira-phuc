package cybersoft.javabackend.java16giraphuc.security.jwt;

import java.util.Date;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Component;

import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.MalformedJwtException;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.SignatureException;
import io.jsonwebtoken.UnsupportedJwtException;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Component
public class JwtHelper {
	private String key = "whatissecrect";
	String prefix = "Bearer ";

	public String generateJwtToken(String username) {
		Date currentDate = new Date();

		return Jwts.builder().setSubject(username).setIssuedAt(currentDate)
				.setExpiration(new Date(currentDate.getTime() + 86400000)).signWith(SignatureAlgorithm.HS512, key)
				.compact();
	}

	public String token(HttpServletRequest request) {
		String jwt = request.getHeader("Authorization");
		if (jwt == null) {
			return null;
		}
		return jwt.substring(prefix.length(), jwt.length());
	}

	public boolean validate(String token) {
		try {
			Jwts.parser().setSigningKey(key).parseClaimsJws(token);
			return true;
		} catch (UnsupportedJwtException e1) {
			log.error("JWT is not supported: {}", e1.getMessage());
		} catch (ExpiredJwtException e2) {
			log.error("JWT is expired: {}", e2.getMessage());
		} catch (MalformedJwtException e3) {
			log.error("JWT form is incorrect: {}", e3.getMessage());
		} catch (SignatureException e4) {
			log.error("JWT signature is incorrect: {}", e4.getMessage());
		} catch (IllegalArgumentException e5) {
			log.error("JWT is illegal: {}", e5.getMessage());
		}
		return false;
	}

	public String getUserName(String token) {
		return Jwts.parser().setSigningKey(key).parseClaimsJws(token).getBody().getSubject();
	}
}
