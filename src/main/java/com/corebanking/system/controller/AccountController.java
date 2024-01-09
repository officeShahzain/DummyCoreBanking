package com.corebanking.system.controller;

import com.corebanking.system.model.dto.AccountDto;
import com.corebanking.system.service.AccountService;
import jakarta.validation.Valid;
import jakarta.ws.rs.Produces;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/account")
public class AccountController {
    @Autowired
    AccountService accountService;
    @PostMapping("/add")
    public ResponseEntity<String> addAccount(@Valid @RequestBody AccountDto accountDto){
        String newAccount = accountService.createAccount(accountDto);
        return new ResponseEntity<>(newAccount, HttpStatus.CREATED);
    }
    @PutMapping("/update")
    public ResponseEntity<String> updateInfo(@RequestBody AccountDto accountDto){
        AccountDto updateAccountDto = accountService.updateAccount(accountDto);
        return new ResponseEntity<>(updateAccountDto.getAccountNumber()+" has been updated",HttpStatus.OK);
    }
    @GetMapping("/getAccount")
    public ResponseEntity<AccountDto> getAccount(@RequestParam("accountNumber")String accountNumber)
    {
        AccountDto accountDto =  accountService.getAccountDetail(accountNumber);
        return new ResponseEntity<>(accountDto, HttpStatus.FOUND);
    }
    @GetMapping("/getAccounts")
    @Produces("application/json")
    public ResponseEntity<List<AccountDto>> getAllAccounts(@RequestParam("cnicNumber")String cnicNumber)
    {
        List<AccountDto> accountDtoList = accountService.listOfAccount(cnicNumber);
        return new ResponseEntity<>(accountDtoList,HttpStatus.FOUND);
    }

    @GetMapping("/verifyAccount")
    public boolean verifyAccount(@RequestParam("cnic") String cnic, @RequestParam("accountNumber") String accountNumber){
         return accountService.accountAvailable(accountNumber, cnic);
    }
    @DeleteMapping("/delete/{accoutId}")
    public ResponseEntity<String> deleteAccount(@PathVariable("accountId")Long accountId) {
        return new ResponseEntity<>(accountService.deleteAccount(accountId), HttpStatus.OK);
    }

}
