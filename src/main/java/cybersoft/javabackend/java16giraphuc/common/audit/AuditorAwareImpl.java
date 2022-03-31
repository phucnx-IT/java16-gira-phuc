package cybersoft.javabackend.java16giraphuc.common.audit;

import java.util.Optional;

import org.springframework.data.domain.AuditorAware;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

@Component
public class AuditorAwareImpl implements AuditorAware<String> {

	@Override
	public Optional<String> getCurrentAuditor() {
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		if (auth == null) {
			return Optional.ofNullable("");
		}
		if (auth.getPrincipal() instanceof String principal) {
			return Optional.ofNullable(principal);
		}
		UserDetails user = (UserDetails) auth.getPrincipal();
		return Optional.ofNullable(user.getUsername());
	}

}
