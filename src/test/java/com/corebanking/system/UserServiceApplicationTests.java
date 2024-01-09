package com.corebanking.system;

import com.corebanking.system.repository.CustomerRepository;
import com.corebanking.system.service.CustomerService;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class UserServiceApplicationTests {
	@Test
	void contextLoads() {
	}
	@Mock
	private CustomerRepository customerRepository;
	private CustomerService customerService;


}
