package cybersoft.javabackend.java16giraphuc.security.authorization;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
public @interface GiraProgramAuthorization {
	String value() default "";

	Class<?>[] groups() default {};
}
