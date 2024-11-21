package com.example.userloginproject.config;

import com.example.userloginproject.model.Company;
import com.example.userloginproject.service.IAdminUserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class GetUserFromToken {
    @Autowired
    private IAdminUserService iAdminUserService;

    public Company getUserFromToken() {
        // TokenDecodeResponse tokenDecodeResponse = null;
        try {
            UserDetails userDetails = (UserDetails) SecurityContextHolder.getContext().getAuthentication()
                    .getPrincipal();
            String username = userDetails.getUsername();
            System.out.println(username);
            //tokenDecodeResponse = decodeToken(JwtAuthenticationFilter.jwtTokenGlobal);
            return iAdminUserService.findByEmail(username);
        } catch (Exception e) {
            return iAdminUserService.findByEmail("ganesh@gmail.com");
        }
    }
}
