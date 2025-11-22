package com.example.ecommerce.model.entity;
import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Entity
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;
    private String name;
    private String description;
    private double amount;
    private int remainCount;
    private double starRating = 0;
    private String comment;
    @ElementCollection
    private List<String> productDetails;
    private boolean status = true;

    @ManyToOne
    private Category category;

    @ManyToOne
    private Brand brand;
}
