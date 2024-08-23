package com.fh.scms.dto.product;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fh.scms.dto.category.CategoryResponse;
import com.fh.scms.dto.tag.TagResponse;
import com.fh.scms.dto.unit.UnitResponse;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Set;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ProductResponseWithTagUnit {

    private Long id;

    private String name;

    private String description;

    private BigDecimal price;

    private String image;

    @JsonFormat(pattern = "dd-MM-yyyy")
    private LocalDate expiryDate;

    private CategoryResponse category;

    private Set<TagResponse> tagSet;

    private Set<UnitResponse> unitSet;
}