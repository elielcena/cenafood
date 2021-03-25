package com.github.cenafood.api.mapper;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.github.cenafood.api.model.request.PaymentMethodRequestDTO;
import com.github.cenafood.api.model.response.PaymentMethodResponseDTO;
import com.github.cenafood.domain.model.PaymentMethod;

/**
 * @author elielcena
 *
 */
@Component
public class PaymentMethodMapper {

	@Autowired
	private ModelMapper modelMapper;

	public PaymentMethodResponseDTO toDTO(PaymentMethod paymentMethod) {
		return modelMapper.map(paymentMethod, PaymentMethodResponseDTO.class);
	}

	public List<PaymentMethodResponseDTO> toCollectionDTO(Collection<PaymentMethod> paymentMethod) {
		return paymentMethod.stream().map(rest -> toDTO(rest)).collect(Collectors.toList());
	}

	public PaymentMethod toDomainEntity(PaymentMethodRequestDTO paymentMethod) {
		return modelMapper.map(paymentMethod, PaymentMethod.class);
	}

	public void copyToDomainEntity(PaymentMethodRequestDTO paymentMethodRequest, PaymentMethod paymentMethod) {
		modelMapper.map(paymentMethodRequest, paymentMethod);
	}
}
