package com.corebanking.system.mapper;

import com.corebanking.system.model.dto.AccountDto;
import com.corebanking.system.model.dto.CustomerDto;
import com.corebanking.system.model.entity.Account;
import com.corebanking.system.model.entity.Customer;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface CustomerMapper {
    CustomerDto jpeToDto(Customer customer);
    Customer dtoToJpe(CustomerDto customerDto);
    List<CustomerDto> jpeToDtoList(List<Customer> customers);
    List<Customer> dtoToJpe(List<CustomerDto> customerDtos);
    List<Account> accountList(List<AccountDto> accountDtoList);
}
