package cybersoft.javabackend.java16giraphuc.role.repository;

import static org.junit.Assert.assertNotNull;
import static org.mockito.Mockito.when;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;

import cybersoft.javabackend.java16giraphuc.role.service.GiraProgramService;
import cybersoft.javabackend.java16giraphuc.role.service.GiraProgramServiceImpl;

@SpringBootTest
public class GiraProgramRepositoryTest {

	@Mock
	private GiraProgramRepository repository;
	@InjectMocks
	private GiraProgramService service = new GiraProgramServiceImpl();
	
	@Test
	public void shouldReturnEmptyList() {
		when(repository.findAll()).thenReturn(List.of());
		assertNotNull(null, service.findAllPrograms());
	}
}
