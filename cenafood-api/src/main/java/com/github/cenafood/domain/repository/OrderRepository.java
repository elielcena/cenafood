package com.github.cenafood.domain.repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.github.cenafood.domain.model.Order;

/**
 * @author elielcena
 *
 */
@Repository
public interface OrderRepository extends JpaRepository<Order, Long> {

	static final String JPQL_RESTAURANT = "FROM Order o JOIN FETCH o.restaurant r JOIN FETCH o.customer u ";

	@Override
	@Query(JPQL_RESTAURANT)
	List<Order> findAll();

	@Query(JPQL_RESTAURANT
			+ " JOIN FETCH o.address.city c JOIN FETCH c.state s JOIN FETCH r.kitchen JOIN FETCH r.address.city ci JOIN FETCH ci.state st JOIN FETCH o.paymentMethod p JOIN FETCH o.orderItems oi JOIN FETCH oi.product po WHERE o.code = :code")
	Optional<Order> findByCode(UUID code);

}
