package com.github.cenafood.domain.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.github.cenafood.domain.model.Restaurant;

/**
 * @author elielcena
 *
 */
@Repository
public interface RestaurantRepository extends JpaRepository<Restaurant, Long> {

	static final String JPQL_RESTAURANT = "FROM Restaurant r JOIN FETCH r.kitchen k JOIN FETCH r.address.city c JOIN FETCH c.state";

	@Query(JPQL_RESTAURANT)
	List<Restaurant> findAll();

	@Query(JPQL_RESTAURANT + " WHERE name LIKE %:name% AND k.id = :id")
	List<Restaurant> findByName(String name, Long id);

}
