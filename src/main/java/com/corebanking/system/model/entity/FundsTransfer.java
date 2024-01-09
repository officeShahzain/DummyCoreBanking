package com.corebanking.system.model.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@Entity
@Builder
@Table(name = "funds_transfer")
@NoArgsConstructor
@AllArgsConstructor
public class FundsTransfer {
        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        private Long id;

        @ManyToOne
        @JoinColumn(name = "sender_account_id")//,nullable = false)
        private Account senderAccount;

        @ManyToOne
        @JoinColumn(name = "receiver_account_id")
        private Account receiverAccount;

        @Column(name = "amount", nullable = false)
        private Double transferAmount;

        @Column(name = "transfer_date", nullable = false)
        private LocalDateTime transferDate;

    }

