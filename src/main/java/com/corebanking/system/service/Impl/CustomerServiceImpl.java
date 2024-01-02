package com.corebanking.system.service.Impl;

import com.corebanking.system.exception.ResourceNotFoundException;
import com.corebanking.system.mapper.CustomerMapper;
import com.corebanking.system.repository.CustomerRepository;
import com.corebanking.system.model.dto.CreateOTPDto;
import com.corebanking.system.model.dto.LoginDto;
import com.corebanking.system.model.dto.CustomerDto;
import com.corebanking.system.model.entity.Customer;
import com.corebanking.system.service.CustomerService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CustomerServiceImpl implements CustomerService
{
    private static final Logger logger = LoggerFactory.getLogger(CustomerServiceImpl.class);
    @Autowired
    private CustomerRepository customerRepository;
    @Autowired
    CustomerMapper customerMapper;
    @Override
    public String addUser(Customer customer) {
        Optional<Customer> customerExist = customerRepository.findByCnicOrEmailOrMobileNumber(customer.getCnic(),customer.getEmail(),customer.getMobileNumber());
        if(customerExist.isPresent())
        {
            logger.error("Customer Already registered against cnic No: {} email: {} mobile No: ",customer.getCnic(),customer.getEmail(), customer.getMobileNumber());
            return "Customer Already registered against cnic : "+customer.getCnic() +"email: "+customer.getEmail()+" password:  "+ customer.getMobileNumber();
        }
        Customer newCustomer = save(customer);
        logger.info("customer save Successfully");
        return "customer register successfully against cnic number : "+ customer.getCnic();
    }
    @Override
    public void deleteCustomer(Long id) {
        this.customerRepository.deleteById(id);
    }

    public Customer save(Customer customer) {
        return customerRepository.save(customer);
    }

    public Customer getCustomerWithEmail(String email) {
        return Optional.ofNullable(this.customerRepository.findByEmail(email)).orElseThrow(() -> new ResourceNotFoundException(String.format("User with email %s not found", email)));
    }

    @Override
    public Customer getCustomerWithCnic(String cnic) {
        Customer customer = customerRepository.findByCnic(cnic).orElseThrow(()-> new ResourceNotFoundException("Customer does not exist against Cnic Number : "+cnic));
        return customer;
    }

    public List<Customer> getAllCustomers() {
        return this.customerRepository.findAll();
    }

    @Override
    public CustomerDto updateCustomer(CustomerDto customerDto) {
        Customer updateCustomer = this.customerRepository.findByEmail(customerDto.getEmailAddress());
        if(updateCustomer == null)
        {
            throw new ResourceNotFoundException(String.format("User with email %s not found", customerDto.getEmailAddress()));
        }

        updateCustomer.setFirstName(customerDto.getFirstName());
        updateCustomer.setLastName(customerDto.getLastName());
        updateCustomer.setEmail(customerDto.getEmailAddress());
        logger.info("Customer has been updated with Id {}", updateCustomer.getId());
        return customerMapper.jpeToDto(save(updateCustomer));
    }

    public Customer createOTP(CreateOTPDto createOTPDto)
    {
        return null;
    }
    public String login(LoginDto loginDto) {
        Customer customer = customerRepository.findByEmail(loginDto.getEmail());
        if(customer!=null)
        {
            if(customer.getEmail().equals(loginDto.getEmail()) && customer.getPassword().equals(loginDto.getPassword()))
            {
                return "welcome";
            }
            else {
                throw new ResourceNotFoundException("invalid email or password");
            }
        }
        return "invalid email";
    }
}
