package cybersoft.javabackend.java16giraphuc.role.service;

import java.util.List;

import cybersoft.javabackend.java16giraphuc.role.dto.GiraRoleDTO;
import cybersoft.javabackend.java16giraphuc.role.model.GiraRole;

public interface GiraRoleService {
	List<GiraRole> findAllEntity();

	GiraRole save(GiraRoleDTO dto);

}
