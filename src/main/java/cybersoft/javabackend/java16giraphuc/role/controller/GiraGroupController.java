package cybersoft.javabackend.java16giraphuc.role.controller;

import java.util.List;

import javax.validation.Valid;

import org.apache.catalina.connector.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import cybersoft.javabackend.java16giraphuc.common.util.ErrorHelper;
import cybersoft.javabackend.java16giraphuc.role.dto.GiraGroupDTO;
import cybersoft.javabackend.java16giraphuc.role.service.GiraGroupService;

@RestController
@RequestMapping("/groups")
public class GiraGroupController {
	@Autowired
	private GiraGroupService service;
	@GetMapping
	public Object findAllGroup() {
		List<GiraGroupDTO> groups = service.findAll();
		return new ResponseEntity<>(groups,HttpStatus.OK);
	}
	
}
