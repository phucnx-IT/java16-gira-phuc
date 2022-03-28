package cybersoft.javabackend.java16giraphuc.security.validation;

import java.util.Optional;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import org.springframework.beans.factory.annotation.Autowired;

import cybersoft.javabackend.java16giraphuc.user.model.GiraUser;
import cybersoft.javabackend.java16giraphuc.user.repository.GiraUserRepository;

public class ExistedUserValidator implements ConstraintValidator<ExistedUser, String> {

	@Autowired
	private GiraUserRepository repository;
	
	private String message;
	
	@Override
	public void initialize(ExistedUser ExistedUser) {
		message = ExistedUser.message();
	}

	@Override
	public boolean isValid(String username, ConstraintValidatorContext context) {
		Optional<GiraUser> user = repository.findByUsername(username);
		if (user.isPresent()) {
			return true;
		}
		context.buildConstraintViolationWithTemplate(message)
		.addConstraintViolation()
		.disableDefaultConstraintViolation();
		return false;
	}
	
	
}
