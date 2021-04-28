package com.github.cenafood.api.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.github.cenafood.api.mapper.PaymentMethodMapper;
import com.github.cenafood.api.model.response.PaymentMethodResponseDTO;
import com.github.cenafood.api.openapi.controller.RestaurantPaymentControllerOpenApi;
import com.github.cenafood.domain.service.RestaurantService;

/**
 * @author elielcena
 *
 */
@RestController
@RequestMapping(value = "/restaurants/{id}/payment-methods", produces = MediaType.APPLICATION_JSON_VALUE)
public class RestaurantPaymentController implements RestaurantPaymentControllerOpenApi {

    @Autowired
    private RestaurantService restaurantService;

    @Autowired
    private PaymentMethodMapper paymentMapper;

    @GetMapping
    public List<PaymentMethodResponseDTO> find(@PathVariable Long id) {
        return paymentMapper.toCollectionDTO(restaurantService.findById(id).getPaymentMethods());
    }

    @PutMapping("/{idPaymentMethod}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void addPaymentMethodToRestaurant(@PathVariable Long id, @PathVariable Long idPaymentMethod) {
        restaurantService.addPaymentMethodToRestaurant(id, idPaymentMethod);
    }

    @DeleteMapping("/{idPaymentMethod}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void removePaymentMethodToRestaurant(@PathVariable Long id, @PathVariable Long idPaymentMethod) {
        restaurantService.removePaymentMethodToRestaurant(id, idPaymentMethod);
    }

}
