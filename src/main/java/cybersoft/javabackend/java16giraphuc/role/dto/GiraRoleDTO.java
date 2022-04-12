package cybersoft.javabackend.java16giraphuc.role.dto;


import java.util.UUID;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

import cybersoft.javabackend.java16giraphuc.role.model.GiraRole.GiraRoleBuilder;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class GiraRoleDTO {
	UUID id;
	@Size(min = 5,max = 100,message = "{role.code.size}")
	private String code;
	@NotBlank(message = "{role.description.notblank}")
	private String description;
}
