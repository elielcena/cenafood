package com.github.cenafood.core.openapi;

import java.io.File;
import java.io.InputStream;
import java.net.URI;
import java.net.URL;
import java.net.URLStreamHandler;
import java.util.Arrays;
import java.util.List;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.core.io.Resource;
import org.springframework.data.domain.Pageable;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.Links;
import org.springframework.hateoas.PagedModel;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.context.request.ServletWebRequest;

import com.fasterxml.classmate.TypeResolver;
import com.github.cenafood.api.exceptionhandler.ErrorResponseDTO;
import com.github.cenafood.api.v1.model.response.KitchenResponseDTO;
import com.github.cenafood.api.v1.model.response.OrderAbstractResponseDTO;
import com.github.cenafood.api.v1.model.response.PaymentMethodResponseDTO;
import com.github.cenafood.api.v1.model.response.PermissionResponseDTO;
import com.github.cenafood.api.v1.model.response.ProductResponseDTO;
import com.github.cenafood.api.v1.model.response.RestaurantResponseDTO;
import com.github.cenafood.api.v1.model.response.RoleResponseDTO;
import com.github.cenafood.api.v1.model.response.UserResponseDTO;
import com.github.cenafood.api.v1.openapi.model.CityResponseOpenApi;
import com.github.cenafood.api.v1.openapi.model.KitchenResponseOpenApi;
import com.github.cenafood.api.v1.openapi.model.LinksResponseOpenApi;
import com.github.cenafood.api.v1.openapi.model.OrderAbstractResponseOpenApi;
import com.github.cenafood.api.v1.openapi.model.PageableOpenApi;
import com.github.cenafood.api.v1.openapi.model.PaymentMethodResponseOpenApi;
import com.github.cenafood.api.v1.openapi.model.PermissionResponseOpenApi;
import com.github.cenafood.api.v1.openapi.model.ProductResponseOpenApi;
import com.github.cenafood.api.v1.openapi.model.RestaurantResponseOpenApi;
import com.github.cenafood.api.v1.openapi.model.RoleResponseOpenApi;
import com.github.cenafood.api.v1.openapi.model.StateResponseOpenApi;
import com.github.cenafood.api.v1.openapi.model.UserResponseOpenApi;
import com.github.cenafood.domain.model.City;
import com.github.cenafood.domain.model.State;

import springfox.bean.validators.configuration.BeanValidatorPluginsConfiguration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.OAuthBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RepresentationBuilder;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.builders.ResponseBuilder;
import springfox.documentation.schema.AlternateTypeRules;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.AuthorizationScope;
import springfox.documentation.service.Contact;
import springfox.documentation.service.GrantType;
import springfox.documentation.service.ResourceOwnerPasswordCredentialsGrant;
import springfox.documentation.service.Response;
import springfox.documentation.service.SecurityReference;
import springfox.documentation.service.SecurityScheme;
import springfox.documentation.service.Tag;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spi.service.contexts.SecurityContext;
import springfox.documentation.spring.web.plugins.Docket;

/**
 * @author elielcena
 *
 */
@Configuration
@Import(BeanValidatorPluginsConfiguration.class)
public class SpringFoxConfig {

    @Bean
    public Docket apiDocketV1() {
        var typeResolver = new TypeResolver();
        return new Docket(DocumentationType.SWAGGER_2)
                .groupName("V1")
                .select()
                .apis(RequestHandlerSelectors.basePackage("com.github.cenafood.api"))
                .paths(PathSelectors.ant("/v1/**"))
                .build()
                .apiInfo(apiInfoV1())
                .securitySchemes(Arrays.asList(securityScheme()))
                .securityContexts(Arrays.asList(securityContext()))
                .useDefaultResponseMessages(Boolean.FALSE)
                .globalResponses(HttpMethod.GET, responsesGet())
                .globalResponses(HttpMethod.POST, responsesPostPut())
                .globalResponses(HttpMethod.PUT, responsesPostPut())
                .globalResponses(HttpMethod.DELETE, responsesDelete())
                .ignoredParameterTypes(ServletWebRequest.class,
                        URL.class, URI.class, URLStreamHandler.class, Resource.class,
                        File.class, InputStream.class)
                .additionalModels(typeResolver.resolve(ErrorResponseDTO.class))
                .directModelSubstitute(Pageable.class, PageableOpenApi.class)
                .directModelSubstitute(Links.class, LinksResponseOpenApi.class)
                .alternateTypeRules(
                        AlternateTypeRules.newRule(typeResolver.resolve(PagedModel.class, OrderAbstractResponseDTO.class),
                                OrderAbstractResponseOpenApi.class))
                .alternateTypeRules(
                        AlternateTypeRules.newRule(typeResolver.resolve(CollectionModel.class, City.class), CityResponseOpenApi.class))
                .alternateTypeRules(
                        AlternateTypeRules.newRule(typeResolver.resolve(CollectionModel.class, State.class), StateResponseOpenApi.class))
                .alternateTypeRules(
                        AlternateTypeRules.newRule(typeResolver.resolve(CollectionModel.class, PaymentMethodResponseDTO.class),
                                PaymentMethodResponseOpenApi.class))
                .alternateTypeRules(
                        AlternateTypeRules.newRule(typeResolver.resolve(CollectionModel.class, RoleResponseDTO.class), RoleResponseOpenApi.class))
                .alternateTypeRules(
                        AlternateTypeRules.newRule(typeResolver.resolve(CollectionModel.class, PermissionResponseDTO.class),
                                PermissionResponseOpenApi.class))
                .alternateTypeRules(
                        AlternateTypeRules.newRule(typeResolver.resolve(CollectionModel.class, ProductResponseDTO.class),
                                ProductResponseOpenApi.class))
                .alternateTypeRules(
                        AlternateTypeRules.newRule(typeResolver.resolve(CollectionModel.class, RestaurantResponseDTO.class),
                                RestaurantResponseOpenApi.class))
                .alternateTypeRules(
                        AlternateTypeRules.newRule(typeResolver.resolve(CollectionModel.class, UserResponseDTO.class),
                                UserResponseOpenApi.class))
                .alternateTypeRules(
                        AlternateTypeRules.newRule(typeResolver.resolve(CollectionModel.class, KitchenResponseDTO.class),
                                KitchenResponseOpenApi.class))
                .tags(new Tag("Root", "Root Entry Point"),
                        new Tag("Cities", "Manage the cities"),
                        new Tag("Roles", "Manage the roles"),
                        new Tag("Kitchens", "Manage the kitchens"),
                        new Tag("Payment Methods", "Manage the payment methods"),
                        new Tag("Orders", "Manage the orders"),
                        new Tag("Restaurants", "Manage the restaurants"),
                        new Tag("States", "Manage the states"),
                        new Tag("Products", "Manage the products"),
                        new Tag("Users", "Manage the users"),
                        new Tag("Statistics", "Manage the statistics"));
    }

    private SecurityScheme securityScheme() {
        return new OAuthBuilder()
                .name("OAuth")
                .grantTypes(grantTypes())
                .scopes(scopes())
                .build();
    }

    private SecurityContext securityContext() {
        var securityReference = SecurityReference.builder()
                .reference("OAuth")
                .scopes(scopes().toArray(new AuthorizationScope[0]))
                .build();
        return SecurityContext.builder()
                .securityReferences(Arrays.asList(securityReference))
                .operationSelector(operationContext -> true)
                .build();
    }

    private List<GrantType> grantTypes() {
        return Arrays.asList(new ResourceOwnerPasswordCredentialsGrant("/oauth/token"));
    }

    private List<AuthorizationScope> scopes() {
        return Arrays.asList(new AuthorizationScope("READ", "Read access"), new AuthorizationScope("WRITE", "Write access"));
    }

    private ApiInfo apiInfoV1() {
        return new ApiInfoBuilder()
                .title("CenaFood API")
                .description("API open to customers and restaurants")
                .version("1")
                .contact(new Contact("Eliel Cena", "https://github.com/elielcena", "elielsenna1999@gmail.com"))
                .build();
    }

    private List<Response> responsesGet() {
        return Arrays.asList(internalServerErrorResponse(), notAcceptableResponse());
    }

    private List<Response> responsesPostPut() {
        return Arrays.asList(badRequestResponse(), internalServerErrorResponse(), notAcceptableResponse(), unsupportedMediaTypeResponse());
    }

    private List<Response> responsesDelete() {
        return Arrays.asList(badRequestResponse(), internalServerErrorResponse());

    }

    private Response badRequestResponse() {
        return new ResponseBuilder()
                .code(String.valueOf(HttpStatus.BAD_REQUEST.value()))
                .description(HttpStatus.BAD_REQUEST.getReasonPhrase())
                .representation(MediaType.ALL)
                .apply(SpringFoxConfig::model)
                .build();
    }

    private Response internalServerErrorResponse() {
        return new ResponseBuilder()
                .code(String.valueOf(HttpStatus.INTERNAL_SERVER_ERROR.value()))
                .description(HttpStatus.INTERNAL_SERVER_ERROR.getReasonPhrase())
                .representation(MediaType.ALL)
                .apply(SpringFoxConfig::model)
                .build();
    }

    private Response notAcceptableResponse() {
        return new ResponseBuilder()
                .code(String.valueOf(HttpStatus.NOT_ACCEPTABLE.value()))
                .description(HttpStatus.NOT_ACCEPTABLE.getReasonPhrase())
                .build();
    }

    private Response unsupportedMediaTypeResponse() {
        return new ResponseBuilder()
                .code(String.valueOf(HttpStatus.UNSUPPORTED_MEDIA_TYPE.value()))
                .description(HttpStatus.UNSUPPORTED_MEDIA_TYPE.getReasonPhrase())
                .build();
    }

    private static RepresentationBuilder model(RepresentationBuilder representationBuilder) {
        return representationBuilder.model(
                m -> m.referenceModel(ref -> ref.key(k -> k.qualifiedModelName(q -> q.namespace("com.github.cenafood.api.exceptionhandler")
                        .name("ErrorResponse")))));
    }

}
