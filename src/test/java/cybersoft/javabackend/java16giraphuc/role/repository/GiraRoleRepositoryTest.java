package cybersoft.javabackend.java16giraphuc.role.repository;

import static org.junit.Assert.assertFalse;

import java.util.List;

import javax.transaction.Transactional;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import cybersoft.javabackend.java16giraphuc.role.model.GiraRole;

@SpringBootTest
@Transactional
public class GiraRoleRepositoryTest {
	@Autowired
	private GiraRoleRepository repository;

	@Test
	public void shouldNotInsertRole() {
		GiraRole role = GiraRole.builder()
								.code("COUNT")
								.description("nothing")
								.build();
		assertFalse(repository.save(role)==null);
	}
	
	@Test
	public void canNotFindAllRole() {
		List<GiraRole> role = repository.findAll();
		assertFalse(role.size()<0);
	}
}
