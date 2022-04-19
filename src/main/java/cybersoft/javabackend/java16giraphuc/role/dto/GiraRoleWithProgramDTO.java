package cybersoft.javabackend.java16giraphuc.role.dto;

import java.util.Set;
import java.util.UUID;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class GiraRoleWithProgramDTO {
	private UUID id;
	private String code;
	private String description;
	private Set<GiraProgramDTO> programs;
}
