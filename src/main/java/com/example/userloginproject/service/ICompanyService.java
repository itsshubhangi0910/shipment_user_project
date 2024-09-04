package com.example.userloginproject.service;

import com.example.userloginproject.model.request.CompanyRequest;

public interface ICompanyService {
    Object signUpUser(CompanyRequest companyRequest);

    Object getLoginUser(String email, String password);
}
