package com.github.cenafood.domain.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.github.cenafood.domain.model.PaymentMethod;

/**
 * @author elielcena
 *
 */
@Repository
public interface PaymentMethodRepository extends JpaRepository<PaymentMethod, Long> {

}
