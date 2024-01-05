package com.corebanking.system.mapper;

import com.corebanking.system.model.dto.AccountDto;
import com.corebanking.system.model.entity.Account;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "spring")
public interface AccountMapper {
    @Mapping(source = "account.customer.id", target = "customerId")
    AccountDto jpeToDto(Account account);
    //@Mapping(source = "customerId", target = "customer")
    @Mapping(source = "accountDto.customerId", target = "customer.id")
    Account dtoToJpe(AccountDto accountDto);
    List<Account> accountEntityList (List<AccountDto>accountDtoList);
    List<AccountDto> accountDtoList (List<Account> accountEntityList);

}
