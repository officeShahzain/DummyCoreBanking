package com.corebanking.system.mapper;

import com.corebanking.system.model.dto.AccountDto;
import com.corebanking.system.model.dto.FundsTransferDto;
import com.corebanking.system.model.entity.Account;
import com.corebanking.system.model.entity.FundsTransfer;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "spring")
public interface FundsTransferMapper {
    @Mapping(source = "fundsTransfer.senderAccount.id", target = "senderAccount")
    @Mapping(source = "fundsTransfer.receiverAccount.id", target = "receiverAccount")
    FundsTransferDto jpeTODto(FundsTransfer fundsTransfer);
    /*@Mapping(source = "account.customer.id", target = "customerId")
    AccountDto jpeToDto(Account account);
    //@Mapping(source = "customerId", target = "customer")
    @Mapping(source = "accountDto.customerId", target = "customer.id")
    Account dtoToJpe(AccountDto accountDto);*/
    @Mapping(source = "sender", target = "senderAccount")
    @Mapping(source = "receiver", target = "receiverAccount")
    //@Mapping(source = "fundsTransferDto.transferAmount", target = "transferAmount")
    //@Mapping(source = "fundsTransferDto.transferDate", target = "transferDate")
    @Mapping(target = "id", ignore = true) // Ignore the id property
    FundsTransfer FundsTransferToFundsTransferDto(FundsTransferDto fundsTransferDto, Account sender, Account receiver);

    List<FundsTransferDto> listOfFundsDto(List<FundsTransfer> fundsTransfer);

}
