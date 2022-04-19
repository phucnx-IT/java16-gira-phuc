package cybersoft.javabackend.java16giraphuc.security.authorization;


import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

import cybersoft.javabackend.java16giraphuc.common.exception.UnAuthorizeException;
import cybersoft.javabackend.java16giraphuc.role.model.GiraProgram;
import cybersoft.javabackend.java16giraphuc.role.repository.GiraProgramRepository;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Component
@Aspect
public class AuthorizationAspect {

//	@Pointcut("within(cybersoft.javabackend.java16giraphuc.role.controller)")
//	public void withinPointcut() {
//	};
//
//	@Pointcut("execution(public cybersoft.javabackend.java16giraphuc.role.controller.GiraGroupController.*(..))")
//	public void executionPointcut() {
//	};
//
//	@Pointcut("annotation(cybersoft.javabackend.java16giraphuc.security.authorization.GiraProgramAuthorization)")
//	public void annotationPointcut() {
//	}
//	@Before("annotationPointcut()")
//	public void beforeAdvice(JoinPoint point) {
//
//	}
//	@After("withinPointcut()")
//	public void afterAdvice(JoinPoint point) {
//		
//	}
//	@Around("executionPointcut()")
//	public void aroundAdvice(ProceedingJoinPoint point) {
//		
//	}
	@Autowired
	private GiraProgramRepository repository;

	@Before("@annotation(giraProgram)")
	public void beforeAuthorizer(GiraProgramAuthorization giraProgram) {
		log.info("===========================================================================");
		log.info("AOP:Gira Program " + giraProgram.value() + " has been called");
		log.info("===========================================================================");
		String username = findUsername();
		String programName=giraProgram.value();
		if (!checkPermission(username, programName)) {
			throw new UnAuthorizeException();
		}
	}
	
	private boolean checkPermission(String username, String programName) {
		GiraProgram program = repository.findProgramWithNameAndUsername(programName, username);
		if (program != null) {
			return true;
		}
		return false;
	}

	private String findUsername() {
		return SecurityContextHolder.getContext().getAuthentication().getPrincipal().toString();
	}

	@After("@annotation(giraProgram)")
	public void afterAuthorizer(GiraProgramAuthorization giraProgram) {
		log.info("===========================================================================");
		log.info("AOP:Gira Program " + giraProgram.value() + " has been ended");
		log.info("===========================================================================");
	}

}
