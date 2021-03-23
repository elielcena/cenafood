package com.github.cenafood.domain.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.github.cenafood.domain.model.City;

/**
 * @author elielcena
 *
 */
@Repository
public interface CityRepository extends JpaRepository<City, Long> {

	@Override
	@Query("FROM City c JOIN FETCH c.state")
	List<City> findAll();
}
