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
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

import org.hibernate.annotations.Type;

import com.fasterxml.jackson.annotation.JsonIgnore;

import cybersoft.javabackend.java16giraphuc.common.model.BaseEntity;
import cybersoft.javabackend.java16giraphuc.user.model.GiraUser;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

@Setter
@Getter
@NoArgsConstructor
@SuperBuilder
@Entity
@Table(name = "gira_group")
public class GiraGroup extends BaseEntity {
	@ManyToMany(cascade = { CascadeType.MERGE, CascadeType.PERSIST })
	@JoinTable(name = "gira_group_user", joinColumns = 
	@JoinColumn(name = "group_id"), inverseJoinColumns = @JoinColumn(name = "user_id"))
	private Set<GiraUser> users = new LinkedHashSet<GiraUser>();
	
	@Size(min = 5, max = 36)
	private String code;
	@NotBlank()
	private String description;
	@ManyToMany(cascade = { CascadeType.MERGE, CascadeType.PERSIST })
	@JoinTable(name = "gira_group_role", joinColumns = 
	@JoinColumn(name = "group_id"), inverseJoinColumns = @JoinColumn(name = "role_id"))
	private Set<GiraRole> roles = new LinkedHashSet<GiraRole>();

	public void addRole(GiraRole role) {
		roles.add(role);
		role.getGroups().add(this);
	}

	public void removeRole(GiraRole role) {
		roles.remove(role);
		role.getGroups().remove(this);
	}

	public void clearRole() {
		this.roles.clear();
	}
}
