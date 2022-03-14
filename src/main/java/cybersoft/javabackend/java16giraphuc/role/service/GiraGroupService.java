package cybersoft.javabackend.java16giraphuc.role.service;

import java.util.List;

import javax.validation.Valid;

import cybersoft.javabackend.java16giraphuc.role.dto.GiraGroupDTO;
import cybersoft.javabackend.java16giraphuc.role.dto.GiraGroupWithRolesDTO;
import cybersoft.javabackend.java16giraphuc.role.model.GiraGroup;

public interface GiraGroupService {
List<GiraGroupDTO> findAll();
GiraGroupDTO createNewGroup(GiraGroupDTO dto);
GiraGroupWithRolesDTO addRole(String groupId, String roleId);
GiraGroupWithRolesDTO removeRole(String groupId, String roleId);
GiraGroupDTO updatedGiraGroup(GiraGroupDTO dto);
void deleteGroupById(String groupId);
GiraGroupWithRolesDTO removeAllRoles(String groupId);
GiraGroupDTO findGroupById(String groupId);
List<GiraGroupWithRolesDTO> findAllGroupsWithRoles();
}
