package com.github.cenafood.domain.event;

import com.github.cenafood.domain.model.Order;

import lombok.AllArgsConstructor;
import lombok.Data;

/**
 * @author elielcena
 *
 */
@Data
@AllArgsConstructor
public class OrderConfirmedEvent {

    private Order order;

}
