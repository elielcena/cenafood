package com.github.cenafood.domain.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.github.cenafood.domain.model.User;

/**
 * @author elielcena
 *
 */
@Repository
public interface UserRepository extends JpaRepository<User, Long> {

	static final String JPQL_USER = "FROM User u LEFT JOIN FETCH u.roles r ";

	@Override
	@Query(JPQL_USER + " WHERE u.id = :id")
	Optional<User> findById(Long id);

	@Query(JPQL_USER + " WHERE u.email = :email")
	Optional<User> findByEmail(String email);

}
