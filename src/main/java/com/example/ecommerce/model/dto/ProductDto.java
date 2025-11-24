
package com.example.ecommerce.model.dto;

import lombok.*;

        import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ProductDto {
    private Long id;
    private String name;
    private String description;
    private double amount;
    private int remainCount;
    private Double starRating;
    private String comment;
    private List<String> productDetails;
    private Long categoryId;
    private Long brandId;
}

