package com.fh.scm.pojo;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

import javax.persistence.*;
import java.io.Serializable;
import java.math.BigDecimal;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = true)
@Entity
@Table(name = "order_details")
public class OrderDetails extends BaseEntity implements Serializable {

    @Builder.Default
    @Column(nullable = false, columnDefinition = "float default 0")
    private Float quantity = 0.0f;

    @Builder.Default
    @Column(name = "unit_price", nullable = false, precision = 11, scale = 2, columnDefinition = "decimal default 0.0")
    private BigDecimal unitPrice = BigDecimal.ZERO;

    @JsonIgnore
    @ManyToOne(optional = false)
    @JoinColumn(name = "order_id", referencedColumnName = "id", nullable = false)
    private Order order;

    @ManyToOne(optional = false)
    @JoinColumn(name = "product_id", referencedColumnName = "id", nullable = false)
    private Product product;
}