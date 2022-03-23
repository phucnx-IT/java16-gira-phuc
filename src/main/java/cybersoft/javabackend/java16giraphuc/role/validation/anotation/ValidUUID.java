package cybersoft.javabackend.java16giraphuc.role.validation.anotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import javax.validation.Constraint;
import javax.validation.Payload;

import cybersoft.javabackend.java16giraphuc.role.validation.validator.ValidUUIDValidator;

@Constraint(validatedBy = ValidUUIDValidator.class)
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.FIELD)
public @interface ValidUUID {
	String message() default "ID is not valid";

	Class<?>[] groups() default {};

	Class<? extends Payload>[] payload() default {};
}
