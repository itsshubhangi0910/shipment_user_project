package com.example.userloginproject.service.impl;

import com.example.userloginproject.config.GetUserFromToken;
import com.example.userloginproject.model.CarrierDetails;
import com.example.userloginproject.model.Company;
import com.example.userloginproject.model.request.CarrierDetailsRequest;
import com.example.userloginproject.model.request.CompanyRequest;
import com.example.userloginproject.repository.CarrierDetailsRepository;
import com.example.userloginproject.repository.CompanyRepository;
import com.example.userloginproject.service.ICompanyService;
import com.example.userloginproject.utils.CompanyType;
import com.example.userloginproject.utils.VerificationStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class CompanyService implements ICompanyService {

    @Autowired
    private CompanyRepository companyRepository;

    @Autowired
    private CarrierDetailsRepository carrierDetailsRepository;

    @Autowired
    private GetUserFromToken getUserFromToken;

    @Override
    public Object signUpUser(CompanyRequest companyRequest) {
        Company company = new Company();
        company.setName(companyRequest.getName());
        company.setEmail(companyRequest.getEmail());
        company.setPhoneNo(companyRequest.getPhoneNo());
        company.setPassword(companyRequest.getPassword());
        if (companyRequest.getCompanyType().equalsIgnoreCase("BUSINESS")) {
            company.setCompanyType(CompanyType.BUSINESS);
        }else if (companyRequest.getCompanyType().equalsIgnoreCase("PERSONAL")){
            company.setCompanyType(CompanyType.PERSONAL);
        }else{
        }
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

    @Override
    public Object saveOrUpdateAccountDetails(CompanyRequest companyRequest) {
        if (companyRepository.existsById(companyRequest.getId())){
            Company company = companyRepository.findById(companyRequest.getId()).get();
            company.setName(companyRequest.getName());
            company.setPhoneNo(companyRequest.getPhoneNo());
            if (companyRequest.getCompanyType().equalsIgnoreCase("BUSINESS")) {
                company.setCompanyType(CompanyType.BUSINESS);
            }else if (companyRequest.getCompanyType().equalsIgnoreCase("PERSONAL")){
                company.setCompanyType(CompanyType.PERSONAL);
            }else{
            }
            company.setAddress(companyRequest.getAddress());
            company.setCountry(companyRequest.getCountry());
            company.setState(companyRequest.getState());
            company.setCity(companyRequest.getCity());
            company.setPostalCode(companyRequest.getPostalCode());
            company.setVerificationStatus(VerificationStatus.INCOMPLETE);
            companyRepository.save(company);
            company.setCompanyId(getUserFromToken.getUserFromToken().getCompanyId());
            companyRepository.save(company);
            return "updated successfully";
        }else {
            Company company = new Company();
            company.setName(companyRequest.getName());
            company.setPhoneNo(companyRequest.getPhoneNo());
            if (companyRequest.getCompanyType().equalsIgnoreCase("BUSINESS")) {
                company.setCompanyType(CompanyType.BUSINESS);
            }else if (companyRequest.getCompanyType().equalsIgnoreCase("PERSONAL")){
                company.setCompanyType(CompanyType.PERSONAL);
            }else{
            }
            company.setAddress(companyRequest.getAddress());
            company.setCountry(companyRequest.getCountry());
            company.setState(companyRequest.getState());
            company.setCity(companyRequest.getCity());
            company.setPostalCode(companyRequest.getPostalCode());
            company.setVerificationStatus(VerificationStatus.INCOMPLETE);
            companyRepository.save(company);
            return "save data successfully";
        }
    }

    @Override
    public Object saveOrUpdateCompanyCarrier(Long carrierDetailsId, List<Long> carrierIds) {
        List<CarrierDetails> list = new ArrayList<>();
        for(Long carrierId:carrierIds){
            CarrierDetails carrierDetails = new CarrierDetails();
            carrierDetails.setCarrierDetailsId(carrierDetailsId);
            carrierDetails.setIsActive(true);
            carrierDetails.setIsDeleted(false);
            carrierDetails.setCarrierId(carrierId);
            list.add(carrierDetails);
            carrierDetails.setCompanyId(carrierDetails.getCarrierDetailsId());
            carrierDetailsRepository.save(carrierDetails);
        }
        carrierDetailsRepository.saveAll(list);
        return "save data successfully";
    }
}
