package com.example.ecommerce.model.dto;

import lombok.*;

import java.time.LocalDate;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UserDto {
    private Long id;
    private String name;
    private String surname;
    private LocalDate birthdate;
    private String fin;
    private String account;
    private String email;
    private String password;
    private String address;
    private double accountBalance;
}
