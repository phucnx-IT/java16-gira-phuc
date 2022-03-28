package cybersoft.javabackend.java16giraphuc.security.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import cybersoft.javabackend.java16giraphuc.security.dto.LoginDTO;
import cybersoft.javabackend.java16giraphuc.security.jwt.JwtHelper;
import cybersoft.javabackend.java16giraphuc.user.model.GiraUser;
import cybersoft.javabackend.java16giraphuc.user.repository.GiraUserRepository;

@Service
public class AuthServiceImpl implements AuthService{

	@Autowired
	private GiraUserRepository repository;
	
	@Autowired
	private PasswordEncoder encoder;
	
	@Autowired
	private JwtHelper jwts;
	
	@Override
	public String login(LoginDTO dto) {
		Optional<GiraUser> user = repository.findByUsername(dto.getUsername());
		
		String encodedPassword = user.get().getPassword();	
		
		if (encoder.matches(dto.getPassword(), encodedPassword)) {
			return jwts.generateJwtToken(dto.getUsername());
		}
		return null;
	}

}
