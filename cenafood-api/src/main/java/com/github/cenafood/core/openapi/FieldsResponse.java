package com.github.cenafood.core.openapi;

import static java.lang.annotation.ElementType.ANNOTATION_TYPE;
import static java.lang.annotation.ElementType.CONSTRUCTOR;
import static java.lang.annotation.ElementType.FIELD;
import static java.lang.annotation.ElementType.METHOD;
import static java.lang.annotation.ElementType.PARAMETER;
import static java.lang.annotation.ElementType.TYPE_USE;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;

/**
 * @author elielcena
 *
 */
@ApiImplicitParams({
    @ApiImplicitParam(
            name = "fields",
            value = "Properties name to filter the response, separated by commas",
            required = false,
            dataTypeClass = String.class,
            paramType = "query",
            example = "id,name")
})
@Retention(RUNTIME)
@Target({FIELD, METHOD, PARAMETER, CONSTRUCTOR, ANNOTATION_TYPE, TYPE_USE})
public @interface FieldsResponse {

}
