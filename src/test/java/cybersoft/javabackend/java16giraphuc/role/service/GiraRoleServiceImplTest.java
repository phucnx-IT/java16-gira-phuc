package cybersoft.javabackend.java16giraphuc.role.service;

import static org.assertj.core.api.Assertions.assertThatNoException;
import static org.junit.Assert.assertTrue;

import java.util.List;
import java.util.UUID;

import javax.transaction.Transactional;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.repository.support.Repositories;

import cybersoft.javabackend.java16giraphuc.role.dto.GiraRoleDTO;
import cybersoft.javabackend.java16giraphuc.role.mapper.GiraRoleMapper;
import cybersoft.javabackend.java16giraphuc.role.model.GiraRole;

@SpringBootTest
@Transactional
public class GiraRoleServiceImplTest {
	@Autowired
	private GiraRoleService service;
	private GiraRole role;
	private GiraRoleDTO dto;

	@Test
	public void findAllEntity() {
		List<GiraRoleDTO> listDto = service.findAllEntity();
		assertTrue(listDto.size()>0);
	}

	@Test
	public void save() {
		dto = new GiraRoleDTO();
		dto.setCode("FIVES");
		dto.setDescription("Description");
		GiraRoleDTO newDto = service.save(dto);
		assertTrue(newDto != null);
	}

	@Test
	public void updateRole() {
		dto = new GiraRoleDTO();
		dto.setId(UUID.fromString("20d4cfb2-6d45-437a-a9d3-75d7e9b60b3f"));
		dto.setCode("FIVES");
		dto.setDescription("Description");
		GiraRoleDTO updateDto = service.updateRole(dto);
		assertTrue(updateDto != null);
	}

}
