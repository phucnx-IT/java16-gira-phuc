package cybersoft.javabackend.java16giraphuc.role.controller;

import java.util.List;

import javax.validation.Valid;

import org.apache.catalina.connector.Response;
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
import cybersoft.javabackend.java16giraphuc.role.dto.GiraGroupDTO;
import cybersoft.javabackend.java16giraphuc.role.dto.GiraGroupWithRolesDTO;
import cybersoft.javabackend.java16giraphuc.role.service.GiraGroupService;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
@RequestMapping("/api/v1/groups")
public class GiraGroupController {
	@Autowired
	private GiraGroupService service;

	@GetMapping
	public Object findAllGroup() {
		log.info("Find all gira groups STARTED");
		log.debug("Calling GiraGroupService.findAllDto()");
		List<GiraGroupDTO> groups = service.findAll();
		log.debug("result:{}", groups);
		log.info("Find all gira groups STOP");
		return ResponseHelper.getResponse(groups, HttpStatus.FOUND);
	}

	@GetMapping("/find-all-groups-with-roles")
	public Object findAllGroupsWithRoles() {
		List<GiraGroupWithRolesDTO> groupWithRoles = service.findAllGroupsWithRoles();
		return ResponseHelper.getResponse(groupWithRoles, HttpStatus.FOUND);
	}

	@PostMapping
	public Object createGiraGroup(@Valid @RequestBody GiraGroupDTO dto, BindingResult result) {
		if (result.hasErrors()) {
			return ResponseHelper.getErrorResponse(result, HttpStatus.BAD_REQUEST);
		}
		GiraGroupDTO newGroup = service.createNewGroup(dto);
		return ResponseHelper.getResponse(newGroup, HttpStatus.ACCEPTED);
	}

	@PutMapping
	public Object updateGiraGroup(@Valid @RequestBody GiraGroupDTO dto, BindingResult result) {
		if (result.hasErrors()) {
			return ResponseHelper.getErrorResponse(result, HttpStatus.BAD_REQUEST);
		}
		GiraGroupDTO updatedGroup = service.updatedGiraGroup(dto);
		return ResponseHelper.getResponse(updatedGroup, HttpStatus.ACCEPTED);
	}

	@DeleteMapping("/delete/{group-id}")
	public Object deleteGiraGroup(@PathVariable(name = "group-id") String groupId) {
		if (ErrorHelper.checkId(groupId)) {
			GiraGroupDTO dto = service.findGroupById(groupId);
			if (dto != null) {
				service.deleteGroupById(groupId);
				return ResponseHelper.getResponse("Completed delete group", HttpStatus.OK);
			} else {
				return ResponseHelper.getErrorResponse("Can't find any groups with the Id", HttpStatus.BAD_REQUEST);
			}
		}
		return ResponseHelper.getErrorResponse("Id is not valid", HttpStatus.BAD_REQUEST);
	}

	@PostMapping("/add-role/{group-id}/{role-id}")
	public Object addRole(@PathVariable(name = "group-id") String groupId,
			@PathVariable(name = "role-id") String roleId) {
		GiraGroupWithRolesDTO modifiedGroup = null;
		if (ErrorHelper.checkId(roleId) && ErrorHelper.checkId(groupId)) {
			modifiedGroup = service.addRole(groupId, roleId);
			if (modifiedGroup == null) {
				return ResponseHelper.getErrorResponse("Group or Role is not existing", HttpStatus.BAD_REQUEST);
			}
		}
		return ResponseHelper.getResponse(modifiedGroup, HttpStatus.CREATED);
	}

	@DeleteMapping("/remove-role/{group-id}/{role-id}")
	public Object removeRole(@PathVariable(name = "group-id") String groupID,
			@PathVariable(name = "role-id") String roleId) {
		GiraGroupWithRolesDTO removeGroup = null;
		if (ErrorHelper.checkId(roleId) && ErrorHelper.checkId(groupID)) {
			removeGroup = service.removeRole(groupID, roleId);
			if (removeGroup == null) {
				return ResponseHelper.getErrorResponse(roleId, HttpStatus.BAD_REQUEST);
			}
		}
		return ResponseHelper.getResponse(removeGroup, HttpStatus.ACCEPTED);
	}

	@DeleteMapping("/remove-all-roles/{group-id}")
	public Object removeAllRole(@PathVariable(name = "group-id") String groupId) {
		if (ErrorHelper.checkId(groupId)) {
			GiraGroupWithRolesDTO removeAllRoles = service.removeAllRoles(groupId);
			if (removeAllRoles != null) {
				return ResponseHelper.getResponse("Completed remove roles in group", HttpStatus.OK);
			} else {
				return ResponseHelper.getErrorResponse("Group is not exist", HttpStatus.BAD_REQUEST);
			}
		}
		return ResponseHelper.getErrorResponse("Id is not valid", HttpStatus.BAD_REQUEST);
	}
	
	@GetMapping("{group-id}")
	public Object findGroupWithRoleById(@PathVariable("group-id") String id) {
		if (ErrorHelper.checkId(id)) {
			GiraGroupWithRolesDTO dto = service.findGroupWithRoleById(id);
			if (dto !=null) {
				return ResponseHelper.getResponse(dto, HttpStatus.ACCEPTED);
			} 
		}
		return ResponseHelper.getErrorResponse("Id is not valid", HttpStatus.BAD_REQUEST);
	}
}
