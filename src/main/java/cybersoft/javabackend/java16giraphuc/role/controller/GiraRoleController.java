package cybersoft.javabackend.java16giraphuc.role.controller;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import cybersoft.javabackend.java16giraphuc.role.dto.GiraRoleDTO;
import cybersoft.javabackend.java16giraphuc.role.model.GiraRole;
import cybersoft.javabackend.java16giraphuc.role.service.GiraRoleService;

@RestController
@RequestMapping
public class GiraRoleController {
@Autowired
private GiraRoleService service;

@GetMapping
public Object findAllRole() {
	List<GiraRole> roles = service.findAllEntity();
	return new ResponseEntity<>(roles,HttpStatus.OK);
}
@PostMapping
public Object createNewRole(@Valid @RequestBody GiraRoleDTO dto, BindingResult bindingResult) {
	if (bindingResult.hasErrors()) {
		return new ResponseEntity<>(bindingResult.getAllErrors().stream().map(t ->t.getDefaultMessage()).collect(Collectors.toList()),
				HttpStatus.BAD_REQUEST);
	}
	GiraRole role = service.save(dto);
	return new ResponseEntity<>(role,HttpStatus.CREATED);
}
}
