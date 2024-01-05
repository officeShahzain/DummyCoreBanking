package com.corebanking.system.service.Impl;

import com.corebanking.system.mapper.FundsTransferMapper;
import com.corebanking.system.model.dto.FundsTransferDto;
import com.corebanking.system.model.entity.Account;
import com.corebanking.system.repository.AccountRepository;
import com.corebanking.system.repository.FundsRepository;
import com.corebanking.system.service.FundsTransfer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.swing.text.html.Option;
import java.time.LocalDateTime;
import java.util.Optional;
@Transactional
@Service
public class FundsTransferImpl implements FundsTransfer {
    Logger logger = LoggerFactory.getLogger(FundsTransferImpl.class);
    @Autowired
    AccountRepository accountRepository;
    @Autowired
    FundsRepository fundsRepository;
    @Autowired
    FundsTransferMapper fundsTransferMapper;
    @Override
    public String transferFunds(FundsTransferDto fundsTransferDto) {
//        String senderAccount = fundsTransferDto.getSenderAccount();
//        String receiverAccount = fundsTransferDto.getReceiverAccount();
        return transaction(fundsTransferDto);
    }
    @Override
    public String debit(FundsTransferDto fundsTransferDto) {
        return null;
    }
    //check sender and receiver have same account
    private boolean senderAndReceiverSame(String senderAccount, String receiverAccount){
        if(senderAccount.equals(receiverAccount)) {
            return true;
        }
        return false;
    }
    private String transaction(FundsTransferDto fundsTransferDto){
        Optional<Account> senderAccount = accountRepository.findByAccountNumber(fundsTransferDto.getSenderAccount());
        Optional<Account> receiverAccount = accountRepository.findByAccountNumber(fundsTransferDto.getReceiverAccount());
        if(senderAccount.isPresent() && receiverAccount.isPresent()) {
            if(senderAndReceiverSame(fundsTransferDto.getSenderAccount(), fundsTransferDto.getReceiverAccount())){
                return "Sender And Receiver have same account ";
            }
           double senderBalance = senderAccount.get().getBalance();
            if (senderBalance >= fundsTransferDto.getTransferAmount()){
                double receiverBalance = receiverAccount.get().getBalance()+fundsTransferDto.getTransferAmount();
                receiverAccount.get().setBalance(receiverBalance);

                senderAccount.get().setBalance(senderBalance-fundsTransferDto.getTransferAmount());
                accountRepository.save(senderAccount.get());
                accountRepository.save(receiverAccount.get());
                fundsTransferDto.setTransferDate(LocalDateTime.now());
                /*com.corebanking.system.model.entity.FundsTransfer funds = com.corebanking.system.model.entity.FundsTransfer.builder()
                        .transferAmount(fundsTransferDto.getTransferAmount())
                        .senderAccount(senderAccount.get())
                        .receiverAccount(receiverAccount.get())
                        .transferDate(LocalDateTime.now())
                        .build();*/
                com.corebanking.system.model.entity.FundsTransfer fundsTransfer = fundsTransferMapper.FundsTransferToFundsTransferDto(fundsTransferDto, senderAccount.get(), receiverAccount.get());
                logger.info(String.valueOf(fundsTransfer));
          //          fundsRepository.save(funds);
                    fundsRepository.save(fundsTransfer);

                return "Transaction successfully from "+fundsTransferDto.getSenderAccount()+" to "+fundsTransferDto.getReceiverAccount()+" amount : "+fundsTransferDto.getTransferAmount()+" on date "+fundsTransferDto.getTransferDate();
            }
            if(senderBalance < fundsTransferDto.getTransferAmount()){
                return "insufficient balance ";
            }
        }
        return "sender or receiver not exist ";

    }
}
