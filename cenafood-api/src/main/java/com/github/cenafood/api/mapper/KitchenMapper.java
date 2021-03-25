package com.github.cenafood.api.mapper;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.github.cenafood.api.model.request.KitchenRequestDTO;
import com.github.cenafood.api.model.response.KitchenResponseDTO;
import com.github.cenafood.domain.model.Kitchen;

/**
 * @author elielcena
 *
 */
@Component
public class KitchenMapper {

	@Autowired
	private ModelMapper modelMapper;

	public KitchenResponseDTO toDTO(Kitchen object) {
		return modelMapper.map(object, KitchenResponseDTO.class);
	}

	public List<KitchenResponseDTO> toCollectionDTO(Collection<Kitchen> objectList) {
		return objectList.stream().map(object -> toDTO(object)).collect(Collectors.toList());
	}

	public Kitchen toDomainEntity(KitchenRequestDTO objectRequest) {
		return modelMapper.map(objectRequest, Kitchen.class);
	}

	public void copyToDomainEntity(KitchenRequestDTO object, Kitchen objectRequest) {
		modelMapper.map(object, objectRequest);
	}

}
