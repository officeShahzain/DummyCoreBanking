package com.corebanking.system.service;

import com.corebanking.system.model.dto.FundsTransferDto;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

public interface FundsTransfer {
    String transferFunds(FundsTransferDto fundsTransferDto);
    String debit(FundsTransferDto fundsTransferDto);
}
