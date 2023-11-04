package com.example.demo.dto.response;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class CustomerResponseDto {
    private String identityNumber;
    private String firstName;
    private String lastName;
    private String phoneNumber;
    private String email;
}
