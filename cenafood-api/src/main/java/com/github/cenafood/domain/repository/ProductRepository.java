package com.github.cenafood.domain.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.github.cenafood.domain.model.Product;

/**
 * @author elielcena
 *
 */
@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {

}
