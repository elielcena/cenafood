package com.github.cenafood.domain.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.github.cenafood.domain.model.Kitchen;

/**
 * @author elielcena
 *
 */
@Repository
public interface KitchenRepository extends JpaRepository<Kitchen, Long> {

	List<Kitchen> findAllByNameContaining(String name);

	Optional<Kitchen> findByName(String name);

	boolean existsByName(String name);

}
