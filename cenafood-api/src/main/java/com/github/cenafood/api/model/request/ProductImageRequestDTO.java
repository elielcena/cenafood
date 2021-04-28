package com.github.cenafood.api.model.request;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import org.springframework.http.MediaType;
import org.springframework.web.multipart.MultipartFile;

import com.github.cenafood.core.validation.FileContentType;
import com.github.cenafood.core.validation.FileSize;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author elielcena
 *
 */
@ApiModel("ProductImageRequest")
@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ProductImageRequestDTO {

    @ApiModelProperty(
            value = "Product image file (maximum 500KB, JPG and PNG only) <br/> <br/> Uploading the file through the documentation is not available, however, through any other client you will be successful",
            required = true)
    @FileSize(max = "5MB")
    @FileContentType(allowed = {MediaType.IMAGE_JPEG_VALUE, MediaType.IMAGE_PNG_VALUE})
    @NotNull
    private MultipartFile file;

    @ApiModelProperty(example = "COCA-COLA 2L")
    @NotBlank
    private String description;

}
