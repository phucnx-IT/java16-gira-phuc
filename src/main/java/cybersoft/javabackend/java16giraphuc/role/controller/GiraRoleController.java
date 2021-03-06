package cybersoft.javabackend.java16giraphuc.role.controller;

import java.util.List;

import javax.validation.Valid;

import org.hibernate.annotations.Check;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
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
import cybersoft.javabackend.java16giraphuc.role.dto.GiraRoleWithProgramDTO;
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

	@DeleteMapping("/delete/{role-Id}")
	public Object deleteRole(@PathVariable(name = "role-Id") String roleId) {
		if (ErrorHelper.checkId(roleId)) {
			service.deleteRoleById(roleId);
			return ResponseHelper.getResponse("Deleted !", HttpStatus.OK);
		}
		return ResponseHelper.getErrorResponse("RoleId is incorrect !", HttpStatus.BAD_REQUEST);
	}
	
	@PutMapping("/{role-id}")
	public Object updateRole(@Valid @RequestBody GiraRoleDTO dto,BindingResult result,@PathVariable(name = "role-id") String id) {
		if (result.hasErrors()) {
			return ResponseHelper.getErrorResponse(result, HttpStatus.BAD_REQUEST);
		}
		if (ErrorHelper.checkId(id)) {
			GiraRoleDTO updateRole = service.updateRole(id,dto);
			if (updateRole !=null) {
				return ResponseHelper.getResponse(updateRole, HttpStatus.ACCEPTED);
			}else {
				return ResponseHelper.getErrorResponse("Code has been existed", HttpStatus.BAD_REQUEST);
			}
		}
		return ResponseHelper.getErrorResponse("Id is not valid", HttpStatus.BAD_REQUEST);
	}
	
	@GetMapping("{role-Id}")
	public Object findById(@PathVariable(name="role-Id") String id) {
		if (ErrorHelper.checkId(id)) {
			GiraRoleDTO dto = service.findById(id);
			if (dto==null) {
				return ResponseHelper.getErrorResponse("Role is not exist", HttpStatus.BAD_REQUEST);
			}
			return ResponseHelper.getResponse(dto,	HttpStatus.OK);
		}
		return ResponseHelper.getErrorResponse("Id is not valid",HttpStatus.NOT_ACCEPTABLE);
	}
	@PostMapping("/add-program/{role-id}/{program-id}")
	public Object addProgramIntoRole(@PathVariable(name="role-id") String roleId, @PathVariable(name="program-id") String programId) {
		if (ErrorHelper.checkId(programId)&&ErrorHelper.checkId(roleId)) {
			GiraRoleWithProgramDTO role = service.addProgramIntoRole(roleId,programId);
			if (role!=null) {
				return ResponseHelper.getResponse(role, HttpStatus.ACCEPTED);
			}
		}
		return ResponseHelper.getErrorResponse("Id is not valid", HttpStatus.BAD_REQUEST);
	}
}
