package com.github.cenafood.api.v1.mapper;

import static org.apache.commons.lang3.BooleanUtils.isTrue;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;

import java.util.Optional;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.server.mvc.RepresentationModelAssemblerSupport;
import org.springframework.stereotype.Component;

import com.github.cenafood.api.v1.CenaLinks;
import com.github.cenafood.api.v1.controller.RestaurantController;
import com.github.cenafood.api.v1.model.request.RestaurantRequestDTO;
import com.github.cenafood.api.v1.model.response.RestaurantResponseDTO;
import com.github.cenafood.core.security.SecurityUtil;
import com.github.cenafood.domain.model.City;
import com.github.cenafood.domain.model.Kitchen;
import com.github.cenafood.domain.model.Restaurant;

/**
 * @author elielcena
 *
 */
@Component
public class RestaurantMapper extends RepresentationModelAssemblerSupport<Restaurant, RestaurantResponseDTO> {

    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    private CenaLinks cenaLinks;

    @Autowired
    private SecurityUtil securityUtil;

    public RestaurantMapper() {
        super(RestaurantController.class, RestaurantResponseDTO.class);
    }

    public RestaurantResponseDTO toModel(Restaurant restaurant) {
        RestaurantResponseDTO restaurantDTO = createModelWithId(restaurant.getId(), restaurant);
        modelMapper.map(restaurant, restaurantDTO);

        if (isTrue(securityUtil.manageRestaurantRegistration())) {
            restaurantDTO.add(cenaLinks.linkToRestaurantUser(restaurant.getId()));

            if (isTrue(restaurant.canActivate()))
                restaurantDTO.add(cenaLinks.linkToActivateRestaurant(restaurant.getId()));

            if (isTrue(restaurant.canInactivate()))
                restaurantDTO.add(cenaLinks.linkToInactivateRestaurant(restaurant.getId()));
        }

        if (isTrue(securityUtil.manageRestaurantOperation(restaurant.getId()))) {
            if (isTrue(restaurant.canOpen()))
                restaurantDTO.add(cenaLinks.linkToOpeningRestaurant(restaurant.getId()));

            if (isTrue(restaurant.canClose()))
                restaurantDTO.add(cenaLinks.linkToClosureRestaurant(restaurant.getId()));
        }

        if (isTrue(securityUtil.noPreAuthorizeRead())) {
            restaurantDTO.add(cenaLinks.linkToRestaurantPaymentMethod(restaurant.getId()));
            restaurantDTO.add(cenaLinks.linkToProducts(restaurant.getId()).withRel("products"));
            restaurantDTO.getKitchen().add(cenaLinks.linkToKitchen(restaurant.getKitchen().getId()));
            restaurantDTO.getAddress().getCity().removeLinks().add(cenaLinks.linkToCity(restaurantDTO.getAddress().getCity().getId()));
            restaurantDTO.getAddress().getCity().getState().removeLinks()
                    .add(cenaLinks.linkToState(restaurantDTO.getAddress().getCity().getState().getUf()));
            restaurantDTO.add(cenaLinks.linkToRestaurants());
        }

        return restaurantDTO;
    }

    @Override
    public CollectionModel<RestaurantResponseDTO> toCollectionModel(Iterable<? extends Restaurant> entities) {
        var collectionModel = super.toCollectionModel(entities);

        if (isTrue(securityUtil.noPreAuthorizeRead())) {
            collectionModel.add(linkTo(RestaurantController.class).withSelfRel());
        }

        return collectionModel;
    }

    public Restaurant toDomainEntity(RestaurantRequestDTO restaurant) {
        return modelMapper.map(restaurant, Restaurant.class);
    }

    public void copyToDomainEntity(RestaurantRequestDTO restaurantRequest, Restaurant restaurant) {
        /*
         * Do not remove "new", because to reference a new kitchen and city it is
         * necessary to set a new object. To avoid org.hibernate.HibernateException:
         * identifier of an instance of com.algaworks.algafood.domain.model.Cozinha was
         * altered from {} {}
         */
        restaurant.setKitchen(new Kitchen());

        if (Optional.ofNullable(restaurant.getAddress()).isPresent())
            restaurant.getAddress().setCity(new City());

        modelMapper.map(restaurantRequest, restaurant);
    }

}
