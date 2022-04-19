package cybersoft.javabackend.java16giraphuc.role.repository;

import static org.junit.Assert.assertNotNull;


import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import cybersoft.javabackend.java16giraphuc.role.model.GiraProgram;

@SpringBootTest
public class GiraProgramRepositoryTest {

	@Autowired
	private GiraProgramRepository repository;
	
	@Test
	public void shouldReturnEmptyList() {
		GiraProgram program = repository.findProgramWithNameAndUsername("findAllPrograms", "moderator");
		assertNotNull(program);
	}
}
