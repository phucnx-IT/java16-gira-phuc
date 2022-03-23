package cybersoft.javabackend.java16giraphuc.role.dto;

import java.util.UUID;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

import cybersoft.javabackend.java16giraphuc.role.validation.anotation.ValidUUID;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class GiraGroupDTO {
	@ValidUUID(message = "{group.UUID.valid}")
	private UUID id;
	@Size(min = 5, max = 36, message = "{group.code.size}")
	private String code;
	@NotBlank(message = "{group.description.notblank}")
	private String description;
}
