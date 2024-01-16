package com.corebanking.system;

import com.corebanking.system.dataInitializer.DataInitializer;
import com.corebanking.system.model.dto.AccountDto;
import com.corebanking.system.model.dto.CustomerDto;
import com.corebanking.system.model.entity.Account;
import com.corebanking.system.model.entity.Customer;
import com.corebanking.system.repository.CustomerRepository;
import com.corebanking.system.service.AccountService;
import com.corebanking.system.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.List;

@SpringBootApplication
public class AccountServiceApplication extends DataInitializer {
	@Autowired
	CustomerService customerService;
	@Autowired
	AccountService accountService;

	@Autowired
	CustomerRepository customerRepository;

	public static void main(String[] args) {
		SpringApplication.run(AccountServiceApplication.class, args);
	}

}

