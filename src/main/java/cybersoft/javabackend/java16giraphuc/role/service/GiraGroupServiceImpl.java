package cybersoft.javabackend.java16giraphuc.role.service;

import static org.mockito.Mockito.spy;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

import javax.persistence.EntityNotFoundException;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cybersoft.javabackend.java16giraphuc.role.dto.GiraGroupDTO;
import cybersoft.javabackend.java16giraphuc.role.dto.GiraGroupWithRolesDTO;
import cybersoft.javabackend.java16giraphuc.role.mapper.GiraGroupMapper;
import cybersoft.javabackend.java16giraphuc.role.model.GiraGroup;
import cybersoft.javabackend.java16giraphuc.role.model.GiraRole;
import cybersoft.javabackend.java16giraphuc.role.repository.GiraGroupRepository;
import cybersoft.javabackend.java16giraphuc.role.repository.GiraRoleRepository;
@Service
public class GiraGroupServiceImpl implements GiraGroupService {
	@Autowired
	private GiraGroupRepository repository;
	@Autowired
	private GiraRoleRepository roleRepository;
	@Override
	public List<GiraGroupDTO> findAll() {
		List<GiraGroup> groups = repository.findAll();
		List<GiraGroupDTO> dtos = groups.stream()
				.map(t -> GiraGroupMapper.INSTANCE.mapToGroupDTO(t))
				.collect(Collectors.toList());
		return dtos;
	}

	@Override
	public GiraGroupDTO createNewGroup(GiraGroupDTO dto) {
		GiraGroup group = GiraGroupMapper.INSTANCE.mapToEntity(dto);
		GiraGroup newGroup = repository.save(group);
		return GiraGroupMapper.INSTANCE.mapToGroupDTO(newGroup);
	}

	@Override
	public GiraGroupWithRolesDTO addRole(String groupId, String roleId) {
		GiraGroup group;
		GiraRole role;
		try {
			group = repository.getById(UUID.fromString(groupId));
			role = roleRepository.getById(UUID.fromString(roleId));
		} catch (EntityNotFoundException e) {
			return null;
		}
		group.addRole(role);
		GiraGroup modifiedGroup = repository.save(group);
		GiraGroupWithRolesDTO dto = GiraGroupMapper.INSTANCE.toDtoWithRoles(modifiedGroup);		
		return dto;
	}

	@Override
	public GiraGroupWithRolesDTO removeRole(String groupId, String roleId) {
		GiraGroup group;
		GiraRole role;
		try {
			group = repository.getById(UUID.fromString(groupId));
			role = roleRepository.getById(UUID.fromString(roleId));
		} catch (EntityNotFoundException e) {
			return null;
		}
		group.removeRole(role);
		GiraGroup modifiedGroup = repository.save(group);
		GiraGroupWithRolesDTO dto = GiraGroupMapper.INSTANCE.toDtoWithRoles(modifiedGroup);		
		return dto;
	}

	@Override
	public GiraGroupDTO updatedGiraGroup(GiraGroupDTO dto) {
		GiraGroup group = repository.findById(dto.getId()).orElse(null);
		if (group!=null) {
			group.setCode(dto.getCode());
			group.setDescription(dto.getDescription());
			repository.save(group);
			return GiraGroupMapper.INSTANCE.mapToGroupDTO(group);
		}
		return null;
	}

	@Override
	public void deleteGroupById(String groupId) {
			repository.deleteById(UUID.fromString(groupId));
	}

	@Override
	public GiraGroupWithRolesDTO removeAllRoles(String groupId) {
		GiraGroup group = repository.findById(UUID.fromString(groupId)).orElse(null);
		if (group!=null) {
			group.clearRole();
		}
		return GiraGroupMapper.INSTANCE.toDtoWithRoles(group);
	}

	@Override
	public GiraGroupDTO findGroupById(String groupId) {
		GiraGroup group = repository.findById(UUID.fromString(groupId)).orElse(null);
		return GiraGroupMapper.INSTANCE.mapToGroupDTO(group);
	}

	@Override
	public List<GiraGroupWithRolesDTO> findAllGroupsWithRoles() {
		List<GiraGroupWithRolesDTO> allGroupsWithRoles = repository.findAll()
																	.stream().map(t ->GiraGroupMapper.INSTANCE.toDtoWithRoles(t))
																	.collect(Collectors.toList());
		return allGroupsWithRoles;
	}

}
