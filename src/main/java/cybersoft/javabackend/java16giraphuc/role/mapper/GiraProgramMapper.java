package cybersoft.javabackend.java16giraphuc.role.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import cybersoft.javabackend.java16giraphuc.role.dto.GiraGroupDTO;
import cybersoft.javabackend.java16giraphuc.role.dto.GiraProgramDTO;
import cybersoft.javabackend.java16giraphuc.role.model.GiraGroup;
import cybersoft.javabackend.java16giraphuc.role.model.GiraProgram;
@Mapper
public interface GiraProgramMapper {
	GiraProgramMapper INSTANCE = Mappers.getMapper(GiraProgramMapper.class);
	GiraProgram mapToEntity(GiraProgramDTO dto);
	GiraProgramDTO mapToProgramDTO(GiraProgram group);
}
