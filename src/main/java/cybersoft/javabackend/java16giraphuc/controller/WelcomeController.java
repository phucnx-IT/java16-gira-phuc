package cybersoft.javabackend.java16giraphuc.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import lombok.Getter;

@RestController
@RequestMapping
public class WelcomeController {

	@GetMapping("/welcome")
	public Object welcome() {
		return new ResponseEntity<>("Welcome to ...",HttpStatus.OK);
	}
}
