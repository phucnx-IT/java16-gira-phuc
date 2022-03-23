package cybersoft.javabackend.java16giraphuc.user.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import cybersoft.javabackend.java16giraphuc.common.util.ResponseHelper;
import cybersoft.javabackend.java16giraphuc.user.dto.GiraUserDTO;
import cybersoft.javabackend.java16giraphuc.user.service.GiraUserService;

@RestController
@RequestMapping("/api/v1/users")
public class GiraUserController {
	@Autowired
	private GiraUserService service;
	@PostMapping
	public Object addUser(@Valid @RequestBody GiraUserDTO dto, BindingResult result) {
		if (result.hasErrors()) {
			return ResponseHelper.getErrorResponse(result, HttpStatus.BAD_REQUEST);
		}
		GiraUserDTO newUser = service.addUser(dto);
		return ResponseHelper.getResponse(newUser, HttpStatus.ACCEPTED);
	}
}
