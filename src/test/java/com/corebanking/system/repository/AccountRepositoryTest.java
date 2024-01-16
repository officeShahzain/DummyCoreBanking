package com.corebanking.system.repository;

import com.corebanking.system.model.dto.AccountDto;
import com.corebanking.system.model.entity.Account;
import com.corebanking.system.model.entity.Customer;
import com.corebanking.system.service.AccountService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.util.Assert;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.verify;

/*@ExtendWith(MockitoExtension.class)
class AccountRepositoryTest {
    @Mock
    private AccountRepository accountRepository;
    @Autowired
    private AccountService accountService;

    @Test
    void findByAccountNumberAndCnic() {
        String cnicNumber = "12345678910135";
        String accountNumber = "zanbeel-b8eab5d";
        //boolean accountAvailable =
        accountService.accountAvailable(accountNumber, cnicNumber);
        verify(accountRepository).findByAccountNumberAndCnic(cnicNumber,accountNumber);
        //assertTrue(accountAvailable);
        //assertTrue(accountAvailable);
    //    List<Account> accountList = accountRepository.findByCnic("12345678910135");
    }

    @Test
    void findByAccountNumber() {
         String accountNumber = "zanbeel-b8eab5d";
        AccountDto account = accountService.getAccountDetail(accountNumber);
        assertEquals(accountNumber,account.getAccountNumber());
    }


    @Test
    void findByCnic() {
        String cnic = "12345678910135";
        List<Account> accountList = accountRepository.findByCnic(cnic);
        assertFalse(accountList.isEmpty());
        assertEquals(cnic,accountList.get(0).getCnic());
        }
}*/