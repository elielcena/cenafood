package com.github.cenafood.domain.model;

import static java.util.Optional.ofNullable;

import java.math.BigDecimal;
import java.time.OffsetDateTime;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.PrePersist;
import javax.persistence.Table;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author elielcena
 *
 */
@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "RESTAURANT")
public class Restaurant {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column
    private Long id;

    @Column(nullable = false)
    private String name;

    @Column(name = "DELIVERYFEE", nullable = false)
    private BigDecimal deliveryFee;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "IDKITCHEN", nullable = false)
    private Kitchen kitchen;

    @Embedded
    private Address address;

    @Column(nullable = false)
    private Boolean active;

    @Column(nullable = false)
    private Boolean open;

    @CreationTimestamp
    @Column(name = "CREATEDAT", nullable = false, columnDefinition = "TIMESTAMP")
    private OffsetDateTime createdAt;

    @UpdateTimestamp
    @Column(name = "UPDATEDAT", nullable = false, columnDefinition = "TIMESTAMP")
    private OffsetDateTime updatedAt;

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(name = "RESTAURANTPAYMENT", joinColumns = @JoinColumn(name = "IDRESTAURANT"), inverseJoinColumns = @JoinColumn(name = "IDPAYMENTMETHOD"))
    private Set<PaymentMethod> paymentMethods;

    @OneToMany(mappedBy = "restaurant")
    private Set<Product> products;

    @ManyToMany
    @JoinTable(name = "RESTAURANTSYSTEMUSER", joinColumns = @JoinColumn(name = "IDRESTAURANT"), inverseJoinColumns = @JoinColumn(name = "IDSYSTEMUSER"))
    private Set<User> users;

    @PrePersist
    private void prePersist() {

        if (!ofNullable(this.active).isPresent())
            this.active = Boolean.TRUE;

        if (!ofNullable(this.open).isPresent())
            this.open = Boolean.TRUE;

    }

    public Restaurant activate() {
        setActive(Boolean.TRUE);
        return this;
    }

    public Restaurant inactivate() {
        setActive(Boolean.FALSE);
        return this;
    }

    public Boolean canActivate() {
        return !this.active;
    }

    public Boolean canInactivate() {
        return this.active;
    }

    public Restaurant opening() {
        setOpen(Boolean.TRUE);
        return this;
    }

    public Restaurant closure() {
        setOpen(Boolean.FALSE);
        return this;
    }

    public Boolean canOpen() {
        return this.active && !this.open;
    }

    public Boolean canClose() {
        return this.open;
    }

    public Restaurant addOrRemovePaymentMethod(Boolean isAdd, PaymentMethod paymentMethod) {
        if (Boolean.TRUE.equals(isAdd))
            getPaymentMethods().add(paymentMethod);
        else
            getPaymentMethods().remove(paymentMethod);

        return this;
    }

    public Restaurant addOrRemoveProduct(Boolean isAdd, Product product) {
        if (Boolean.TRUE.equals(isAdd))
            getProducts().add(product);
        else
            getProducts().remove(product);

        return this;
    }

    public Restaurant addOrRemoveUser(Boolean isAdd, User user) {
        if (Boolean.TRUE.equals(isAdd))
            getUsers().add(user);
        else
            getUsers().remove(user);

        return this;
    }

    public Boolean acceptPaymentMethod(PaymentMethod paymentMethod) {
        return getPaymentMethods().contains(paymentMethod);
    }

}
