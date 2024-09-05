package com.example.userloginproject.service.impl;

import com.example.userloginproject.config.GetUserFromToken;
import com.example.userloginproject.model.Company;
import com.example.userloginproject.model.response.RegisterResponse;
import com.example.userloginproject.repository.CompanyRepository;
import com.example.userloginproject.service.IAdminUserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

@Slf4j
@Service
public class AdminUserService implements UserDetailsService, IAdminUserService {

    @Autowired
    private CompanyRepository companyRepository;

    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    @Autowired
    private GetUserFromToken getUserFromToken;


    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        Company company = companyRepository.findByEmailIgnoreCaseAndIsDeleted(email, false);
       /* if (user == null) {
            // throw new UsernameNotFoundException("Invalid username");
            user = teamRepository.findByMobileNumber(email);
        }*/
        return new org.springframework.security.core.userdetails.User(company.getEmail(), company.getPassword(),
                getAuthority(company));

    }

    @Override
    public Company findByEmail(String username) {
        Company company = companyRepository.findByEmailIgnoreCaseAndIsDeleted(username, false);
       /* if (user == null) {
            user = teamRepository.findByMobileNumber(username);
        }*/
        return company;
    }

    private Collection<? extends GrantedAuthority> getAuthority(Company company) {
        Set<SimpleGrantedAuthority> authorities = new HashSet<>();
        authorities.add(new SimpleGrantedAuthority("ROLE_" + "Admin"));
        return authorities;
    }

    @Override
    public Object register(String email, String password) {
       // try {
            Company company = new Company();
            company.setEmail(email);
            company.setPassword((bCryptPasswordEncoder.encode(password)));
           // user.setGender(Gender.MALE);
           // company.setName("HomeStay");
            //company.setPhoneNo("Admin");
            company.setIsActive(true);
            company.setIsDeleted(false);
            companyRepository.save(company);
            company.setCompanyId(getUserFromToken.getUserFromToken().getCompanyId());
           companyRepository.save(company);
       /* } catch (Exception e) {
            e.printStackTrace();
        }*/
        return "User Registered successfully";

    }

    @Override
    public Company findByUsername(String username) {
        Company company = companyRepository.findByEmailIgnoreCaseAndIsDeleted(username, false);
        /*if (company == null) {
            company = companyRepository.findByMobileNumber(username);
        }*/
        return company;
    }
}

