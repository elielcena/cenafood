package com.github.cenafood.api.v1.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.CollectionModel;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.github.cenafood.api.v1.mapper.PaymentMethodMapper;
import com.github.cenafood.api.v1.model.request.PaymentMethodRequestDTO;
import com.github.cenafood.api.v1.model.response.PaymentMethodResponseDTO;
import com.github.cenafood.api.v1.openapi.controller.PaymentMethodControllerOpenApi;
import com.github.cenafood.core.security.anotation.CheckSecurity;
import com.github.cenafood.domain.model.PaymentMethod;
import com.github.cenafood.domain.service.PaymentMethodService;

/**
 * @author elielcena
 *
 */
@RestController
@RequestMapping(value = "/v1/payment-methods", produces = MediaType.APPLICATION_JSON_VALUE)
public class PaymentMethodController implements PaymentMethodControllerOpenApi {

    @Autowired
    private PaymentMethodService paymentMethodService;

    @Autowired
    private PaymentMethodMapper mapper;

    @CheckSecurity.PaymentMethod.Consult
    @GetMapping
    public CollectionModel<PaymentMethodResponseDTO> findAll() {
        return mapper.toCollectionModel(paymentMethodService.findAll());
    }

    @CheckSecurity.PaymentMethod.Consult
    @GetMapping("/{id}")
    public PaymentMethodResponseDTO findById(@PathVariable Long id) {
        return mapper.toModel(paymentMethodService.findById(id));
    }

    @CheckSecurity.PaymentMethod.Edit
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public PaymentMethodResponseDTO save(@RequestBody @Valid PaymentMethodRequestDTO paymentMethod) {
        return mapper.toModel(paymentMethodService.save(mapper.toDomainEntity(paymentMethod)));
    }

    @CheckSecurity.PaymentMethod.Edit
    @PutMapping("/{id}")
    public PaymentMethodResponseDTO update(@PathVariable Long id,
            @RequestBody @Valid PaymentMethodRequestDTO paymentMethodRequest) {
        PaymentMethod paymentMethod = paymentMethodService.findById(id);
        mapper.copyToDomainEntity(paymentMethodRequest, paymentMethod);
        return mapper.toModel(paymentMethodService.save(paymentMethod));
    }

    @CheckSecurity.PaymentMethod.Edit
    @DeleteMapping("/{id}")
    @ResponseStatus(code = HttpStatus.NO_CONTENT)
    public void delete(@PathVariable Long id) {
        paymentMethodService.delete(id);
    }
}
