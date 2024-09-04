package com.example.userloginproject.model.response;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class LoginResponse {
    private String token;
    private Long id;
    private String name;
    private Long companyId;
    private String mobileNo;


}