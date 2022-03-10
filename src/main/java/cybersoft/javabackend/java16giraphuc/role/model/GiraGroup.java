package cybersoft.javabackend.java16giraphuc.role.model;

import java.util.LinkedHashSet;
import java.util.Set;
import java.util.UUID;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

import org.hibernate.annotations.Type;

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
@Table(name="gira_group")
public class GiraGroup extends BaseEntity {
	private String code;
	private String description;
	@ManyToMany(cascade = {CascadeType.MERGE,CascadeType.PERSIST})
	@JoinTable(
	name="gira_group_role",
	joinColumns = @JoinColumn(name="group_id"),
	inverseJoinColumns = @JoinColumn(name="role_id"))
	private Set<GiraRole> roles = new LinkedHashSet<GiraRole>();

}