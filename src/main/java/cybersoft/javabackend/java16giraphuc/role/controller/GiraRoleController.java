package cybersoft.javabackend.java16giraphuc.role.controller;

import java.util.Collections;
import java.util.List;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import cybersoft.javabackend.java16giraphuc.common.util.ErrorHelper;
import cybersoft.javabackend.java16giraphuc.common.util.ResponseHelper;
import cybersoft.javabackend.java16giraphuc.role.dto.GiraRoleDTO;
import cybersoft.javabackend.java16giraphuc.role.model.GiraRole;
import cybersoft.javabackend.java16giraphuc.role.service.GiraRoleService;

@RestController
@RequestMapping("/api/v1/roles")
public class GiraRoleController {
	@Autowired
	private GiraRoleService service;

	@GetMapping
	public Object findAllRole() {
		List<GiraRoleDTO> roles = service.findAllEntity();
		if (roles.isEmpty()) {
			return ResponseHelper.getErrorResponse("We don't have any roles", HttpStatus.NO_CONTENT);
		}
		return ResponseHelper.getResponse(roles, HttpStatus.FOUND);
	}

	@PostMapping
	public Object createNewRole(@Valid @RequestBody GiraRoleDTO dto, BindingResult bindingResult) {
		if (bindingResult.hasErrors()) {
			return ResponseHelper.getErrorResponse(bindingResult, HttpStatus.BAD_REQUEST);
		}
		GiraRoleDTO createdRole = service.save(dto);
		return ResponseHelper.getResponse(createdRole, HttpStatus.ACCEPTED);
	}

	@PutMapping
	public Object updateRole(@Valid @RequestBody GiraRoleDTO dto, BindingResult result) {
		if (result.hasErrors()) {
			return ResponseHelper.getErrorResponse(result, HttpStatus.BAD_REQUEST);
		}
		GiraRoleDTO updateRole = service.updateRole(dto);
		return ResponseHelper.getResponse(updateRole, HttpStatus.ACCEPTED);
	}

	@DeleteMapping("/delete/{role-Id}")
	public Object deleteRole(@PathVariable(name = "role-Id") String roleId) {
		if (ErrorHelper.checkId(roleId)) {
			service.deleteRoleById(roleId);
			return ResponseHelper.getResponse("Deleted !", HttpStatus.OK);
		}
		return ResponseHelper.getErrorResponse("RoleId is incorrect !", HttpStatus.BAD_REQUEST);
	}
}
