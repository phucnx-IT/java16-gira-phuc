package cybersoft.javabackend.java16giraphuc.security.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import cybersoft.javabackend.java16giraphuc.common.util.ResponseHelper;
import cybersoft.javabackend.java16giraphuc.security.dto.LoginDTO;
import cybersoft.javabackend.java16giraphuc.security.service.AuthService;

@RestController
@RequestMapping("/api/v1/auth")
public class AuthController {
	@Autowired
	private AuthService service;
	
	@PostMapping("/login")
	public Object login(@Valid @RequestBody LoginDTO dto, BindingResult result) {
		if (result.hasErrors()) {
			return ResponseHelper.getErrorResponse(result, HttpStatus.BAD_REQUEST);
		}
		String jwt = service.login(dto);
		if (jwt == null) {
			return ResponseHelper.getErrorResponse("Password is not correct", HttpStatus.BAD_REQUEST);
		}
		return ResponseHelper.getResponse(jwt,HttpStatus.ACCEPTED);
	}
}
