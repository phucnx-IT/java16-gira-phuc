package cybersoft.javabackend.java16giraphuc.user.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import cybersoft.javabackend.java16giraphuc.user.dto.GiraUserDTO;
import cybersoft.javabackend.java16giraphuc.user.model.GiraUser;
@Mapper
public interface GiraUserMapper {
	GiraUserMapper INSTANCE = Mappers.getMapper(GiraUserMapper.class);
	
	GiraUser toEntity(GiraUserDTO dto);
	
	GiraUserDTO toDTO(GiraUser model);
}
