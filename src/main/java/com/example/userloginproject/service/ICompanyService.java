package com.example.userloginproject.service;

import com.example.userloginproject.model.request.BrandDetailsRequest;
import com.example.userloginproject.model.request.BusinessDetailsRequest;
import com.example.userloginproject.model.request.CarrierDetailsRequest;
import com.example.userloginproject.model.request.CompanyRequest;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface ICompanyService {
    Object signUpUser(CompanyRequest companyRequest);

    Object getLoginUser(String email, String password);

    Object saveOrUpdateCompanyAccountDetails(CompanyRequest companyRequest) throws Exception;

    Object saveOrUpdateCompanyCarrier(Long carrierDetailsId, List<Long> carrierIds);

    Object saveOrUpdateBrandDetails(BrandDetailsRequest brandDetailsRequest) throws Exception;

    Object brandLogoImage(MultipartFile logo) throws Exception;

    Object saveOrUpdateBusinessDetails(BusinessDetailsRequest businessDetailsRequest) throws Exception;

    Object getBusinessDetailsById(Long businessDetailsId) throws Exception;
}
