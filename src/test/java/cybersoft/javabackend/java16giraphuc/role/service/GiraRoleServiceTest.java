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
@Transactional
public class GiraRoleServiceTest {
	@Autowired
	private GiraRoleService service;
//	@Test
//public void shouldNotSaveGiraRole() {
//	GiraRoleDTO dto = new GiraRoleDTO();
//	dto.setCode("ADMIN");
//	dto.setDescription("Hello");
//	GiraRole role = service.save(dto);
//	assertTrue(role!=null);
//}
//	@Test
//	public void canNotFindAll() {
//		List<GiraRole> role = service.findAllEntity();
//		assertTrue(role.size()>0);
//	}
}
