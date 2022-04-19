package cybersoft.javabackend.java16giraphuc.role.dto;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import cybersoft.javabackend.java16giraphuc.role.model.GiraModule;
import cybersoft.javabackend.java16giraphuc.role.model.GiraProgramType;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

@Getter
@Setter
@SuperBuilder
@NoArgsConstructor
public class GiraProgramDTO {
	@NotBlank
	private String name;
	@NotNull
	private GiraProgramType type;
	@NotNull
	private GiraModule module;
	@NotBlank
	private String description;
}
