package cybersoft.javabackend.java16giraphuc.role.service;

import java.util.List;

import cybersoft.javabackend.java16giraphuc.role.dto.GiraProgramDTO;

public interface GiraProgramService {

	List<GiraProgramDTO> findAllPrograms();

	GiraProgramDTO save(GiraProgramDTO dto);

}
