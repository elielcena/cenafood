package com.github.cenafood.domain.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.springframework.hateoas.RepresentationModel;
import org.springframework.hateoas.server.core.Relation;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

/**
 * @author elielcena
 *
 */
@Relation(collectionRelation = "cities")
@ApiModel("CityResponse")
@Builder
@Data
@EqualsAndHashCode(callSuper = true)
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "CITY")
public class City extends RepresentationModel<City> implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(example = "1")
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column
    private Long id;

    @ApiModelProperty(example = "São Paulo")
    @Column(nullable = false)
    private String name;

    @ManyToOne
    @JoinColumn(name = "UFSTATE", nullable = false, referencedColumnName = "UF")
    private State state;

}
