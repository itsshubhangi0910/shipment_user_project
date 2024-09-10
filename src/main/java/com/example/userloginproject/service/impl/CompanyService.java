package com.example.userloginproject.service.impl;

import com.example.userloginproject.config.GetUserFromToken;
import com.example.userloginproject.model.BrandDetails;
import com.example.userloginproject.model.BusinessDetails;
import com.example.userloginproject.model.CarrierDetails;
import com.example.userloginproject.model.Company;
import com.example.userloginproject.model.request.BrandDetailsRequest;
import com.example.userloginproject.model.request.BusinessDetailsRequest;
import com.example.userloginproject.model.request.CompanyRequest;
import com.example.userloginproject.repository.BrandDetailsRepository;
import com.example.userloginproject.repository.BusinessDetailsRepository;
import com.example.userloginproject.repository.CarrierDetailsRepository;
import com.example.userloginproject.repository.CompanyRepository;
import com.example.userloginproject.service.ICompanyService;
import com.example.userloginproject.utils.CompanyType;
import com.example.userloginproject.utils.VerificationStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
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

    @Autowired
    private BrandDetailsRepository brandDetailsRepository;

    @Autowired
    private BusinessDetailsRepository businessDetailsRepository;

    @Override
    public Object signUpUser(CompanyRequest companyRequest) {
        Company company = new Company();
        company.setName(companyRequest.getName());
        company.setEmail(companyRequest.getEmail());
        company.setPhoneNo(companyRequest.getPhoneNo());
        company.setPassword(companyRequest.getPassword());
        if (companyRequest.getCompanyType().equalsIgnoreCase("BUSINESS")) {
            company.setCompanyType(CompanyType.BUSINESS);
        } else if (companyRequest.getCompanyType().equalsIgnoreCase("PERSONAL")) {
            company.setCompanyType(CompanyType.PERSONAL);
        } else {
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
        if (companyRepository.existsByEmail(email)) {
            Company user = companyRepository.findByEmail(email);
            if (user.getPassword().equals(password)) {
                return "user login successfully";
            } else {
                return "login failed";
            }

        } else {
            return "user not found";
        }
    }

    @Override
    public Object saveOrUpdateCompanyAccountDetails(CompanyRequest companyRequest) throws Exception {
        if (companyRepository.existsById(companyRequest.getId())) {
            Company company = companyRepository.findById(companyRequest.getId()).get();
            if (companyRepository.existsByEmailIgnoreCaseAndIsDeleted(companyRequest.getEmail().trim(), false)) {
                throw new Exception("This Email is Already Exists..!");
            } else {
                company.setEmail(companyRequest.getEmail());
            }
                company.setName(companyRequest.getName());
                company.setPhoneNo(companyRequest.getPhoneNo());
                if (companyRequest.getCompanyType().equalsIgnoreCase("BUSINESS")) {
                    company.setCompanyType(CompanyType.BUSINESS);
                } else if (companyRequest.getCompanyType().equalsIgnoreCase("PERSONAL")) {
                    company.setCompanyType(CompanyType.PERSONAL);
                } else {
                }
                company.setAddress(companyRequest.getAddress());
                company.setCountry(companyRequest.getCountry());
                company.setState(companyRequest.getState());
                company.setCity(companyRequest.getCity());
                company.setPostalCode(companyRequest.getPostalCode());
                company.setVerificationStatus(VerificationStatus.INCOMPLETE);
                companyRepository.save(company);
            }
        return "updated successfully";
    }

    @Override
    public Object saveOrUpdateCompanyCarrier(Long carrierDetailsId, List<Long> carrierIds) {
        try {
            Long companyId = getUserFromToken.getUserFromToken().getCompanyId();

            // Check if the carrier details exist (Update case)
            if ((carrierDetailsId != null && carrierDetailsId>0) && carrierDetailsRepository.existsByCompanyId(companyId)) {
                carrierDetailsRepository.deleteAllByCompanyId(companyId);

                    if (carrierIds != null && !carrierIds.isEmpty()) {
                        for (Long carrierId : carrierIds) {
                            CarrierDetails carrierDetails = new CarrierDetails();
                            carrierDetails.setCompanyId(companyId);
                            carrierDetails.setIsActive(true);
                            carrierDetails.setIsDeleted(false);
                            carrierDetails.setCarrierId(carrierId);
                            carrierDetailsRepository.save(carrierDetails);
                        }
                    }
                    return "Updated successfully";
            } else {
                // Save new carrier details (Create case)
                if (carrierIds != null && !carrierIds.isEmpty()) {
                    List<CarrierDetails> list = new ArrayList<>();

                    for (Long carrierId : carrierIds) {
                        CarrierDetails carrierDetails = new CarrierDetails();
                        carrierDetails.setCompanyId(companyId);
                        carrierDetails.setIsActive(true);
                        carrierDetails.setIsDeleted(false);
                        carrierDetails.setCarrierId(carrierId);
                        // Save each carrierDetails
                        list.add(carrierDetails);
                        carrierDetailsRepository.save(carrierDetails);
                    }

                    return "Data saved successfully";
                } else {
                    return "No carrier IDs provided";
                }
            }
        } catch (Exception e) {
            e.printStackTrace(); // Replace with proper logging if needed
            return "Error occurred while saving/updating carrier details: " + e.getMessage();
        }
    }

    @Override
    public Object saveOrUpdateBrandDetails(BrandDetailsRequest brandDetailsRequest) throws Exception {
        if(brandDetailsRepository.existsById(brandDetailsRequest.getBrandDetailsId())){
            BrandDetails brandDetails = brandDetailsRepository.findById(brandDetailsRequest.getBrandDetailsId()).get();
            brandDetails.setBrandName(brandDetailsRequest.getBrandName());
            brandDetails.setColor(brandDetailsRequest.getColor());

            //brandDetails.setLogo(brandDetailsRequest.getLogo());
            brandDetails.setLink(brandDetailsRequest.getLink());
            brandDetailsRepository.save(brandDetails);
            return "updated successfully";
        }else {
            BrandDetails brandDetails = new BrandDetails();
            brandDetails.setBrandName(brandDetailsRequest.getBrandName());
            brandDetails.setColor(brandDetailsRequest.getColor());
            brandDetails.setLink(brandDetailsRequest.getLink());
            brandDetails.setCompanyId(getUserFromToken.getUserFromToken().getCompanyId());
            brandDetails.setIsActive(true);
            brandDetails.setIsDeleted(false);
            brandDetailsRepository.save(brandDetails);
            brandDetails.setLogo(this.brandLogoImage(brandDetailsRequest.getLogo()));
            brandDetailsRepository.save(brandDetails);
            return "save data successfully";
        }

    }

    @Override
    public String brandLogoImage(MultipartFile logo) throws Exception {
        if(logo!=null){
            String storagePath = "C:\\Users\\oms-desktop\\Pictures\\shipmentImages";
            String originalFileName = logo.getOriginalFilename();
            Path path = Paths.get(storagePath,originalFileName);
            Files.write(path, logo.getBytes());
            return originalFileName;
        }else {
            throw new Exception("file not found");
        }
    }

    @Override
    public Object saveOrUpdateBusinessDetails(BusinessDetailsRequest businessDetailsRequest) throws Exception {
        if (businessDetailsRepository.existsById(businessDetailsRequest.getBusinessDetailsId())){
            BusinessDetails businessDetails = businessDetailsRepository.findById(businessDetailsRequest.getBusinessDetailsId()).get();
            saveUpdateBusinessDetails(businessDetails,businessDetailsRequest);
            return "update Data Successfully";
        }else {
            BusinessDetails businessDetails = new BusinessDetails();
            saveUpdateBusinessDetails(businessDetails,businessDetailsRequest);
            return "save Data Successfully";
        }
    }

    @Override
    public Object getBusinessDetailsById(Long businessDetailsId) throws Exception {
        if (businessDetailsRepository.existsById(businessDetailsId)){
            BusinessDetails businessDetails = businessDetailsRepository.findById(businessDetailsId).get();
            return businessDetails;
        }else {
            throw new Exception("id not found");
        }
    }

    private void saveUpdateBusinessDetails(BusinessDetails businessDetails, BusinessDetailsRequest businessDetailsRequest) throws Exception {
        businessDetails.setEORINumber(businessDetailsRequest.getEORINumber());
        businessDetails.setVATNumber(businessDetailsRequest.getVATNumber());
        businessDetails.setLossNumber(businessDetailsRequest.getLossNumber());
        businessDetails.setFullName(businessDetailsRequest.getFullName());
        businessDetails.setInitials(businessDetailsRequest.getInitials());
        businessDetails.setCompanyId(getUserFromToken.getUserFromToken().getCompanyId());
        businessDetailsRepository.save(businessDetails);
        businessDetails.setFile(this.brandLogoImage(businessDetailsRequest.getFile()));
        businessDetailsRepository.save(businessDetails);

    }
}
