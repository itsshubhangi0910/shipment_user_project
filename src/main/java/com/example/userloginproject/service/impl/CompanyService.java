package com.example.userloginproject.service.impl;

import com.example.userloginproject.model.Company;
import com.example.userloginproject.model.request.CompanyRequest;
import com.example.userloginproject.repository.CompanyRepository;
import com.example.userloginproject.service.ICompanyService;
import com.example.userloginproject.utils.VerificationStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CompanyService implements ICompanyService {

    @Autowired
    private CompanyRepository companyRepository;

    @Override
    public Object signUpUser(CompanyRequest companyRequest) {
        Company company = new Company();
        company.setName(companyRequest.getName());
        company.setEmail(companyRequest.getEmail());
        company.setMobileNo(companyRequest.getMobileNo());
        company.setPassword(companyRequest.getPassword());
        company.setIsActive(true);
        company.setVerificationStatus(VerificationStatus.INCOMPLETE);
        company.setIsDeleted(false);
        companyRepository.save(company);
        company.setCompanyId(company.getId());
        companyRepository.save(company);

        return "user SignUp Successfully";
    }

    @Override
    public Object getLoginUser(String email, String password) {
        if (companyRepository.existsByEmail(email)){
            Company user = companyRepository.findByEmail(email);
            if(user.getPassword().equals(password)){
                return "user login successfully";
            }
            else {
                return "login failed";
            }

        }else {
            return "user not found";
        }
    }
}
