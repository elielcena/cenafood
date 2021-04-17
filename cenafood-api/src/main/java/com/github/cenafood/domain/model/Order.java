package com.github.cenafood.domain.model;

import java.math.BigDecimal;
import java.time.OffsetDateTime;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.PrePersist;
import javax.persistence.Table;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.Type;
import org.springframework.data.domain.AbstractAggregateRoot;

import com.github.cenafood.domain.event.OrderCanceledEvent;
import com.github.cenafood.domain.event.OrderConfirmedEvent;
import com.github.cenafood.domain.exception.BusinessException;

import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * @author elielcena
 *
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Entity
@Table(name = "ORDER")
public class Order extends AbstractAggregateRoot<Order> {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Type(type = "pg-uuid")
    @Column(name = "CODE", length = 10, updatable = false, unique = true, nullable = false, columnDefinition = "UUID DEFAULT uuid_generate_v1()")
    private UUID code;

    @Column(name = "SUBTOTAL")
    private BigDecimal subtotal;

    @Column(name = "DELIVERYFEE")
    private BigDecimal deliveryFee;

    @Column(name = "TOTALPRICE")
    private BigDecimal totalPrice;

    @Embedded
    private Address address;

    @Enumerated(EnumType.STRING)
    private OrderStatus status;

    @CreationTimestamp
    @Column(name = "CREATEDAT", nullable = false, columnDefinition = "TIMESTAMP")
    private OffsetDateTime createdAt;

    @Column(name = "CONFIRMEDAT", nullable = false, columnDefinition = "TIMESTAMP")
    private OffsetDateTime confirmedAt;

    @Column(name = "CANCELEDAT", nullable = false, columnDefinition = "TIMESTAMP")
    private OffsetDateTime canceledAt;

    @Column(name = "DELIVERYAT", nullable = false, columnDefinition = "TIMESTAMP")
    private OffsetDateTime deliveredAt;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "IDPAYMENTMETHOD", nullable = false)
    private PaymentMethod paymentMethod;

    @ManyToOne
    @JoinColumn(name = "IDRESTAURANT", nullable = false)
    private Restaurant restaurant;

    @ManyToOne
    @JoinColumn(name = "IDSYSTEMUSER", nullable = false)
    private User customer;

    @OneToMany(mappedBy = "order", cascade = CascadeType.ALL)
    private List<OrderItem> orderItems;

    @PrePersist
    private void generateCodeAndStatus() {
        setCode(UUID.randomUUID());

        if (!Optional.ofNullable(status).isPresent())
            setStatus(OrderStatus.CREATED);
    }

    public void calculateValueTotal() {
        getOrderItems().forEach(OrderItem::calculateTotalPrice);

        this.subtotal = getOrderItems().stream().map(item -> item.getTotalPrice()).reduce(BigDecimal.ZERO,
                BigDecimal::add);

        this.totalPrice = this.subtotal.add(this.deliveryFee);
    }

    public void setDelivery() {
        setDeliveryFee(getRestaurant().getDeliveryFee());
    }

    public void assignOrderToItems() {
        getOrderItems().forEach(item -> item.setOrder(this));
    }

    public Order confirm() {
        setNewStatus(OrderStatus.CONFIRMED);
        setConfirmedAt(OffsetDateTime.now());
        registerEvent(new OrderConfirmedEvent(this));
        return this;
    }

    public Order delivery() {
        setNewStatus(OrderStatus.DELIVERED);
        setDeliveredAt(OffsetDateTime.now());
        return this;
    }

    public Order cancel() {
        setNewStatus(OrderStatus.CANCELED);
        setCanceledAt(OffsetDateTime.now());
        registerEvent(new OrderCanceledEvent(this));
        return this;
    }

    private void setNewStatus(OrderStatus newStatus) {
        if (getStatus().cannotChangeTo(newStatus)) {
            throw new BusinessException(String.format("Order status %s cannot be changed from %s to %s", getCode(),
                    getStatus().getDescription(), newStatus.getDescription()));
        }

        this.status = newStatus;
    }

}
