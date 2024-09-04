package com.example.userloginproject.model.response;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class RegisterResponse {
    private String name;
    private String email;
    private String password;
    private Long companyId;
    private String mobileNo;
    private  Boolean isActive=true;
    private Boolean isDeleted=false;


}
