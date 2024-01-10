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

//	@Override
//	public void run(String... args) throws Exception {
//		//List<Customer> customerList = customerService.getAllCustomers();
//		//if(customerList.isEmpty())
//		//{
//			for (int i = 0; i < 10; i++) {
//				CustomerDto customer = new CustomerDto();
//				customer.setMobileNumber("0300331019" + i);
//				customer.setFirstName("First ");
//				customer.setLastName("Customer " + i);
//				customer.setCnic("123456789101" + i);
//				customer.setEmail("customer" + i + "@example.com");
//				customer.setUserName(customer.getLastName() + i);
//				customer.setPassword(customer.getFirstName() + i);
//				customer.setSecurityPicture("security" + i);
//				customerService.addUser(customer);
//				//List<Customer> customeList = customerService.getAllCustomers();
//				//if (customeList.isEmpty()) {
//				//	System.out.println("there is no any customer register yet please register before create account ");
//				//}
//				//else {
//					AccountDto account = new AccountDto();
//					account.setName(customer.getUserName());
//					account.setCnic(customer.getCnic());
//					account.setDob("1990-01-01");
//					account.setAccountType("Saving");
//					account.setBranch("Main Branch");
//					account.setBankName("Meezan Bank");
//					account.setCity("Karachi");
//					account.setEmail(customer.getEmail());
//					account.setCellNumber(customer.getMobileNumber());
//					account.setCnicIssuance("2000-01-01");
//					account.setCnicExpiry("2030-01-01");
//					account.setPurposeOfAccount("Personal");
//					account.setSourceOfIncome("Employment");
//					account.setResidentialAddress("123 Main Street, Karachi");
//					account.setLineOfBusiness("Software Development");
//					account.setBalance(1000.0 * (i + 1));
//					account.setBusinessAddress("456 Business Avenue, Karachi");
//					account.setCustomerId(customerService.getCustomerWithCnic(customer.getCnic()).getId());
//					accountService.createAccount(account);
//
//				//}
//			}

			}
		//}

	//}
//}