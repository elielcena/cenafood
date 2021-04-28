package com.github.cenafood.domain.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

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
@ApiModel("StateResponse")
@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "STATE")
public class State implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(example = "SP")
    @Id
    @Column(nullable = false, length = 2)
    private String uf;

    @ApiModelProperty(example = "São Paulo")
    @Column(nullable = false)
    private String name;

}
