package com.example.userloginproject.model.request;

import com.example.userloginproject.utils.CompanyType;
import com.example.userloginproject.utils.VerificationStatus;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;


@Getter
@Setter
@AllArgsConstructor
public class CompanyRequest {

    private Long id;
    private String name;
    private String email;
    private String password;
    private String phoneNo;
    private String address;
    private String country;
    private String state;
    private String city;
    private String postalCode;
    private String companyType;


}
