package com.fh.scm.pojo;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

import javax.persistence.*;
import java.io.Serializable;
import java.math.BigDecimal;

@Setter
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "cart_details", uniqueConstraints = {
        @UniqueConstraint(columnNames = {"cart_id", "product_id"})
})
public class CartDetails extends _BaseEntity implements Serializable {

    @Builder.Default
    @Column(nullable = false, columnDefinition = "float default 0")
    private Float quantity = 0.0f;

    @Builder.Default
    @Column(name = "unit_price", nullable = false, precision = 11, scale = 2, columnDefinition = "decimal default 0.0")
    private BigDecimal unitPrice = BigDecimal.ZERO;

    @JsonIgnore
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "cart_id", referencedColumnName = "id", nullable = false)
    private Cart cart;

    @ManyToOne
    @JoinColumn(name = "product_id", referencedColumnName = "id")
    private Product product;
}