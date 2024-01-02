package com.corebanking.system.service;


import com.corebanking.system.model.dto.CreateOTPDto;
import com.corebanking.system.model.dto.CustomerDto;
import com.corebanking.system.model.dto.LoginDto;
import com.corebanking.system.model.entity.Customer;

import java.util.List;

public interface CustomerService
{
    String addUser(Customer customer);

    Customer createOTP(CreateOTPDto createOTPDto);

    //Customer register(Customer customer);

    void deleteCustomer(Long id);

    CustomerDto updateCustomer(CustomerDto customer);

    List<Customer> getAllCustomers();
    Customer getCustomerWithEmail(String email);
    Customer getCustomerWithCnic(String cnic);
    String login(LoginDto loginDto);
}
