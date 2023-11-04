package com.example.demo.service;

import com.example.demo.dto.request.CreateCustomerDto;
import com.example.demo.dto.request.UpdateCustomerDto;
import com.example.demo.dto.response.CustomerResponseDto;
import com.example.demo.model.Customer;
import com.example.demo.repository.CustomerRepoitory;
import org.springframework.stereotype.Service;

import java.util.Objects;

@Service
public class CustomerService {
    private final CustomerRepoitory customerRepoitory;


    public CustomerService(CustomerRepoitory customerRepoitory) {
        this.customerRepoitory = customerRepoitory;
    }

    public CustomerResponseDto createCustomer(CreateCustomerDto createCustomerDto) {
        Customer getCustomer = getCustomerByIdentityNumber(createCustomerDto.getIdentityNumber());
        if (!Objects.isNull(getCustomer)) {
            return null;
        }
        Customer customer = Customer.builder()
                .identityNumber(createCustomerDto.getIdentityNumber())
                .firstName(createCustomerDto.getFirstName())
                .lastName(createCustomerDto.getLastName())
                .phoneNumber(createCustomerDto.getPhoneNumber())
                .email(createCustomerDto.getEmail())
                .build();
        customerRepoitory.save(customer);

        return CustomerResponseDto.builder()
                .firstName(customer.getFirstName())
                .lastName(customer.getLastName())
                .phoneNumber(customer.getPhoneNumber())
                .email(customer.getEmail())
                .build();
    }

    public CustomerResponseDto updateCustomer(String identityNumber, UpdateCustomerDto updateCustomerDto) {
        Customer getCustomer = getCustomerByIdentityNumber(identityNumber);
        if (Objects.isNull(getCustomer)) {
            return null;
        }
        getCustomer.setFirstName(updateCustomerDto.getFirstName());
        getCustomer.setLastName(updateCustomerDto.getLastName());
        getCustomer.setPhoneNumber(updateCustomerDto.getPhoneNumber());
        getCustomer.setEmail(updateCustomerDto.getEmail());

        customerRepoitory.save(getCustomer);
        return CustomerResponseDto.builder()
                .firstName(getCustomer.getFirstName())
                .lastName(getCustomer.getLastName())
                .phoneNumber(getCustomer.getPhoneNumber())
                .email(getCustomer.getEmail())
                .build();
    }

    public void deleteCustomer(String identityNumber) {
        Customer getCustomer = getCustomerByIdentityNumber(identityNumber);
        if (checkCreditCardDebtAndExistMoneyInAccount(getCustomer)) {
            customerRepoitory.delete(getCustomer);
        }
    }

    private Customer getCustomerByIdentityNumber(String identityNumber) {
        return customerRepoitory.findByIdentityNumber(identityNumber);
    }

    private boolean checkCreditCardDebtAndExistMoneyInAccount(Customer customer) {
        boolean checkMoneyInAccount = customer.getAccounts().stream().anyMatch(account -> account.getBalance() > 0);
        return checkMoneyInAccount;
    }
}
