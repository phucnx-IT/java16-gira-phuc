package cybersoft.javabackend.java16giraphuc.role.mapper;


import static org.junit.Assert.assertFalse;

import java.util.UUID;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import cybersoft.javabackend.java16giraphuc.role.dto.GiraRoleDTO;
import cybersoft.javabackend.java16giraphuc.role.model.GiraRole;

@SpringBootTest
public class GiraRoleMapperTest {
	@Test
public void mapToEntity() {
	GiraRoleDTO dto = new GiraRoleDTO();
	dto.setId(UUID.fromString("20d4cfb2-6d45-437a-a9d3-75d7e9b60b3f"));
	dto.setCode("FIVES");
	dto.setDescription("Description");
	GiraRole role = GiraRoleMapper.INSTANCE.mapToEntity(dto);
	assertFalse(role==null);
}
	@Test
public void mapToDto() {
	GiraRole role = new GiraRole();
	role.setId(UUID.fromString("20d4cfb2-6d45-437a-a9d3-75d7e9b60b3f"));
	role.setCode("FIVES");
	role.setDescription("Description");
	GiraRoleDTO dto = GiraRoleMapper.INSTANCE.mapToDTO(role);
	assertFalse(dto==null);
}
}
