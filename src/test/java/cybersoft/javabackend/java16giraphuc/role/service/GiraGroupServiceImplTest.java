package cybersoft.javabackend.java16giraphuc.role.service;

import static org.junit.Assert.assertTrue;

import java.util.List;
import java.util.UUID;

import javax.transaction.Transactional;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import cybersoft.javabackend.java16giraphuc.role.dto.GiraGroupDTO;
import cybersoft.javabackend.java16giraphuc.role.dto.GiraGroupWithRolesDTO;

@SpringBootTest
@Transactional
public class GiraGroupServiceImplTest{
	@Autowired
	private GiraGroupService service;
	private GiraGroupDTO dto;
	@Test
	public void findAll() {
		List<GiraGroupDTO> list = service.findAll(); 
		assertTrue(list.size()>0);
	}

	@Test
	public void createNewGroup() {
		dto = new GiraGroupDTO();
		dto.setCode("MENTORS");
		dto.setDescription("Group of mentors");
		GiraGroupDTO newDto = service.createNewGroup(dto);
		assertTrue(newDto != null);
	}

	@Test
	public void addRole() {
		String groupID = "20d4cfb2-6d45-437a-a9d3-75d7e9b60b3f";
		String roleID = "11effe87-e5b7-4982-a445-3dce7f6a5d55";
		GiraGroupWithRolesDTO dtoWithRole = service.addRole(groupID, roleID);
		assertTrue(dtoWithRole !=null);
	}
	@Test
	public void updatedGiraGroup() {
		dto = new GiraGroupDTO();
		dto.setCode("MENTORS");
		dto.setDescription("Group of mentors");
		dto.setId(UUID.fromString("20d4cfb2-6d45-437a-a9d3-75d7e9b60b3f"));
		GiraGroupDTO newDto = service.updatedGiraGroup(dto);
		assertTrue(newDto != null);
	}

	@Test
	public void findGroupById() {
		String groupID = "20d4cfb2-6d45-437a-a9d3-75d7e9b60b3f";
		GiraGroupDTO dto = service.findGroupById(groupID);
		assertTrue(dto!=null);
	}

	@Test
	public void findAllGroupsWithRoles() {
		List<GiraGroupWithRolesDTO> list = service.findAllGroupsWithRoles();
		assertTrue(list.size()>0);
	}
	@Test
	public void removeRole() {
		String groupID = "20d4cfb2-6d45-437a-a9d3-75d7e9b60b3f";
		String roleID = "11effe87-e5b7-4982-a445-3dce7f6a5d55";
		GiraGroupWithRolesDTO dtoWithRole = service.removeRole(groupID, roleID);
		assertTrue(dtoWithRole !=null);
	}
}
