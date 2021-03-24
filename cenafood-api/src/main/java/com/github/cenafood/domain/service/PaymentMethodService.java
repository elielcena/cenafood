package com.github.cenafood.domain.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import com.github.cenafood.domain.exception.EntityInUseException;
import com.github.cenafood.domain.exception.ResourceNotFoundException;
import com.github.cenafood.domain.model.PaymentMethod;
import com.github.cenafood.domain.repository.PaymentMethodRepository;

/**
 * @author elielcena
 *
 */
@Service
public class PaymentMethodService {

	private static final String MSG_RESOURCE_NOT_FOUND = "There is no payment method registration with code %d";

	@Autowired
	private PaymentMethodRepository paymentMethodRepository;

	public List<PaymentMethod> findAll() {
		return paymentMethodRepository.findAll();
	}

	public PaymentMethod findById(Long id) {
		return paymentMethodRepository.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException(String.format(MSG_RESOURCE_NOT_FOUND, id)));
	}

	public PaymentMethod save(PaymentMethod paymentMethod) {
		return paymentMethodRepository.save(paymentMethod);
	}

	public void delete(Long id) {
		try {
			paymentMethodRepository.delete(findById(id));
		} catch (DataIntegrityViolationException e) {
			throw new EntityInUseException();
		}
	}
}
