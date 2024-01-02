package com.corebanking.system.model.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CustomerDto {

    private String emailAddress;
    private String mobileNumber;
    private String cnic;
    private String dateOfBirth;
    private String firstName;
    private String middleName;
    private String lastName;
}
