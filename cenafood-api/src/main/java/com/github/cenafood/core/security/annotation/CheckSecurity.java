package com.github.cenafood.core.security.annotation;

import static java.lang.annotation.ElementType.ANNOTATION_TYPE;
import static java.lang.annotation.ElementType.METHOD;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import org.springframework.security.access.prepost.PostAuthorize;
import org.springframework.security.access.prepost.PreAuthorize;

/**
 * @author elielcena
 *
 */
@Retention(RUNTIME)
@Target(METHOD)
public @interface CheckSecurity {

    @PreAuthorize("@securityUtil.noPreAuthorizeWrite()")
    @Retention(RUNTIME)
    @Target(value = {METHOD, ANNOTATION_TYPE})
    public @interface NoPreAuthorizeWrite {
    }

    @PreAuthorize("@securityUtil.noPreAuthorizeRead()")
    @Retention(RUNTIME)
    @Target(value = {METHOD, ANNOTATION_TYPE})
    public @interface NoPreAuthorizeRead {
    }

    public @interface Kitchens {

        @PreAuthorize("hasAuthority('SCOPE_WRITE') and hasAuthority('EDIT_KITCHENS')")
        @Retention(RUNTIME)
        @Target(METHOD)
        public @interface Edit {
        }

    }

    public @interface Restaurants {

        @PreAuthorize("@securityUtil.manageRestaurantRegistration()")
        @Retention(RUNTIME)
        @Target(METHOD)
        public @interface Edit {
        }

        @PreAuthorize("@securityUtil.manageRestaurantOperation(#id)")
        @Retention(RUNTIME)
        @Target(METHOD)
        public @interface Manage {
        }

    }

    public @interface Orders {

        @CheckSecurity.NoPreAuthorizeRead
        @PostAuthorize("hasAuthority('CONSULT_ORDERS') "
                + "or @securityUtil.userSameAsAuthenticated(returnObject.customer.id) "
                + "or @securityUtil.manageRestaurant(returnObject.restaurant.id)")
        @Retention(RUNTIME)
        @Target(METHOD)
        public @interface Find {
        }

        @PreAuthorize("@securityUtil.consultOrders(#filter.idCustomer, #filter.idRestaurant)")
        @Retention(RUNTIME)
        @Target(METHOD)
        public @interface Consult {
        }

        @PreAuthorize("@securityUtil.manageOrder(#code)")
        @Retention(RUNTIME)
        @Target(METHOD)
        public @interface Manage {
        }

    }

    public @interface PaymentMethod {

        @PreAuthorize("hasAuthority('SCOPE_WRITE') and hasAuthority('EDIT_PAYMENT_METHODS')")
        @Retention(RUNTIME)
        @Target(METHOD)
        public @interface Edit {
        }

    }

    public @interface UsersRolesPermission {

        @PreAuthorize("hasAuthority('SCOPE_READ') and hasAuthority('CONSULT_USERS_ROLES_PERMISSIONS')")
        @Retention(RUNTIME)
        @Target(METHOD)
        public @interface Consult {
        }

        @PreAuthorize("hasAuthority('SCOPE_WRITE') and hasAuthority('EDIT_USERS_ROLES_PERMISSIONS')")
        @Retention(RUNTIME)
        @Target(METHOD)
        public @interface Edit {
        }

        @PreAuthorize("hasAuthority('SCOPE_WRITE') and @securityUtil.userSameAsAuthenticated(#id)")
        @Retention(RUNTIME)
        @Target(METHOD)
        public @interface ChangeOwnPassword {
        }

        @PreAuthorize("hasAuthority('SCOPE_WRITE') and (hasAuthority('EDIT_USERS_ROLES_PERMISSIONS') or @securityUtil.userSameAsAuthenticated(#id))")
        @Retention(RUNTIME)
        @Target(METHOD)
        public @interface EditUser {
        }

    }

    public @interface Statistic {

        @PreAuthorize("@securityUti.consultStatistic()")
        @Retention(RUNTIME)
        @Target(METHOD)
        public @interface Consult {

        }

    }

}
