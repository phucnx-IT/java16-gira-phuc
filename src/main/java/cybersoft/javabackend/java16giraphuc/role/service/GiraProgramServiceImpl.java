package cybersoft.javabackend.java16giraphuc.role.service;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cybersoft.javabackend.java16giraphuc.role.dto.GiraProgramDTO;
import cybersoft.javabackend.java16giraphuc.role.mapper.GiraProgramMapper;
import cybersoft.javabackend.java16giraphuc.role.model.GiraProgram;
import cybersoft.javabackend.java16giraphuc.role.repository.GiraProgramRepository;

@Service
public class GiraProgramServiceImpl implements GiraProgramService{
	@Autowired
	private GiraProgramRepository repository;

	@Override
	public List<GiraProgramDTO> findAllPrograms() {
		return repository.findAll().stream()
									.map(t ->GiraProgramMapper.INSTANCE.mapToProgramDTO(t))
									.collect(Collectors.toList());
	}

	@Override
	public GiraProgramDTO save(GiraProgramDTO dto) {
		GiraProgram program = GiraProgramMapper.INSTANCE.mapToEntity(dto);
		if (program ==null) {
		return null;	
		}
		GiraProgram modifiedRole = repository.save(program);
		return GiraProgramMapper.INSTANCE.mapToProgramDTO(modifiedRole);
	}

}
