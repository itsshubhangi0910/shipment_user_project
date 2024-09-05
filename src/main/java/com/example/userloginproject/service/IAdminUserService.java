package com.example.userloginproject.service;

import com.example.userloginproject.model.Company;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;

public interface IAdminUserService {
    Object register(String email, String password);

    UserDetails loadUserByUsername(String email);

    Company findByEmail(String username);

    Company findByUsername(String username);
}
