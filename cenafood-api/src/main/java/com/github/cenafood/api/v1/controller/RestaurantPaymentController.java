package com.github.cenafood.api.v1.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.CollectionModel;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.github.cenafood.api.v1.CenaLinks;
import com.github.cenafood.api.v1.mapper.PaymentMethodMapper;
import com.github.cenafood.api.v1.model.response.PaymentMethodResponseDTO;
import com.github.cenafood.api.v1.openapi.controller.RestaurantPaymentControllerOpenApi;
import com.github.cenafood.core.security.annotation.CheckSecurity;
import com.github.cenafood.domain.service.RestaurantService;

/**
 * @author elielcena
 *
 */
@RestController
@RequestMapping(value = "/v1/restaurants/{id}/payment-methods", produces = MediaType.APPLICATION_JSON_VALUE)
public class RestaurantPaymentController implements RestaurantPaymentControllerOpenApi {

    @Autowired
    private RestaurantService restaurantService;

    @Autowired
    private PaymentMethodMapper paymentMapper;

    @Autowired
    private CenaLinks cenaLinks;

    @CheckSecurity.Restaurants.Consult
    @GetMapping
    public CollectionModel<PaymentMethodResponseDTO> find(@PathVariable Long id) {
        CollectionModel<PaymentMethodResponseDTO> paymentMethodResponseDTO =
                paymentMapper.toCollectionModel(restaurantService.findById(id).getPaymentMethods())
                        .removeLinks()
                        .add(cenaLinks.linkToRestaurantPaymentMethod(id).withSelfRel())
                        .add(cenaLinks.linkToRestaurantAddPaymentMethod(id));

        paymentMethodResponseDTO.getContent()
                .forEach(paymentMethod -> paymentMethod.add(cenaLinks.linkToRestaurantRemovePaymentMethod(id, paymentMethod.getId())));

        return paymentMethodResponseDTO;
    }

    @CheckSecurity.Restaurants.Manage
    @PutMapping("/{idPaymentMethod}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public ResponseEntity<Void> addPaymentMethodToRestaurant(@PathVariable Long id, @PathVariable Long idPaymentMethod) {
        restaurantService.addPaymentMethodToRestaurant(id, idPaymentMethod);

        return ResponseEntity.noContent().build();
    }

    @CheckSecurity.Restaurants.Manage
    @DeleteMapping("/{idPaymentMethod}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public ResponseEntity<Void> removePaymentMethodToRestaurant(@PathVariable Long id, @PathVariable Long idPaymentMethod) {
        restaurantService.removePaymentMethodToRestaurant(id, idPaymentMethod);

        return ResponseEntity.noContent().build();
    }

}
