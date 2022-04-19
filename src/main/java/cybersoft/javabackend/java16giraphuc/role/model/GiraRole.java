package cybersoft.javabackend.java16giraphuc.role.model;

import java.util.LinkedHashSet;

import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;


import com.fasterxml.jackson.annotation.JsonIgnore;

import cybersoft.javabackend.java16giraphuc.common.model.BaseEntity;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

@Setter
@Getter
@NoArgsConstructor
@SuperBuilder
@Entity
@Table(name="gira_role")
public class GiraRole extends BaseEntity{
	@Size(min = 5,max = 100)
	private String code;
	@NotBlank
	private String description;
	@JsonIgnore
	@ManyToMany(mappedBy = "roles")
	private Set<GiraGroup> groups = new LinkedHashSet<GiraGroup>();
	
	@JsonIgnore
	@ManyToMany(cascade = {CascadeType.MERGE, CascadeType.PERSIST})
	@JoinTable(
		name = "gira_role_program",
		joinColumns = @JoinColumn(name = "role_id"),
		inverseJoinColumns = @JoinColumn(name = "program_id")
		
	)
	private Set<GiraProgram> programs = new LinkedHashSet();
}
