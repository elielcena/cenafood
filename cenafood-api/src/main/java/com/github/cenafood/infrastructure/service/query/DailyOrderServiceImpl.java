package com.github.cenafood.infrastructure.service.query;

import static org.apache.commons.lang3.BooleanUtils.isTrue;
import static org.springframework.util.ObjectUtils.isEmpty;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.OffsetDateTime;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.criteria.Predicate;

import org.springframework.stereotype.Repository;

import com.github.cenafood.api.v1.model.response.DailyOrderDTO;
import com.github.cenafood.domain.filter.DailyOrderFilter;
import com.github.cenafood.domain.model.Order;
import com.github.cenafood.domain.model.OrderStatus;
import com.github.cenafood.domain.service.DailyOrderService;

/**
 * @author elielcena
 *
 */
@Repository
public class DailyOrderServiceImpl implements DailyOrderService {

	private static final String CREATE_AT = "createdAt";

	@PersistenceContext
	private EntityManager manager;

	@Override
	public List<DailyOrderDTO> findByFilter(DailyOrderFilter filtro) {
		var builder = manager.getCriteriaBuilder();
		var query = builder.createQuery(DailyOrderDTO.class);
		var root = query.from(Order.class);
		var predicates = new ArrayList<Predicate>();

		var functionDateDataCriacao = root.get(CREATE_AT).as(Date.class);

		var selection = builder.construct(DailyOrderDTO.class, functionDateDataCriacao, builder.count(root.get("id")),
				builder.sum(root.get("totalPrice")));

		if (!isEmpty(filtro.getIdRestaurant())) {
			predicates.add(builder.equal(root.get("restaurant"), filtro.getIdRestaurant()));
		}

		if (!isEmpty(filtro.getStartDate())) {
			predicates.add(builder.greaterThanOrEqualTo(root.get(CREATE_AT),
					convertLocalDateToOffsetDateTime(filtro.getStartDate(), Boolean.TRUE)));
		}

		if (!isEmpty(filtro.getEndDate())) {
			predicates.add(builder.lessThanOrEqualTo(root.get(CREATE_AT),
					convertLocalDateToOffsetDateTime(filtro.getEndDate(), Boolean.FALSE)));
		}

		predicates.add(root.get("status").in(OrderStatus.CONFIRMED, OrderStatus.DELIVERED));

		query.select(selection);
		query.groupBy(functionDateDataCriacao);

		return manager.createQuery(query.where(predicates.toArray(new Predicate[0]))).getResultList();
	}

	private OffsetDateTime convertLocalDateToOffsetDateTime(LocalDate date, Boolean init) {
		ZoneId zone = ZoneId.of("America/Sao_Paulo");
		LocalDateTime dataTime = null;

		dataTime = date.atTime(LocalTime.MAX);

		if (isTrue(init))
			dataTime = date.atTime(LocalTime.MIN);

		return OffsetDateTime.of(dataTime, zone.getRules().getOffset(dataTime));
	}
}
