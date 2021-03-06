package com.github.cenafood.domain.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.github.cenafood.domain.model.State;

/**
 * @author elielcena
 *
 */
@Repository
public interface StateRepository extends JpaRepository<State, Long> {

    Optional<State> findByUf(String uf);

}
