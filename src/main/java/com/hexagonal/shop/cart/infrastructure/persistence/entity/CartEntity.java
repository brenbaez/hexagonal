package com.hexagonal.shop.cart.infrastructure.persistence.entity;

import com.hexagonal.shop.shared.infraestructure.product.persistence.ProductQuantityEntity;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import java.io.Serializable;
import java.util.List;

@Entity
@Table(name = "cart")
@Getter
@Setter
@NoArgsConstructor
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@ToString(onlyExplicitlyIncluded = true)
public class CartEntity implements Serializable {

    @Id
    @EqualsAndHashCode.Include
    private String id;

    private Boolean closed;

    @OneToMany(fetch = FetchType.LAZY)
    private List<ProductQuantityEntity> products;

    public CartEntity(String value) {
        this.id = value;
        this.closed = false;
    }
}
