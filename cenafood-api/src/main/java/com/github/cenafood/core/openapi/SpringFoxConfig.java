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
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.context.request.ServletWebRequest;

import com.fasterxml.classmate.TypeResolver;
import com.github.cenafood.api.exceptionhandler.ErrorResponseDTO;
import com.github.cenafood.api.model.response.OrderAbstractResponseDTO;
import com.github.cenafood.api.openapi.model.OrderAbstractResponseOpenApi;
import com.github.cenafood.api.openapi.model.PageableOpenApi;

import springfox.bean.validators.configuration.BeanValidatorPluginsConfiguration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.RepresentationBuilder;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.builders.ResponseBuilder;
import springfox.documentation.schema.AlternateTypeRules;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.service.Response;
import springfox.documentation.service.Tag;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;

/**
 * @author elielcena
 *
 */
@Configuration
@Import(BeanValidatorPluginsConfiguration.class)
public class SpringFoxConfig {

    @Bean
    public Docket apiDocket() {
        var typeResolver = new TypeResolver();
        return new Docket(DocumentationType.SWAGGER_2)
                .select()
                .apis(RequestHandlerSelectors.basePackage("com.github.cenafood.api"))
                .build()
                .apiInfo(apiInfo())
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
                .alternateTypeRules(
                        AlternateTypeRules.newRule(typeResolver.resolve(Page.class, OrderAbstractResponseDTO.class), OrderAbstractResponseOpenApi.class))
                .tags(new Tag("Cities", "Manage the cities"),
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

    private ApiInfo apiInfo() {
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
