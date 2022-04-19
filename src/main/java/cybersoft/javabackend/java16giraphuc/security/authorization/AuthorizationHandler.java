package cybersoft.javabackend.java16giraphuc.security.authorization;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import cybersoft.javabackend.java16giraphuc.common.exception.UnAuthorizeException;
import cybersoft.javabackend.java16giraphuc.common.util.ResponseHelper;

@RestControllerAdvice
public class AuthorizationHandler {
	private static final String MESSAGE="You do not have permission to call this operation. Please contact admin to get suitable permission";
	@ExceptionHandler(UnAuthorizeException.class)
public Object handlerUnauthorizeException() {
	
		return ResponseHelper.getErrorResponse(MESSAGE, HttpStatus.UNAUTHORIZED);
}
}
