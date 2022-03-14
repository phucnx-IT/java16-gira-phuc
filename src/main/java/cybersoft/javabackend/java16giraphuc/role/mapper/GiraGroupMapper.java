package cybersoft.javabackend.java16giraphuc.role.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import cybersoft.javabackend.java16giraphuc.role.dto.GiraGroupDTO;
import cybersoft.javabackend.java16giraphuc.role.dto.GiraGroupWithRolesDTO;
import cybersoft.javabackend.java16giraphuc.role.model.GiraGroup;

@Mapper
public interface GiraGroupMapper {
GiraGroupMapper INSTANCE = Mappers.getMapper(GiraGroupMapper.class);
GiraGroup mapToEntity(GiraGroupDTO dto);
GiraGroupDTO mapToGroupDTO(GiraGroup group);
GiraGroupWithRolesDTO toDtoWithRoles(GiraGroup modifiedGroup);
}
