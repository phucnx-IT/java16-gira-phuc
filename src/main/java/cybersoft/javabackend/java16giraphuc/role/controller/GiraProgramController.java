package cybersoft.javabackend.java16giraphuc.role.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import cybersoft.javabackend.java16giraphuc.common.util.ResponseHelper;
import cybersoft.javabackend.java16giraphuc.role.dto.GiraProgramDTO;
import cybersoft.javabackend.java16giraphuc.role.dto.GiraRoleDTO;
import cybersoft.javabackend.java16giraphuc.role.model.GiraProgram;
import cybersoft.javabackend.java16giraphuc.role.service.GiraProgramService;
import cybersoft.javabackend.java16giraphuc.security.authorization.GiraProgramAuthorization;

@RestController
@RequestMapping("/api/v1/program")
public class GiraProgramController {
	@Autowired
	private GiraProgramService service;

	@GiraProgramAuthorization("findAllPrograms")
	@GetMapping
	public Object findAllPrograms() {
		List<GiraProgramDTO> program = service.findAllPrograms();
		if (program.isEmpty()) {
			return ResponseHelper.getErrorResponse("We don't have any roles", HttpStatus.NO_CONTENT);
		}
		return ResponseHelper.getResponse(program, HttpStatus.FOUND);
	}
	@GiraProgramAuthorization("createNewProgram")
	@PostMapping
	public Object createNewProgram(@Valid @RequestBody GiraProgramDTO dto, BindingResult bindingResult) {
		if (bindingResult.hasErrors()) {
			return ResponseHelper.getErrorResponse(bindingResult, HttpStatus.BAD_REQUEST);
		}
		GiraProgramDTO createdProgram = service.save(dto);
		return ResponseHelper.getResponse(createdProgram, HttpStatus.ACCEPTED);
	}
}
