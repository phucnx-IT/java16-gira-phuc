package cybersoft.javabackend.java16giraphuc.user.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import cybersoft.javabackend.java16giraphuc.user.dto.GiraUserDTO;
import cybersoft.javabackend.java16giraphuc.user.mapper.GiraUserMapper;
import cybersoft.javabackend.java16giraphuc.user.model.GiraUser;
import cybersoft.javabackend.java16giraphuc.user.repository.GiraUserRepository;

@Service
public class GiraUserServiceImpl implements GiraUserService {

	@Autowired
	private GiraUserRepository repository;
	
	@Autowired
	private PasswordEncoder encoder;
	
	@Override
	public GiraUserDTO addUser(GiraUserDTO dto) {
		GiraUser  user = GiraUserMapper.INSTANCE.toEntity(dto);
		//encode before save
		user.setPassword(encoder.encode(dto.getPassword()));
		
		GiraUser newUser = repository.save(user);
		
		newUser.setPassword(null);
		return GiraUserMapper.INSTANCE.toDTO(newUser);
	}

}
