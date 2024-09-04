package com.example.userloginproject.model.request;

import com.example.userloginproject.utils.Role;
import lombok.Getter;
import lombok.Setter;


@Getter
@Setter
public class UserRequest {
    private Long userId;
    private String firstName;
    private String lastName;
    private String mobileNo;
    private String email;
    private String password;


}
