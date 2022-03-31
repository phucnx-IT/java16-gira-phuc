package cybersoft.javabackend.java16giraphuc.common.model;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.UUID;

import javax.persistence.EntityListeners;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;
import javax.persistence.Version;

import org.hibernate.annotations.Type;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;


@Setter
@Getter
@NoArgsConstructor
@SuperBuilder
@MappedSuperclass
@EntityListeners(AuditingEntityListener.class)
public class BaseEntity implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = -4238009913618445600L;
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
