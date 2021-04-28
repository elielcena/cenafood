package com.github.cenafood.infrastructure.repository.spec;

import java.time.LocalDate;
import java.time.OffsetDateTime;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Optional;

import javax.persistence.criteria.Predicate;

import org.springframework.data.jpa.domain.Specification;

import com.github.cenafood.domain.filter.OrderFilter;
import com.github.cenafood.domain.model.Order;

/**
 * @author elielcena
 *
 */
public class OrderSpecs {

    private static final ZoneId ZONE = ZoneId.systemDefault();

    public static Specification<Order> withFilter(OrderFilter filter) {
        return (root, query, builder) -> {
            if (Order.class.equals(query.getResultType())) {
                root.fetch("restaurant").fetch("kitchen");
                root.fetch("customer");
            }

            var predicates = new ArrayList<Predicate>();

            if (Optional.ofNullable(filter.getIdCustomer()).isPresent()) {
                predicates.add(builder.equal(root.get("customer"), filter.getIdCustomer()));
            }
            if (Optional.ofNullable(filter.getIdRestaurant()).isPresent()) {
                predicates.add(builder.equal(root.get("restaurant"), filter.getIdRestaurant()));
            }
            if (Optional.ofNullable(filter.getStartDate()).isPresent()) {
                predicates.add(builder.greaterThanOrEqualTo(root.get("createdAt"), atStartOfDay(filter.getStartDate())));
            }
            if (Optional.ofNullable(filter.getEndDate()).isPresent()) {
                predicates.add(builder.lessThanOrEqualTo(root.get("createdAt"), atEndOfDay(filter.getStartDate())));
            }

            return builder.and(predicates.toArray(new Predicate[0]));
        };
    }

    public static Specification<Order> withNameSimilar(String name) {
        return (root, query, builder) -> builder.like(root.get("name"), "%" + name + "%");
    }

    public static OffsetDateTime atStartOfDay(LocalDate localDate) {
        return localDate.atStartOfDay(ZONE).toLocalDateTime().atZone(ZONE).toOffsetDateTime();
    }

    public static OffsetDateTime atEndOfDay(LocalDate localDate) {
        return localDate.plusDays(1).atStartOfDay(ZONE).toLocalDateTime().atZone(ZONE).toOffsetDateTime();
    }

}
