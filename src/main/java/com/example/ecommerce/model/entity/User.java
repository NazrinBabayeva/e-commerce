package com.example.ecommerce.model.entity;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;

@Entity
@Table(name = "_user")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;
    private String name;
    private String surname;
    private LocalDate birthdate;
    private String fin;
    private String account;
    @Column(unique = true)
    private String email;
    private String password;
    private String address;
    private double accountBalance = 10000;
}
