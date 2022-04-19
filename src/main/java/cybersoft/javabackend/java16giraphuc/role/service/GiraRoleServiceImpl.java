package cybersoft.javabackend.java16giraphuc.role.service;


import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import cybersoft.javabackend.java16giraphuc.role.dto.GiraRoleDTO;
import cybersoft.javabackend.java16giraphuc.role.dto.GiraRoleWithProgramDTO;
import cybersoft.javabackend.java16giraphuc.role.mapper.GiraRoleMapper;
import cybersoft.javabackend.java16giraphuc.role.model.GiraProgram;
import cybersoft.javabackend.java16giraphuc.role.model.GiraRole;
import cybersoft.javabackend.java16giraphuc.role.repository.GiraProgramRepository;
import cybersoft.javabackend.java16giraphuc.role.repository.GiraRoleRepository;

@Service
public class GiraRoleServiceImpl implements GiraRoleService {

	@Autowired
	private GiraRoleRepository repository;
	
	@Autowired
	private GiraProgramRepository repositoryProgram;

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
	public void deleteRoleById(String roleId) {
		repository.deleteById(UUID.fromString(roleId));
	}
	
	@Override
	public GiraRoleDTO updateRole(String id, GiraRoleDTO dto) {
		Optional<GiraRole> role= repository.findById(UUID.fromString(id));
		
		if (role.isEmpty()) {
			return null;
		}
		GiraRole updateRole = role.get();
		if (!updateRole.getCode().equals(dto.getCode())) {
			Optional<GiraRole> existedRole = repository.findByCode(dto.getCode());
			if (existedRole.isPresent()) {
				return null;
			}
			updateRole.setCode(dto.getCode());
		}
		updateRole.setDescription(dto.getDescription());
		return GiraRoleMapper.INSTANCE.mapToDTO(repository.save(updateRole));
	}
	
	@Cacheable(cacheNames = "GiraRoleDTO", key = "#id")
	@Override
	public GiraRoleDTO findById(String id) {
		Optional<GiraRole> role = repository.findById(UUID.fromString(id));
		if (role.isEmpty()) {
			return null;
		}
		return GiraRoleMapper.INSTANCE.mapToDTO(role.get());
	}

	@Override
	public GiraRoleWithProgramDTO addProgramIntoRole(String roleId, String programId) {
		GiraRole role = repository.findById(UUID.fromString(roleId)).get();
		GiraProgram program = repositoryProgram.findById(UUID.fromString(programId)).get();
		if (role!=null && program !=null) {
			if(role.getPrograms().stream().anyMatch(t -> t.getId().equals(program.getId()))) {
				return null;
			}
			role.getPrograms().add(program);
			GiraRole modifiedRole = repository.save(role);
			return GiraRoleMapper.INSTANCE.mapToRoleWithProgram(modifiedRole);
		}
		return null;
	}
}
