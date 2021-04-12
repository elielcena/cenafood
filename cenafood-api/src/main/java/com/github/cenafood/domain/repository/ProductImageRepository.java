package com.github.cenafood.domain.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.github.cenafood.domain.model.ProductImage;

/**
 * @author elielcena
 *
 */
@Repository
public interface ProductImageRepository extends JpaRepository<ProductImage, Long> {

    static final String JPQL_PRODUCT_IMAGE =
            "select f FROM ProductImage f JOIN FETCH f.product p JOIN FETCH p.restaurant r WHERE r.id = :idRestaurant AND p.id = :idProduct";

    @Query(JPQL_PRODUCT_IMAGE)
    List<ProductImage> findByIdProductAndIdRestaurant(Long idProduct, Long idRestaurant);

    @Query(JPQL_PRODUCT_IMAGE + " AND f.id = :id")
    Optional<ProductImage> findById(Long id, Long idProduct, Long idRestaurant);
}
