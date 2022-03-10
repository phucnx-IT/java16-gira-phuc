package cybersoft.javabackend.java16giraphuc.role.service;

import static org.junit.Assert.assertTrue;

import java.util.List;

import javax.transaction.Transactional;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import cybersoft.javabackend.java16giraphuc.role.dto.GiraRoleDTO;
import cybersoft.javabackend.java16giraphuc.role.model.GiraRole;

@SpringBootTest

public class GiraRoleServiceImplTest {
	@Autowired
private GiraRoleServiceImpl implement;
	@Test
	public void canNotSaveRole() {
		GiraRoleDTO dto = new GiraRoleDTO();
		dto.setCode("COUNT");
		dto.setDescription("H");
		GiraRole role = implement.save(dto);
		assertTrue(role!=null);
	}
	@Test
	public void canNotFindAllRole() {
		List<GiraRole> role = implement.findAllEntity();
		assertTrue(role.size()>0);
	}
}
