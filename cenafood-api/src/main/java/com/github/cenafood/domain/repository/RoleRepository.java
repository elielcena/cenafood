package com.github.cenafood.domain.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.github.cenafood.domain.model.Role;

/**
 * @author elielcena
 *
 */
@Repository
public interface RoleRepository extends JpaRepository<Role, Long> {

	static final String JPQL_ROLE = "FROM Role r LEFT JOIN FETCH r.permissions p";

	@Override
	@Query(JPQL_ROLE + " WHERE r.id = :id")
	Optional<Role> findById(Long id);

}
