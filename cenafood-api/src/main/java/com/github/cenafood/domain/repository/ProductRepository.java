package com.github.cenafood.domain.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.github.cenafood.domain.model.Product;
import com.github.cenafood.domain.model.Restaurant;

/**
 * @author elielcena
 *
 */
@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {

	static final String JPQL_PRODUCT = "FROM Product p JOIN FETCH p.restaurant r";

	@Query(JPQL_PRODUCT + " WHERE p.id = :id and r.id = :idRestaurant ")
	Optional<Product> findById(@Param("id") Long id, @Param("idRestaurant") Long idRestaurant);

	@Query(JPQL_PRODUCT)
	List<Product> findByRestaurant(Restaurant restaurant);
	
}
