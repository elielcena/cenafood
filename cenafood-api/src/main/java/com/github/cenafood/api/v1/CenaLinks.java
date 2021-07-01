package com.github.cenafood.api.v1;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

import org.springframework.hateoas.IanaLinkRelations;
import org.springframework.hateoas.Link;
import org.springframework.hateoas.TemplateVariable;
import org.springframework.hateoas.TemplateVariable.VariableType;
import org.springframework.hateoas.TemplateVariables;
import org.springframework.hateoas.UriTemplate;
import org.springframework.stereotype.Component;

import com.github.cenafood.api.v1.controller.CityController;
import com.github.cenafood.api.v1.controller.KitchenController;
import com.github.cenafood.api.v1.controller.OrderController;
import com.github.cenafood.api.v1.controller.PaymentMethodController;
import com.github.cenafood.api.v1.controller.RestaurantController;
import com.github.cenafood.api.v1.controller.RestaurantPaymentController;
import com.github.cenafood.api.v1.controller.RestaurantProductController;
import com.github.cenafood.api.v1.controller.RestaurantProductImageController;
import com.github.cenafood.api.v1.controller.RestaurantUserController;
import com.github.cenafood.api.v1.controller.RoleController;
import com.github.cenafood.api.v1.controller.RolePermissionController;
import com.github.cenafood.api.v1.controller.StateController;
import com.github.cenafood.api.v1.controller.StatisticController;
import com.github.cenafood.api.v1.controller.UserController;
import com.github.cenafood.api.v1.controller.UserRoleController;

/**
 * @author elielcena
 *
 */

@Component
public class CenaLinks {

    public static final TemplateVariables PAGE_VARIABLES = new TemplateVariables(
            new TemplateVariable("page", VariableType.REQUEST_PARAM),
            new TemplateVariable("size", VariableType.REQUEST_PARAM),
            new TemplateVariable("sort", VariableType.REQUEST_PARAM));

    public Link linkToOrders() {
        TemplateVariables filterVariables = new TemplateVariables(
                new TemplateVariable("idCustomer", VariableType.REQUEST_PARAM),
                new TemplateVariable("idRestaurant", VariableType.REQUEST_PARAM),
                new TemplateVariable("startDate", VariableType.REQUEST_PARAM),
                new TemplateVariable("endDate", VariableType.REQUEST_PARAM));

        String orderUrl = linkTo(OrderController.class).toUri().toString();

        return Link.of(UriTemplate.of(orderUrl).with(PAGE_VARIABLES).with(filterVariables), IanaLinkRelations.COLLECTION);
    }

    public Link linkToCorfirmOrder(String orderCode) {
        return linkTo(methodOn(OrderController.class).confirm(orderCode)).withRel("confirm");
    }

    public Link linkToDeliveryOrder(String orderCode) {
        return linkTo(methodOn(OrderController.class).delivery(orderCode)).withRel("delivery");
    }

    public Link linkToCancelOrder(String orderCode) {
        return linkTo(methodOn(OrderController.class).cancel(orderCode)).withRel("cancel");
    }

    public Link linkToRestaurants() {
        return linkTo(methodOn(RestaurantController.class).findAll()).withRel(IanaLinkRelations.COLLECTION);
    }

    public Link linkToRestaurant(Long idRestaurant) {
        return linkTo(methodOn(RestaurantController.class).findById(idRestaurant)).withSelfRel();
    }

    public Link linkToActivateRestaurant(Long idRestaurant) {
        return linkTo(methodOn(RestaurantController.class).activate(idRestaurant)).withRel("activate");
    }

    public Link linkToInactivateRestaurant(Long idRestaurant) {
        return linkTo(methodOn(RestaurantController.class).inactivate(idRestaurant)).withRel("inactivate");
    }

    public Link linkToOpeningRestaurant(Long idRestaurant) {
        return linkTo(methodOn(RestaurantController.class).opening(idRestaurant)).withRel("opening");
    }

    public Link linkToClosureRestaurant(Long idRestaurant) {
        return linkTo(methodOn(RestaurantController.class).closure(idRestaurant)).withRel("closure");
    }

    public Link linkToRestaurantUser(Long idRestaurant) {
        return linkTo(methodOn(RestaurantUserController.class).find(idRestaurant)).withRel("responsibles");
    }

    public Link linkToRestaurantAddUser(Long idRestaurant) {
        return linkTo(methodOn(RestaurantUserController.class).addUserToRestaurant(idRestaurant, null)).withRel("addUser");
    }

    public Link linkToRestauranRemovetUser(Long idRestaurant, Long idUser) {
        return linkTo(methodOn(RestaurantUserController.class).removeUserToRestaurant(idRestaurant, idUser)).withRel("removeUser");
    }

    public Link linkToRestaurantPaymentMethod(Long idRestaurant) {
        return linkTo(methodOn(RestaurantPaymentController.class).find(idRestaurant)).withRel("paymentMethods");
    }

    public Link linkToRestaurantAddPaymentMethod(Long idRestaurant) {
        return linkTo(methodOn(RestaurantPaymentController.class).addPaymentMethodToRestaurant(idRestaurant, null)).withRel("addPaymentMethod");
    }

    public Link linkToRestaurantRemovePaymentMethod(Long idRestaurant, Long idPaymentMethod) {
        return linkTo(methodOn(RestaurantPaymentController.class).removePaymentMethodToRestaurant(idRestaurant, idPaymentMethod))
                .withRel("removePaymentMethod");
    }

    public Link linkToCities() {
        return linkTo(methodOn(CityController.class).findAllWithFilter(null)).withRel(IanaLinkRelations.COLLECTION);
    }

    public Link linkToCity(Long idCity) {
        return linkTo(methodOn(CityController.class).findById(idCity)).withSelfRel();
    }

    public Link linkToStates() {
        return linkTo(methodOn(StateController.class).findAllWithFilter(null)).withRel(IanaLinkRelations.COLLECTION);
    }

    public Link linkToState(String uf) {
        return linkTo(methodOn(StateController.class).findByUf(uf)).withSelfRel();
    }

    public Link linkToUsers() {
        return linkTo(methodOn(UserController.class).findAll()).withRel(IanaLinkRelations.COLLECTION);
    }

    public Link linkToUser(Long idUser) {
        return linkTo(methodOn(UserController.class).findById(idUser)).withSelfRel();
    }

    public Link linkToRolesUser(Long idUser) {
        return linkTo(methodOn(UserRoleController.class).findRolesByUser(idUser)).withRel("roles-user");
    }

    public Link linkToAddRoleUser(Long idUser) {
        return linkTo(methodOn(UserRoleController.class).addRoleToUser(idUser, null)).withRel("addRole");
    }

    public Link linkToRemoveRoleUser(Long idUser, Long idRole) {
        return linkTo(methodOn(UserRoleController.class).removeRoleToUser(idUser, idRole)).withRel("removeRole");
    }

    public Link linkToRoles() {
        return linkTo(methodOn(RoleController.class).findAll()).withRel(IanaLinkRelations.COLLECTION);
    }

    public Link linkToKitchens() {
        return linkTo(methodOn(KitchenController.class).findAll()).withRel(IanaLinkRelations.COLLECTION);
    }

    public Link linkToKitchen(Long idkitchen) {
        return linkTo(methodOn(KitchenController.class).findById(idkitchen)).withSelfRel();
    }

    public Link linkToPaymentMethods() {
        return linkTo(methodOn(PaymentMethodController.class).findAll()).withRel(IanaLinkRelations.COLLECTION);
    }

    public Link linkToPermission(Long idRole) {
        return linkTo(methodOn(RolePermissionController.class).findAll(idRole)).withRel(IanaLinkRelations.COLLECTION);
    }

    public Link linkToAddPermission(Long idRole) {
        return linkTo(methodOn(RolePermissionController.class).addPermission(idRole, null)).withRel("addPermission");
    }

    public Link linkToRemovePermission(Long idRole, Long idPermission) {
        return linkTo(methodOn(RolePermissionController.class).removePermission(idRole, idPermission)).withRel("removePermission");
    }

    public Link linkToProducts(Long idRestaurant) {
        return linkTo(methodOn(RestaurantProductController.class).find(idRestaurant)).withRel(IanaLinkRelations.COLLECTION);
    }

    public Link linkToProduct(Long idRestaurant, Long idProduct) {
        return linkTo(methodOn(RestaurantProductController.class).findById(idRestaurant, idProduct)).withSelfRel();
    }

    public Link linkToProductImage(Long idRestaurant, Long idProduct, Long idImage) {
        return linkTo(methodOn(RestaurantProductImageController.class).findById(idRestaurant, idProduct, idImage)).withSelfRel();
    }

    public Link linkToStatistics() {
        return linkTo(StatisticController.class).withRel("statistics");
    }

    public Link linkToStatisticDailyOrder() {
        TemplateVariables filterVariables = new TemplateVariables(
                new TemplateVariable("idRestaurant", VariableType.REQUEST_PARAM),
                new TemplateVariable("startDate", VariableType.REQUEST_PARAM),
                new TemplateVariable("endDate", VariableType.REQUEST_PARAM));

        String orderUrl = linkTo(methodOn(StatisticController.class).findByDailyOrder(null)).toUri().toString();

        return Link.of(UriTemplate.of(orderUrl).with(PAGE_VARIABLES).with(filterVariables), IanaLinkRelations.COLLECTION);
    }

}
