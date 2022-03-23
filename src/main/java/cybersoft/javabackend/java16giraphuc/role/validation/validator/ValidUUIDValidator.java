package cybersoft.javabackend.java16giraphuc.role.validation.validator;

import java.util.UUID;
import java.util.regex.Pattern;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import cybersoft.javabackend.java16giraphuc.role.validation.anotation.ValidUUID;

public class ValidUUIDValidator implements ConstraintValidator<ValidUUID, UUID>{
	private String message;
	
	@Override
	public void initialize(ValidUUID ValidUUID) {
		message = ValidUUID.message();
	}

	@Override
	public boolean isValid(UUID value, ConstraintValidatorContext context) {
		Pattern UUID = Pattern.compile("^[{]?[0-9a-fA-F]{8}-([0-9a-fA-F]{4}-){3}[0-9a-fA-F]{12}[}]?$");
		if (UUID.matcher(value.toString()).matches()) {
			return true;
		}
		context.buildConstraintViolationWithTemplate(message).addConstraintViolation()
		.disableDefaultConstraintViolation();
		return false;
	}
	

}
