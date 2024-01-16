package com.corebanking.system.controller;

import com.corebanking.system.model.dto.FundsTransferDto;
import com.corebanking.system.service.FundsTransferService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/funds")
public class FundsTransferController {
    @Autowired
    FundsTransferService fundsTransferService;

    @PostMapping("/transfer")
    public ResponseEntity<String> fundsTransfer(@Valid @RequestBody FundsTransferDto fundsTransferDto) {
        String response = fundsTransferService.transferFunds(fundsTransferDto);
        return new ResponseEntity<>(response, HttpStatus.OK);
        //new ResponseEntity<> (HttpStatus.OK).getBody(response);
    }
    @GetMapping("/statement")
    public ResponseEntity<?> getSender(@RequestParam ("accountNumber") String senderAccount){
        List<FundsTransferDto> historyList = fundsTransferService.fundsHistory(senderAccount);
        if(historyList.isEmpty())
        {
            return new ResponseEntity<>("no history of given account",HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(historyList,HttpStatus.FOUND);
    }
}
