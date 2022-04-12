package cybersoft.javabackend.java16giraphuc.role.service;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.when;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;

import cybersoft.javabackend.java16giraphuc.role.dto.GiraRoleDTO;
import cybersoft.javabackend.java16giraphuc.role.mapper.GiraRoleMapper;
import cybersoft.javabackend.java16giraphuc.role.model.GiraRole;
import cybersoft.javabackend.java16giraphuc.role.repository.GiraRoleRepository;
@DisplayName("GiraRoleServiceTest")
@SpringBootTest
public class GiraRoleServiceTest {
	@Mock
	private GiraRoleRepository repository;
	@Mock
	private GiraRoleMapper mapper;
	@InjectMocks
	private GiraRoleService service = new GiraRoleServiceImpl();

	
	@DisplayName("Should return empty list when no entity found")
	@Test
	public void shouldReturnEmptyListWhenNoEntityFound() {
		when(repository.findAll()).thenReturn(List.of());
		List<GiraRoleDTO> role = service.findAllEntity();
		assertEquals(0, role.size());
	}
	
	@DisplayName("Should return correctly DTO when there is only one entity found")
	@Test
	public void shouldReturnCorrectlyDTOWhenOneEntityFound() {
		UUID id = UUID.randomUUID();
		GiraRole role = GiraRole.builder()
								.id(id)
								.code("TEST ROLE")
								.description("Role for testing")
								.build();
		GiraRoleDTO dto = GiraRoleDTO.builder()
				.id(id)
				.code("TEST ROLE")
				.description("Role for testing")
				.build();
		when(repository.findAll()).thenReturn(List.of(role));
		when(mapper.mapToDTO(role)).thenReturn(dto);
		List<GiraRoleDTO> list = service.findAllEntity();
		assertEquals(1, list.size());
		GiraRoleDTO dtoTest = list.get(0);
		assertEquals(id, dtoTest.getId());
		assertEquals("TEST ROLE", dtoTest.getCode());
		assertEquals("Role for testing", dtoTest.getDescription());
	}
}
