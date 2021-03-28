package com.github.cenafood.domain.repository;

import java.util.List;
import java.util.Optional;

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

	static final String JPQL_RESTAURANT = "FROM Restaurant r LEFT JOIN FETCH r.kitchen k LEFT JOIN FETCH r.address.city c LEFT JOIN FETCH c.state LEFT JOIN FETCH r.users u";

	static final String JPQL_RESTAURANT_USERS = "FROM Restaurant r JOIN FETCH r.users u";

	@Override
	@Query(JPQL_RESTAURANT)
	List<Restaurant> findAll();

	@Override
	@Query("FROM Restaurant r LEFT JOIN FETCH r.paymentMethods JOIN FETCH r.address.city c JOIN FETCH c.state s WHERE r.id = :id")
	Optional<Restaurant> findById(Long id);

	@Query(JPQL_RESTAURANT + " WHERE name LIKE %:name% AND k.id = :id")
	List<Restaurant> findByName(String name, Long id);

}
