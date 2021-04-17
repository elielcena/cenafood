package com.github.cenafood.domain.listener;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.event.TransactionalEventListener;

import com.github.cenafood.domain.event.OrderCanceledEvent;
import com.github.cenafood.domain.event.OrderConfirmedEvent;
import com.github.cenafood.domain.service.SendMailService;
import com.github.cenafood.domain.service.SendMailService.Message;

/**
 * @author elielcena
 *
 */
@Component
public class NotifyOrderListener {

    @Autowired
    private SendMailService sendMailService;

    @TransactionalEventListener
    public void orderConfirmedEvent(OrderConfirmedEvent event) {
        var message = Message.builder()
                .to(event.getOrder().getCustomer().getEmail())
                .subject(event.getOrder().getRestaurant().getName() + " - Pedido confirmado!")
                .body("order-confirmed.html")
                .variable("order", event.getOrder())
                .build();

        sendMailService.send(message);
    }

    @TransactionalEventListener
    public void orderCanceleddEvent(OrderCanceledEvent event) {
        var message = Message.builder()
                .to(event.getOrder().getCustomer().getEmail())
                .subject(event.getOrder().getRestaurant().getName() + " - Pedido cancelado!")
                .body("order-canceled.html")
                .variable("order", event.getOrder())
                .build();

        sendMailService.send(message);
    }

}
