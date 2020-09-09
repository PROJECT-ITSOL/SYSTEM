package com.example.sell.model.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class CustomerDTO {
    private String idCustomer;
    private String name;
    private String passwordHash;
    private String phoneNumber;
    private String address;
    private String email;
    private boolean status;
    private int amountBoom;
}
