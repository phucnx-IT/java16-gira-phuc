package cybersoft.javabackend.java16giraphuc.user.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import cybersoft.javabackend.java16giraphuc.common.util.ResponseHelper;
import cybersoft.javabackend.java16giraphuc.user.dto.GiraUserDTO;
import cybersoft.javabackend.java16giraphuc.user.dto.GiraUserRolesDTO;
import cybersoft.javabackend.java16giraphuc.user.dto.GiraUserWithRolesDTO;
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
//	@GetMapping("/{username}/roles")
//	public Object findUserWithRoles(@PathVariable("username") String username) {
//		List<GiraUserRolesDTO> userRoles = service.findUserWithRolesByUsername(username);
//		if (userRoles == null) {
//			return ResponseHelper.getErrorResponse("Username is not existed",HttpStatus.BAD_GATEWAY);
//		}
//		return ResponseHelper.getResponse(userRoles, HttpStatus.OK);
//	}
	
	@GetMapping("/{username}/roles-by-join-query")
	public Object findRolesByUsernameUsingJoin(@PathVariable ("username") String username) {
		GiraUserWithRolesDTO dto = service.findRolesByUsernameUsingJoin(username);
		if (dto !=null) {
			return ResponseHelper.getResponse(dto, HttpStatus.ACCEPTED);
		}
		return ResponseHelper.getErrorResponse("Username is not existed", HttpStatus.BAD_REQUEST);
	}
}
