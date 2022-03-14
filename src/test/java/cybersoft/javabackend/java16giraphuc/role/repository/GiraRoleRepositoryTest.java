package cybersoft.javabackend.java16giraphuc.role.repository;

import static org.assertj.core.api.Assertions.assertThatNoException;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertThrows;
import static org.junit.Assert.assertTrue;

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
	public void insertRole() {
		GiraRole role = GiraRole.builder()
								.code("COUNT")
								.description("nothing")
								.build();
		assertThatNoException().isThrownBy(()->repository.save(role));
	}
	
	@Test
	public void findAllRole() {
		List<GiraRole> role = repository.findAll();
		assertTrue(role.size()>0);
	}
}
