package com.github.cenafood.domain.model;

import static org.springframework.util.ObjectUtils.isEmpty;

import java.time.OffsetDateTime;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

import org.hibernate.annotations.CreationTimestamp;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author elielcena
 *
 */
@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "SYSTEMUSER")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false, unique = true)
    private String email;

    @Column(nullable = false)
    private String password;

    @CreationTimestamp
    @Column(name = "CREATEDAT", nullable = false, columnDefinition = "TIMESTAMP")
    private OffsetDateTime createdAt;

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(name = "SYSTEMUSERROLE", joinColumns = @JoinColumn(name = "IDSYSTEMUSER"), inverseJoinColumns = @JoinColumn(name = "IDROLE"))
    private Set<Role> roles;

    public User addOrRemoveRole(Boolean isAdss, Role role) {
        if (Boolean.TRUE.equals(isAdss))
            getRoles().add(role);
        else
            getRoles().remove(role);

        return this;
    }

    public boolean isNew() {
        return isEmpty(getId());
    }

}
