package com.github.cenafood.domain.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.github.cenafood.domain.model.Order;

/**
 * @author elielcena
 *
 */
@Repository
public interface OrderRepository extends JpaRepository<Order, Long> {

}
