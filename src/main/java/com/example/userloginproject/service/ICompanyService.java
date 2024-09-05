package com.example.userloginproject.service;

import com.example.userloginproject.model.request.CarrierDetailsRequest;
import com.example.userloginproject.model.request.CompanyRequest;

import java.util.List;

public interface ICompanyService {
    Object signUpUser(CompanyRequest companyRequest);

    Object getLoginUser(String email, String password);

    Object saveOrUpdateAccountDetails(CompanyRequest companyRequest);

    Object saveOrUpdateCompanyCarrier(Long carrierDetailsId, List<Long> carrierIds);
}
