package cybersoft.javabackend.java16giraphuc.role.service;

import java.util.List;

import cybersoft.javabackend.java16giraphuc.role.dto.GiraGroupDTO;
import cybersoft.javabackend.java16giraphuc.role.model.GiraGroup;

public interface GiraGroupService {
List<GiraGroupDTO> findAll();
GiraGroupDTO createNewGroup(GiraGroupDTO dto);
}
