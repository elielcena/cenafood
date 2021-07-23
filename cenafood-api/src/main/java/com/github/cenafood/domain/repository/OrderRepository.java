package com.github.cenafood.domain.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.github.cenafood.domain.model.Order;

/**
 * @author elielcena
 *
 */
@Repository
public interface OrderRepository extends JpaRepository<Order, Long>, JpaSpecificationExecutor<Order> {

    static final String JPQL_RESTAURANT = "FROM Order o JOIN FETCH o.restaurant r JOIN FETCH o.customer u ";

    @Override
    @Query(JPQL_RESTAURANT)
    List<Order> findAll();

    @Query(JPQL_RESTAURANT
            + " JOIN FETCH o.address.city c JOIN FETCH c.state s JOIN FETCH r.kitchen JOIN FETCH r.address.city ci JOIN FETCH ci.state st JOIN FETCH o.paymentMethod p JOIN FETCH o.orderItems oi JOIN FETCH oi.product po WHERE o.code = :code")
    Optional<Order> findByCode(String code);

    @Query("SELECT CASE WHEN count(1) > 0 THEN true ELSE false END FROM Order o JOIN o.restaurant r JOIN r.users u WHERE o.code = :code AND u.id = :idUser")
    Boolean isManagedByUser(String code, Long idUser);

}
