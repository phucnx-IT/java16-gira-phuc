package cybersoft.javabackend.java16giraphuc.role.service;

import java.util.List;

import cybersoft.javabackend.java16giraphuc.role.dto.GiraRoleDTO;
import cybersoft.javabackend.java16giraphuc.role.model.GiraRole;

public interface GiraRoleService {
	List<GiraRoleDTO> findAllEntity();

	GiraRoleDTO save(GiraRoleDTO dto);

	GiraRoleDTO updateRole(String id, GiraRoleDTO dto);

	void deleteRoleById(String roleId);

	GiraRoleDTO findById(String id);

}
