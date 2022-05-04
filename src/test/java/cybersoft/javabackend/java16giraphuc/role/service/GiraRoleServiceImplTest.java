package cybersoft.javabackend.java16giraphuc.role.service;

import static org.junit.Assert.assertNotEquals;
import static org.mockito.Mockito.when;

import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;

import cybersoft.javabackend.java16giraphuc.role.dto.GiraRoleDTO;
import cybersoft.javabackend.java16giraphuc.role.mapper.GiraRoleMapper;
import cybersoft.javabackend.java16giraphuc.role.model.GiraRole;
import cybersoft.javabackend.java16giraphuc.role.repository.GiraRoleRepository;

@SpringBootTest
public class GiraRoleServiceImplTest {
@Mock
private GiraRoleRepository repository;
@InjectMocks
private GiraRoleService service = new GiraRoleServiceImpl();

@Test
public void shouldCreateAnRole() {
	GiraRoleDTO dto = GiraRoleDTO.builder().code("MENTOR").description("Mentors of cybersoft").build();
	GiraRole role = GiraRoleMapper.INSTANCE.mapToEntity(dto);
	when(repository.save(role)).thenReturn(role);
	assertNotEquals(null, service.save(dto));
}
}
