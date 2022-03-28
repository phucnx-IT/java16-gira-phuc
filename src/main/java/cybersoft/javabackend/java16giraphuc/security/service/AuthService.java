package cybersoft.javabackend.java16giraphuc.security.service;

import cybersoft.javabackend.java16giraphuc.security.dto.LoginDTO;

public interface AuthService {
	String login(LoginDTO dto);
}
