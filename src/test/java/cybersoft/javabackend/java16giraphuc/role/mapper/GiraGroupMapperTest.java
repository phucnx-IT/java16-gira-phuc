package cybersoft.javabackend.java16giraphuc.role.mapper;

import static org.junit.Assert.assertTrue;

import java.util.LinkedHashSet;
import java.util.Set;
import java.util.UUID;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.TestInstance.Lifecycle;
import org.springframework.boot.test.context.SpringBootTest;

import cybersoft.javabackend.java16giraphuc.role.dto.GiraGroupDTO;
import cybersoft.javabackend.java16giraphuc.role.dto.GiraGroupWithRolesDTO;
import cybersoft.javabackend.java16giraphuc.role.model.GiraGroup;
import cybersoft.javabackend.java16giraphuc.role.model.GiraRole;

@SpringBootTest
public class GiraGroupMapperTest {
@Test
public void mapToEntity() {
	GiraGroupDTO dto = new GiraGroupDTO();
	dto.setId(UUID.fromString("20d4cfb2-6d45-437a-a9d3-75d7e9b60b3f"));
	dto.setCode("MANAGER");
	dto.setDescription("Group of managers");
	GiraGroup group = GiraGroupMapper.INSTANCE.mapToEntity(dto);
	assertTrue(group!=null);
}
@Test
public void mapToDto() {
	GiraGroup group = new GiraGroup();
	group.setId(UUID.fromString("20d4cfb2-6d45-437a-a9d3-75d7e9b60b3f"));
	group.setCode("MANAGER");
	group.setDescription("Group of managers");
	GiraGroupDTO dto = GiraGroupMapper.INSTANCE.mapToGroupDTO(group);
	assertTrue(dto!=null);
}
@Test
public void mapToDtoWithRole() {
	Set<GiraRole> roles = new LinkedHashSet<GiraRole>();
	GiraGroup group = new GiraGroup();
	group.setId(UUID.fromString("20d4cfb2-6d45-437a-a9d3-75d7e9b60b3f"));
	group.setCode("MANAGER");
	group.setDescription("Group of managers");
	group.setRoles(roles);
	GiraGroupWithRolesDTO dtoWithRole = GiraGroupMapper.INSTANCE.toDtoWithRoles(group);
	assertTrue(dtoWithRole!=null);
}
}
