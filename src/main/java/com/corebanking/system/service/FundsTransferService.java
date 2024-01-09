package com.corebanking.system.service;

import com.corebanking.system.model.dto.FundsTransferDto;

import java.util.List;

public interface FundsTransferService {
    String transferFunds(FundsTransferDto fundsTransferDto);
    List<FundsTransferDto> fundsHistory(String accountNumber);
}
