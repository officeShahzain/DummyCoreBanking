package com.corebanking.system.controller;

import com.corebanking.system.model.dto.AccountDto;
import com.corebanking.system.service.AccountService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
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
    @Operation(
            tags = "Add Account",
            description = "Add Account",
            responses = {
                    @ApiResponse(description = "Success",
                            responseCode = "200"
                    ),
                    @ApiResponse(description = "Data Not-Found",
                            responseCode = "404"
                    )
            }
    )
    @PostMapping("/add")
    public ResponseEntity<String> addAccount(@Valid @RequestBody AccountDto accountDto){
        String newAccount = accountService.createAccount(accountDto);
        return new ResponseEntity<>(newAccount, HttpStatus.CREATED);
    }
    @Operation(
            tags = "Update Account",
            description = "Update Accounts With AccountDto",
            responses = {
                    @ApiResponse(description = "Success",
                            responseCode = "200"
                    ),
                    @ApiResponse(description = "Data Not-Found",
                            responseCode = "404"
                    )
            }
    )
    @PutMapping("/update")
    public ResponseEntity<String> updateInfo(@RequestBody AccountDto accountDto){
        AccountDto updateAccountDto = accountService.updateAccount(accountDto);
        return new ResponseEntity<>(updateAccountDto.getAccountNumber()+" has been updated",HttpStatus.OK);
    }
    @Operation(
            tags = "Get Account",
            description = "Get Accounts With Account Number",
            responses = {
                    @ApiResponse(description = "Success",
                            responseCode = "200"
                    ),
                    @ApiResponse(description = "Data Not-Found",
                            responseCode = "404"
                    )
            }
    )
    @GetMapping("/getAccount")
    public ResponseEntity<AccountDto> getAccount(@RequestParam("accountNumber")String accountNumber)
    {
        AccountDto accountDto =  accountService.getAccountDetail(accountNumber);
        return new ResponseEntity<>(accountDto, HttpStatus.FOUND);
    }
    @Operation(
            tags = "Get Account",
            description = "Get Accounts With Cnic Number",
            responses = {
                    @ApiResponse(description = "Success",
                            responseCode = "200"
                    ),
                    @ApiResponse(description = "Data Not-Found",
                            responseCode = "404"
                    )
            }
    )
    @GetMapping("/getAccounts")
    //@Produces("application/json")
    public ResponseEntity<List<AccountDto>> getAllAccounts(@RequestParam("cnicNumber")String cnicNumber)
    {
        List<AccountDto> accountDtoList = accountService.listOfAccount(cnicNumber);
        return new ResponseEntity<>(accountDtoList,HttpStatus.FOUND);
    }
    @Operation(
            tags = "Verify Account",
            description = "Verify account with accountNumber and cnic Number",
            responses = {
                    @ApiResponse(
                            description = "Success",
                            responseCode = "200"
                    ),
                    @ApiResponse(
                            description = "Data Not-Found",
                            responseCode = "404"
                    )
            }
    )
    @GetMapping("/verifyAccount")
    public boolean verifyAccount(@RequestParam("cnic") String cnic, @RequestParam("accountNumber") String accountNumber){
         return accountService.accountAvailable(accountNumber, cnic);
    }

    @Operation(
            tags = "Delete Account",
            description = "Delete Accounts With Account-Id",
            responses = {
                    @ApiResponse(description = "Success",
                            responseCode = "200"
                    ),
                    @ApiResponse(description = "Data Not-Found",
                            responseCode = "404"
                    )
            }
    )
    @DeleteMapping("/delete/{accountId}")
    public ResponseEntity<String> deleteAccount(@PathVariable("accountId")Long accountId) {
        return new ResponseEntity<>(accountService.deleteAccount(accountId), HttpStatus.OK);
    }

}
