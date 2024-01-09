package com.corebanking.system.model.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Account {
    private String name;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String cnic;
    private String dob;
    private String accountNumber;
    private String accountType;
    private String branch;
    private String bankName;
    private String city;
    private String email;
    private String cellNumber;
    private String cnicIssuance;
    private String cnicExpiry;
    private String purposeOfAccount;
    private String sourceOfIncome;
    private String residentialAddress;
    private String lineOfBusiness;
    private String businessAddress;
    private double balance;
    @ManyToOne
    @JoinColumn(name = "customer_id")
            @JsonBackReference
    Customer customer;
}
