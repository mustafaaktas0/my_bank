package com.example.demo.dto.request;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class UpdateCustomerDto {
    private String identityNumber;
    private String firstName;
    private String lastName;
    private String phoneNumber;
    private String email;
}
