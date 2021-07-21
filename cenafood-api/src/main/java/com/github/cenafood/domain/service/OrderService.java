package com.github.cenafood.domain.service;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.github.cenafood.core.data.PageableTranslater;
import com.github.cenafood.core.security.SecurityUtil;
import com.github.cenafood.domain.exception.BusinessException;
import com.github.cenafood.domain.exception.ResourceNotFoundException;
import com.github.cenafood.domain.filter.OrderFilter;
import com.github.cenafood.domain.model.City;
import com.github.cenafood.domain.model.Order;
import com.github.cenafood.domain.model.PaymentMethod;
import com.github.cenafood.domain.model.Product;
import com.github.cenafood.domain.model.Restaurant;
import com.github.cenafood.domain.model.User;
import com.github.cenafood.domain.repository.OrderRepository;
import com.github.cenafood.infrastructure.repository.spec.OrderSpecs;
import com.google.common.collect.ImmutableMap;

/**
 * @author elielcena
 *
 */
@Service
public class OrderService {

    private static final String MSG_RESOURCE_NOT_FOUND = "There is no order registration with code %s";

    private static final String MSG_PAYMENT_NOT_ACCEPT = "Payment method '%s' is not accepted by this restaurant.";

    @Autowired
    private OrderRepository orderRepository;

    @Autowired
    private RestaurantService restaurantService;

    @Autowired
    private CityService cityService;

    @Autowired
    private UserService userService;

    @Autowired
    private ProductService productService;

    @Autowired
    private PaymentMethodService paymentMethodService;

    @Autowired
    private SecurityUtil securityUtil;

    public Page<Order> findAllWithFilterAndPage(OrderFilter filter, Pageable pageable) {
        pageable = translatePageable(pageable);
        return orderRepository.findAll(OrderSpecs.withFilter(filter), pageable);
    }

    public Order findByCode(String code) {
        return orderRepository.findByCode(code)
                .orElseThrow(() -> new ResourceNotFoundException(String.format(MSG_RESOURCE_NOT_FOUND, code)));
    }

    public Order generate(Order order) {
        try {
            order.setCustomer(User.builder().id(securityUtil.getIdUser()).build());

            validOrder(order);
            validItems(order);

            order.setDeliveryFee(order.getRestaurant().getDeliveryFee());
            order.calculateValueTotal();

            return orderRepository.save(order);
        } catch (ResourceNotFoundException e) {
            throw new BusinessException(e.getMessage(), e);
        } catch (NullPointerException e) {
            e.printStackTrace();
            throw new BusinessException(e.getMessage(), e);
        }
    }

    @Transactional
    public void confirm(String code) {
        Order order = findByCode(code);

        orderRepository.save(order.confirm());
    }

    @Transactional
    public void delivery(String code) {
        Order order = findByCode(code);

        orderRepository.save(order.delivery());
    }

    @Transactional
    public void cancel(String code) {
        Order order = findByCode(code);

        orderRepository.save(order.cancel());
    }

    private Pageable translatePageable(Pageable pageable) {
        var mapper = ImmutableMap.of("customerName", "customer.name");
        return PageableTranslater.translate(pageable, mapper);
    }

    private void validOrder(Order order) {
        City city = cityService.findById(order.getAddress().getCity().getId());
        User customer = userService.findById(order.getCustomer().getId());
        Restaurant restaurant = restaurantService.findById(order.getRestaurant().getId());
        PaymentMethod paymentMethod = paymentMethodService.findById(order.getPaymentMethod().getId());

        order.getAddress().setCity(city);
        order.setCustomer(customer);
        order.setRestaurant(restaurant);
        order.setPaymentMethod(paymentMethod);

        if (Boolean.FALSE.equals(restaurant.acceptPaymentMethod(paymentMethod))) {
            throw new BusinessException(String.format(MSG_PAYMENT_NOT_ACCEPT, paymentMethod.getDescription()));
        }
    }

    private void validItems(Order order) {
        order.getOrderItems().forEach(item -> {
            Product product = productService.findById(order.getRestaurant().getId(), item.getProduct().getId());

            item.setOrder(order);
            item.setProduct(product);
            item.setUnitPrice(product.getPrice());
        });
    }

}
