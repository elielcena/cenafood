package com.github.cenafood.domain.model;

import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author elielcena
 *
 */
@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "ROLE")
public class Role {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column
	private Long id;

	@Column(nullable = false)
	private String name;

	@ManyToMany(fetch = FetchType.LAZY)
	@JoinTable(name = "ROLEPERMISSION", joinColumns = @JoinColumn(name = "IDROLE"), inverseJoinColumns = @JoinColumn(name = "IDPERMISSION"))
	private Set<Permission> permissions;

	public Role addOrRemovePermission(Boolean isAdss, Permission permission) {
		if (Boolean.TRUE.equals(isAdss))
			getPermissions().add(permission);
		else
			getPermissions().remove(permission);

		return this;
	}

}
