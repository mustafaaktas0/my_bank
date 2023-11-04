package com.example.demo.controller;

import com.example.demo.dto.request.CreateCustomerDto;
import com.example.demo.dto.request.UpdateCustomerDto;
import com.example.demo.dto.response.CustomerResponseDto;
import com.example.demo.service.CustomerService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("v1/api/customer")
public class CustomerController {
    private final CustomerService customerService;

    public CustomerController(CustomerService customerService) {
        this.customerService = customerService;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<CustomerResponseDto> createCustomer(@RequestBody CreateCustomerDto createCustomerDto) {
        return ResponseEntity.ok(customerService.createCustomer(createCustomerDto));
    }

    @PutMapping(value = "/update/{identityNumber}")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<CustomerResponseDto> updateCustomer(@PathVariable("identityNumber") String identityNumber,
                                                              @RequestBody UpdateCustomerDto updateCustomerDto) {
        return ResponseEntity.ok(customerService.updateCustomer(identityNumber, updateCustomerDto));
    }

    @DeleteMapping(value = "delete/{identityNumber}")
    @ResponseStatus(HttpStatus.OK)
    public void deleteCustomer(@PathVariable("identityNumber") String identityNumber,
                               @RequestBody CreateCustomerDto createCustomerDto) {
        customerService.deleteCustomer(identityNumber);
    }
}
