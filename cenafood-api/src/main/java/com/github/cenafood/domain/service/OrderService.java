package com.github.cenafood.domain.service;

import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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

/**
 * @author elielcena
 *
 */
@Service
public class OrderService {

	private static final String MSG_RESOURCE_NOT_FOUND = "There is no order registration with code %d";

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

	public List<Order> findAll(OrderFilter filter) {
		return orderRepository.findAll(OrderSpecs.withFilter(filter));
	}

	public Order findByCode(UUID code) {
		return orderRepository.findByCode(code)
				.orElseThrow(() -> new ResourceNotFoundException(String.format(MSG_RESOURCE_NOT_FOUND, code)));
	}

	public Order generate(Order order) {
		try {
			// TODO get authenticated user
			order.setCustomer(new User());
			order.getCustomer().setId(1L);

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

	public void confirm(UUID code) {
		Order order = findByCode(code);

		orderRepository.save(order.confirm());
	}

	public void delivery(UUID code) {
		Order order = findByCode(code);

		orderRepository.save(order.delivery());
	}

	public void cancel(UUID code) {
		Order order = findByCode(code);

		orderRepository.save(order.cancel());
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
			throw new BusinessException(String.format("Payment method '%s' is not accepted by this restaurant.",
					paymentMethod.getDescription()));
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
