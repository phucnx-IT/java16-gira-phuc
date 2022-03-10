package cybersoft.javabackend.java16giraphuc.common.model;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.Set;
import java.util.UUID;

import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;
import javax.persistence.Version;

import org.hibernate.annotations.Type;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.annotation.LastModifiedDate;

import cybersoft.javabackend.java16giraphuc.role.model.GiraGroup;
import cybersoft.javabackend.java16giraphuc.role.model.GiraRole;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;


@Setter
@Getter
@NoArgsConstructor
@SuperBuilder
@MappedSuperclass
public class BaseEntity implements Serializable {
	@Id
	@GeneratedValue
	@Type(type = "uuid-char")
	protected UUID id;
	@Version
	protected int version;
	@CreatedDate
	protected LocalDateTime createdAt;
	@CreatedBy
	protected String createBy;
	@LastModifiedDate
	protected LocalDateTime lastModifiedAt;
	@LastModifiedBy
	protected String lastModifiedBy;
}
