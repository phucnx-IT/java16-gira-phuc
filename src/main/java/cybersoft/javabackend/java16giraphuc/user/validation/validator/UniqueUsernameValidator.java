package cybersoft.javabackend.java16giraphuc.user.validation.validator;

import java.util.Optional;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import org.springframework.beans.factory.annotation.Autowired;

import cybersoft.javabackend.java16giraphuc.user.model.GiraUser;
import cybersoft.javabackend.java16giraphuc.user.repository.GiraUserRepository;
import cybersoft.javabackend.java16giraphuc.user.validation.anotation.UniqueUsername;

public class UniqueUsernameValidator implements ConstraintValidator<UniqueUsername, String> {
	
	private String message;
	@Autowired
	private GiraUserRepository repository;

	@Override
	public void initialize(UniqueUsername UniqueUsername) {
		message = UniqueUsername.message();
	}

	@Override
	public boolean isValid(String username, ConstraintValidatorContext context) {
		if (username == null) {
			return false;
		}
		Optional<GiraUser> user = repository.findByUsername(username);
		if (user.isEmpty()) {
			return true;
		}
		context.buildConstraintViolationWithTemplate(message).addConstraintViolation()
				.disableDefaultConstraintViolation();
		return false;
	}

}
