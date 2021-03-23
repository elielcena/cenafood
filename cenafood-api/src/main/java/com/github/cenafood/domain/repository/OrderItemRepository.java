package com.github.cenafood.domain.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.github.cenafood.domain.model.OrderItem;

/**
 * @author elielcena
 *
 */
@Repository
public interface OrderItemRepository extends JpaRepository<OrderItem, Long> {

}
