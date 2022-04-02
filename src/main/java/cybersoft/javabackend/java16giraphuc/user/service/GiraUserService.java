package cybersoft.javabackend.java16giraphuc.user.service;

import java.util.List;

import cybersoft.javabackend.java16giraphuc.user.dto.GiraUserDTO;
import cybersoft.javabackend.java16giraphuc.user.dto.GiraUserRolesDTO;
import cybersoft.javabackend.java16giraphuc.user.dto.GiraUserWithRolesDTO;

public interface GiraUserService {
GiraUserDTO addUser(GiraUserDTO dto);

List<GiraUserRolesDTO> findUserWithRolesByUsername(String username);

GiraUserWithRolesDTO findRolesByUsernameUsingJoin(String username);
}
