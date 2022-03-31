package cybersoft.javabackend.java16giraphuc.role.dto;


import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class GiraRoleDTO {
	@Size(min = 5,max = 100,message = "{role.code.size}")
	private String code;
	@NotBlank(message = "{role.description.notblank}")
	private String description;
}
