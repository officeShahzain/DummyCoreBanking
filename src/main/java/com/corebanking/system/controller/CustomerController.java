package com.corebanking.system.controller;


import com.corebanking.system.model.dto.CreateOTPDto;
import com.corebanking.system.model.dto.CustomerDto;
import com.corebanking.system.model.dto.LoginDto;
import com.corebanking.system.model.entity.Customer;
import com.corebanking.system.service.CustomerService;
import com.corebanking.system.service.Impl.CustomerServiceImpl;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;

@RestController
@RequestMapping("/user")
public class CustomerController
{
    private static final Logger LOGGER = LoggerFactory.getLogger(CustomerController.class);

    @Autowired
    private CustomerService customerService;
    @PostMapping("/signup")
    public String register(@RequestBody Customer customer)
    {
        return this.customerService.addUser(customer);
    }

    @PostMapping("/createOTP")
    public Customer createOTP(@RequestBody CreateOTPDto createOTPDto)
    {
        return this.customerService.createOTP(createOTPDto);
    }
    @PostMapping("/login")
    public ResponseEntity<String> loginPage(@RequestBody LoginDto loginDto)
    {
         String response =  customerService.login(loginDto);
         return new ResponseEntity<>(response,HttpStatus.FOUND);
    }
    @PutMapping("/update")
    public ResponseEntity<CustomerDto> updateCustomer(@RequestBody CustomerDto customerDto){
        return new ResponseEntity<>(customerService.updateCustomer(customerDto),HttpStatus.OK);
    }
    @DeleteMapping("/delete")
    public ResponseEntity<String> deleteCustomer(@PathVariable("id")Long id){
        customerService.deleteCustomer(id);
        return new ResponseEntity<>("Customer id deleted",HttpStatus.OK);
    }
}
