package cybersoft.javabackend.java16giraphuc.role.controller;

import static org.hamcrest.CoreMatchers.containsString;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.List;
import java.util.UUID;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import cybersoft.javabackend.java16giraphuc.role.dto.GiraRoleDTO;
import cybersoft.javabackend.java16giraphuc.role.service.GiraRoleService;

@SpringBootTest
@AutoConfigureMockMvc
@WithMockUser
public class GiraRoleControllerTest {
	@MockBean
	private GiraRoleService service;
	@Autowired
	private MockMvc mockMvc;
	
	private UUID id = UUID.randomUUID();
	
	@Test
	public void shouldReturnEmptyListRole() throws Exception {
		when(service.findAllEntity()).thenReturn(List.of());
		mockMvc.perform(get("/api/v1/roles"))
		.andExpect(status().isNoContent());
	}

	@Test
	public void shouldReturnListWithRole() throws Exception {
		GiraRoleDTO dto = GiraRoleDTO.builder()
				.id(id).code("TEST ROLE")
				.description("Role for testing")
				.build();
		when(service.findAllEntity()).thenReturn(List.of(dto));
		mockMvc.perform(get("/api/v1/roles"))
		.andExpect(status().isFound())
		.andExpect(MockMvcResultMatchers.content().string(containsString("TEST ROLE")))
		.andExpect(MockMvcResultMatchers.content().string(containsString("Role for testing")));
	}

	@Test
	public void shouldNotFindRoleWithId() throws Exception{
		when(service.findById(id.toString())).thenReturn(null);
		mockMvc.perform(get("/api/v1/roles/"+id))
				.andExpect(status().isBadRequest());
	}
	@Test
	public void shouldNotFindRoleWithInvalidId() throws Exception{
		when(service.findById(id.toString())).thenReturn(null);
		mockMvc.perform(get("/api/v1/roles/"+id.toString().substring(0, 10)))
				.andExpect(status().isNotAcceptable());
	}
	@Test
	public void shouldReturnRoleWithRoleId() throws Exception {
		GiraRoleDTO dto = GiraRoleDTO.builder()
				.id(id).code("TEST ROLE")
				.description("Role for testing")
				.build();
		when(service.findById(id.toString())).thenReturn(dto);
		mockMvc.perform(get("/api/v1/roles/"+id))
		.andDo(print())
		.andExpect(status().isOk())
		.andExpect(MockMvcResultMatchers.content().string(containsString("TEST ROLE")))
		.andExpect(MockMvcResultMatchers.content().string(containsString("Role for testing")));
	}
}
