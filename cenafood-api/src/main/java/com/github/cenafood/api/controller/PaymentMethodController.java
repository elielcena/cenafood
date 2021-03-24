package com.github.cenafood.api.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.github.cenafood.api.mapper.PaymentMethodMapper;
import com.github.cenafood.api.model.request.PaymentMethodRequestDTO;
import com.github.cenafood.api.model.response.PaymentMethodResponseDTO;
import com.github.cenafood.domain.model.PaymentMethod;
import com.github.cenafood.domain.service.PaymentMethodService;

/**
 * @author elielcena
 *
 */
@RestController
@RequestMapping(value = "/payment-methods")
public class PaymentMethodController {

	@Autowired
	private PaymentMethodService paymentMethodService;

	@Autowired
	private PaymentMethodMapper mapper;

	@GetMapping
	public List<PaymentMethodResponseDTO> findAll() {
		return mapper.toCollectionDTO(paymentMethodService.findAll());
	}

	@GetMapping("/{id}")
	public PaymentMethodResponseDTO findById(@PathVariable Long id) {
		return mapper.toDTO(paymentMethodService.findById(id));
	}

	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public PaymentMethodResponseDTO save(@RequestBody @Valid PaymentMethodRequestDTO paymentMethod) {
		return mapper.toDTO(paymentMethodService.save(mapper.toDomainEntity(paymentMethod)));
	}

	@PutMapping("/{id}")
	public PaymentMethodResponseDTO update(@PathVariable Long id,
			@RequestBody @Valid PaymentMethodRequestDTO paymentMethodRequest) {
		PaymentMethod paymentMethod = paymentMethodService.findById(id);
		mapper.copyToDomainEntity(paymentMethodRequest, paymentMethod);
		return mapper.toDTO(paymentMethodService.save(paymentMethod));
	}

	@DeleteMapping("/{id}")
	@ResponseStatus(code = HttpStatus.NO_CONTENT)
	public void delete(@PathVariable Long id) {
		paymentMethodService.delete(id);
	}
}
