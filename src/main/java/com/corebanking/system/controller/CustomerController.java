package com.corebanking.system.controller;


import com.corebanking.system.model.dto.AccountDto;
import com.corebanking.system.model.dto.CreateOTPDto;
import com.corebanking.system.model.dto.CustomerDto;
import com.corebanking.system.model.dto.LoginDto;
import com.corebanking.system.model.entity.Customer;
import com.corebanking.system.service.CustomerService;
import com.corebanking.system.service.Impl.CustomerServiceImpl;
import jakarta.validation.Valid;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping("/customer")
public class CustomerController
{
    private static final Logger LOGGER = LoggerFactory.getLogger(CustomerController.class);

    @Autowired
    private CustomerService customerService;
    @PostMapping("/signup")
    public String register(@Valid @RequestBody CustomerDto customer)
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
    public ResponseEntity<CustomerDto> updateExistCustomer(@RequestBody CustomerDto customerDto){
        CustomerDto updatedCustomer = customerService.updateCustomer(customerDto);
        return new ResponseEntity<>(updatedCustomer,HttpStatus.OK);
    }
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> deleteCustomer(@PathVariable("id")Long id){
        customerService.deleteCustomer(id);
        return new ResponseEntity<>("Customer id deleted",HttpStatus.OK);
    }
    @GetMapping("/get")
    public ResponseEntity<CustomerDto> getCustomerWithCnicNumber(@RequestParam("cnicNumber")String cnicNumber){
        CustomerDto customer = customerService.getCustomerWithCnic(cnicNumber);
        return new ResponseEntity<>(customer, HttpStatus.FOUND);
    }
    @GetMapping("/get/{customerId}")
    public ResponseEntity<CustomerDto> getCustomerWithCustomerId(@PathVariable("customerId")Long customerId) {
        CustomerDto customer = customerService.getCustomerWithId(customerId);
        return new ResponseEntity<>(customer,HttpStatus.FOUND);
    }
    @GetMapping("/get/email")
    public ResponseEntity<CustomerDto> getCustomerWithEmail(@RequestParam("email")String email){
        CustomerDto customer = customerService.getCustomerWithEmail(email);
        return new ResponseEntity<>(customer, HttpStatus.FOUND);
    }
}
