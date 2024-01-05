package com.corebanking.system.model.dto;

import com.corebanking.system.model.entity.Account;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Data;

import java.time.LocalDateTime;

@Data
public class FundsTransferDto {

    private Long id;
    @NotBlank(message = "Sender Account must be required ")
    private String senderAccount;
    @NotBlank(message = "Receiver Account must be required ")
    private String receiverAccount;
    private Double transferAmount;
    private LocalDateTime transferDate;

}
