package cybersoft.javabackend.java16giraphuc.role.service;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cybersoft.javabackend.java16giraphuc.role.dto.GiraRoleDTO;
import cybersoft.javabackend.java16giraphuc.role.mapper.GiraRoleMapper;
import cybersoft.javabackend.java16giraphuc.role.model.GiraRole;
import cybersoft.javabackend.java16giraphuc.role.repository.GiraRoleRepository;

@Service
public class GiraRoleServiceImpl implements GiraRoleService {

	@Autowired
	private GiraRoleRepository repository;

	@Override
	public List<GiraRoleDTO> findAllEntity() {
		return repository.findAll().stream()
									.map(t ->GiraRoleMapper.INSTANCE.mapToDTO(t))
									.collect(Collectors.toList());
	}

	@Override
	public GiraRoleDTO save(GiraRoleDTO dto) {
		GiraRole role = GiraRoleMapper.INSTANCE.mapToEntity(dto);
		if (role ==null) {
		return null;	
		}
		GiraRole modifiedRole = repository.save(role);
		return GiraRoleMapper.INSTANCE.mapToDTO(modifiedRole);
	}

	@Override
	public GiraRoleDTO updateRole(GiraRoleDTO dto) {
		GiraRole role=null;
		try {
			role = repository.findById(dto.getId()).orElse(null);
		} catch (IllegalArgumentException e) {
		}
		if (role!=null) {
			role.setCode(dto.getCode());
			role.setDescription(dto.getDescription());
			repository.save(role);
			return GiraRoleMapper.INSTANCE.mapToDTO(role);
		}
		return null;
	}

	@Override
	public void deleteRole(String roleId) {
		repository.deleteById(UUID.fromString(roleId));
	}
}
