package com.github.cenafood.core.security.anotation;

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

    @PreAuthorize("hasAuthority('SCOPE_WRITE') and isAuthenticated()")
    @Retention(RUNTIME)
    @Target(value = {METHOD, ANNOTATION_TYPE})
    public @interface NoPreAuthorizeWrite {
    }

    @PreAuthorize("hasAuthority('SCOPE_READ') and isAuthenticated()")
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

        @CheckSecurity.NoPreAuthorizeRead
        @Retention(RUNTIME)
        @Target(METHOD)
        public @interface Consult {
        }

    }

    public @interface Restaurants {

        @PreAuthorize("hasAuthority('SCOPE_WRITE') and hasAuthority('EDIT_RESTAURANTS')")
        @Retention(RUNTIME)
        @Target(METHOD)
        public @interface Edit {
        }

        @PreAuthorize("hasAuthority('SCOPE_WRITE') and (hasAuthority('EDIT_RESTAURANTS') or @securityUtil.manageRestaurant(#id))")
        @Retention(RUNTIME)
        @Target(METHOD)
        public @interface Manage {
        }

        @CheckSecurity.NoPreAuthorizeRead
        @Retention(RUNTIME)
        @Target(METHOD)
        public @interface Consult {
        }

    }

    public @interface Orders {

        @PreAuthorize("hasAuthority('SCOPE_READ') and isAuthenticated()")
        @PostAuthorize("hasAuthority('CONSULT_ORDERS') "
                + "or @securityUtil.getIdUser() == returnObject.customer.id "
                + "or @securityUtil.manageRestaurant(returnObject.restaurant.id)")
        @Retention(RUNTIME)
        @Target(METHOD)
        public @interface Find {
        }

        @PreAuthorize("hasAuthority('SCOPE_READ') and (hasAuthority('CONSULT_ORDERS') "
                + "or @securityUtil.getIdUser() == #filter.idCustomer "
                + "or @securityUtil.manageRestaurant(#filter.idRestaurant))")
        @Retention(RUNTIME)
        @Target(METHOD)
        public @interface Consult {
        }

        @CheckSecurity.NoPreAuthorizeWrite
        @Retention(RUNTIME)
        @Target(METHOD)
        public @interface Save {
        }

        @PreAuthorize("hasAuthority('SCOPE_WRITE') and (hasAuthority('MANAGE_ORDERS') "
                + "or @securityUtil.manageRestaurantOrder(#code))")
        @Retention(RUNTIME)
        @Target(METHOD)
        public @interface Manage {
        }

    }

    public @interface PaymentMethod {

        @CheckSecurity.NoPreAuthorizeRead
        @Retention(RUNTIME)
        @Target(METHOD)
        public @interface Consult {
        }

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

        @PreAuthorize("hasAuthority('SCOPE_WRITE') and @securityUtil.getIdUser() == #id")
        @Retention(RUNTIME)
        @Target(METHOD)
        public @interface ChangeOwnPassword {
        }

        @PreAuthorize("hasAuthority('SCOPE_WRITE') and (hasAuthority('EDIT_USERS_ROLES_PERMISSIONS') or @securityUtil.getIdUser() == #id)")
        @Retention(RUNTIME)
        @Target(METHOD)
        public @interface EditUser {
        }

    }

    public @interface Statistic {

        @PreAuthorize("hasAuthority('SCOPE_READ') and hasAuthority('GENERATE_REPORTS')")
        @Retention(RUNTIME)
        @Target(METHOD)
        public @interface Consult {

        }

    }

}
