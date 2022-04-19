package cybersoft.javabackend.java16giraphuc.user.service;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import cybersoft.javabackend.java16giraphuc.role.dto.GiraRoleDTO;
import cybersoft.javabackend.java16giraphuc.role.mapper.GiraRoleMapper;
import cybersoft.javabackend.java16giraphuc.role.model.GiraGroup;
import cybersoft.javabackend.java16giraphuc.role.model.GiraRole;
import cybersoft.javabackend.java16giraphuc.user.dto.GiraUserDTO;
import cybersoft.javabackend.java16giraphuc.user.dto.GiraUserRolesDTO;
import cybersoft.javabackend.java16giraphuc.user.dto.GiraUserWithRolesDTO;
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
		GiraUser user = GiraUserMapper.INSTANCE.toEntity(dto);
		// encode before save
		user.setPassword(encoder.encode(dto.getPassword()));

		GiraUser newUser = repository.save(user);

		newUser.setPassword(null);
		return GiraUserMapper.INSTANCE.toDTO(newUser);
	}

	@Override
	public List<GiraUserRolesDTO> findUserWithRolesByUsername(String username) {
		return repository.findUserWithRolesByUsername(username);
	}

	@Override
	public GiraUserWithRolesDTO findRolesByUsernameUsingJoin(String username) {
		GiraUser user = repository.findRolesByUsernameUsingJoin(username);
		if (user == null) {
			return null;
		}
		
		return GiraUserWithRolesDTO.builder().id(user.getId())
											.username(user.getUsername())
											.email(user.getEmail())
											.displayName(user.getDisplayName())
											.roles(getRolesFromUser(user)).build();
	}

	private List<GiraRoleDTO> getRolesFromUser(GiraUser user) {
		List<GiraRoleDTO> roles = new ArrayList<>();
		for (GiraGroup group : user.getGroups()) {
			group.getRoles().forEach(t -> {
				if (roleExisted(roles, t)) {
					return;
				}
				roles.add(GiraRoleMapper.INSTANCE.mapToDTO(t));
			});
		}
		return roles;
	}

	private boolean roleExisted(List<GiraRoleDTO> roles, GiraRole t) {
		for (GiraRoleDTO giraRoleDTO : roles) {
			if (giraRoleDTO.getCode().equals(t.getCode())) {
				return true;
			}
		}
		return false;
	}

}
