package com.corebanking.system.service.Impl;

import com.corebanking.system.exception.ResourceNotFoundException;
import com.corebanking.system.mapper.CustomerMapper;
import com.corebanking.system.model.dto.AccountDto;
import com.corebanking.system.model.entity.Account;
import com.corebanking.system.repository.CustomerRepository;
import com.corebanking.system.model.dto.CreateOTPDto;
import com.corebanking.system.model.dto.LoginDto;
import com.corebanking.system.model.dto.CustomerDto;
import com.corebanking.system.model.entity.Customer;
import com.corebanking.system.service.AccountService;
import com.corebanking.system.service.CustomerService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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
    @Autowired
    AccountService accountService;
    @Override
    public String addUser(CustomerDto customer) {
        Optional<Customer> customerExist = customerRepository.findByCnicOrEmailOrUserName(customer.getCnic(),customer.getEmail(),customer.getUserName());
        if(customerExist.isPresent()){
            logger.error("Customer Already registered against cnic No: {} email: {} mobile No: ",customer.getCnic(),customer.getEmail());
            if(customerExist.get().getCnic().equals(customer.getCnic())) //|| customerExist.get().getEmail().equals(customer.getEmail()))
            {
                return  "this Cnic : "+customer.getCnic()+" already register ";
            }
            else if(customerExist.get().getEmail().equals(customer.getEmail()))
            {
                return "email : "+customer.getEmail()+" already register ";
            }
            else if(customerExist.get().getUserName().equals(customer.getUserName()))
            {
                return "this User Name is already registered : "+customer.getUserName();
            }
            return "Customer Already registered against cnic : "+customer.getCnic() +", email : "+customer.getEmail()+", user name : "+customer.getUserName();
        }
        Customer newCustomer = save(customer);
        logger.info("customer save Successfully");
        return "customer register successfully against cnic number : "+ customer.getCnic();
    }
    @Override
    public void deleteCustomer(Long id) {
        customerRepository.deleteById(id);
    }

    public Customer save(CustomerDto customer) {
        return customerRepository.save(customerMapper.dtoToJpe(customer));
    }

    /*public Customer getCustomerWithEmail(String email,Long Id) {
        return Optional.ofNullable(this.customerRepository.findByEmailOrId(email,Id)).orElseThrow(() -> new ResourceNotFoundException(String.format("User with email %s not found", email)));
    }*/

    @Override
    public CustomerDto getCustomerWithCnic(String cnic) {
        Customer customer = customerRepository.findByCnic(cnic).orElseThrow(()-> new ResourceNotFoundException("Customer does not exist against Cnic Number : "+cnic));

        List<AccountDto> customerAccount = accountService.listOfAccount(customer.getCnic());
        CustomerDto customerDto = customerMapper.jpeToDto(customer);
        customerDto.setAccountList(customerAccount);
        return customerDto;
    }

    public List<Customer> getAllCustomers() {
        return this.customerRepository.findAll();
    }

    @Override
    public CustomerDto getCustomerWithEmail(String email) {
        Optional<Customer> customer = customerRepository.findByEmail(email);
        if(customer.isEmpty()) {
            throw new ResourceNotFoundException("invalid email");
        }
        return customerMapper.jpeToDto(customer.get());
    }

    @Override
    public CustomerDto updateCustomer(CustomerDto customerDto) {
        Optional<Customer> updateCustomer = customerRepository.findByEmailOrId(customerDto.getEmail(),customerDto.getId());
        if(updateCustomer.isEmpty())
        {
            throw new ResourceNotFoundException(String.format("User with email or id  %s not found ", customerDto.getEmail()));
        }
        updateCustomer.get().setFirstName(customerDto.getFirstName());
        updateCustomer.get().setLastName(customerDto.getLastName());
        updateCustomer.get().setEmail(customerDto.getEmail());
        logger.info("Customer has been updated with Id {}", updateCustomer.get().getId());
        return customerMapper.jpeToDto(save(customerMapper.jpeToDto(updateCustomer.get())));
    }

    public Customer createOTP(CreateOTPDto createOTPDto)
    {
        return null;
    }
    public String login(LoginDto loginDto) {
        Optional<Customer> customer = customerRepository.findByEmail(loginDto.getEmail());
        if(customer.isEmpty())
        {
            if(customer.get().getEmail().equals(loginDto.getEmail()) && customer.get().getPassword().equals(loginDto.getPassword()))
            {
                return "welcome";
            }
            else {
                throw new ResourceNotFoundException("invalid email or password");
            }
        }
        return "invalid email";
    }

    @Override
    public CustomerDto getCustomerWithId(Long id) {
        Customer customer = customerRepository.findById(id).orElseThrow(()->new ResourceNotFoundException("Customer is not exist"));
        return customerMapper.jpeToDto(customer);
    }
}
