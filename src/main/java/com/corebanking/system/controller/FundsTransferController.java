package com.corebanking.system.controller;

import com.corebanking.system.model.dto.FundsTransferDto;
import com.corebanking.system.service.FundsTransfer;
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
    FundsTransfer fundsTransfer;

    @PostMapping("/transfer")
    public ResponseEntity<String> fundsTransfer(@Valid @RequestBody FundsTransferDto fundsTransferDto) {
        String response = fundsTransfer.transferFunds(fundsTransferDto);
        return new ResponseEntity<>(response, HttpStatus.OK);
        //new ResponseEntity<> (HttpStatus.OK).getBody(response);
    }
}
//    @GetMapping("/detail")
//    public List<FundsTransferDto> getSender(@RequestParam )
//}
