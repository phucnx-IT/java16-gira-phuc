package cybersoft.javabackend.java16giraphuc.role.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cybersoft.javabackend.java16giraphuc.role.dto.GiraGroupDTO;
import cybersoft.javabackend.java16giraphuc.role.mapper.GiraGroupMapper;
import cybersoft.javabackend.java16giraphuc.role.model.GiraGroup;
import cybersoft.javabackend.java16giraphuc.role.repository.GiraGroupRepository;
@Service
public class GiraGroupServiceImpl implements GiraGroupService {
	@Autowired
	private GiraGroupRepository repository;
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

}
