package com.example.userloginproject.controller;

import com.example.userloginproject.model.request.BrandDetailsRequest;
import com.example.userloginproject.model.request.CarrierDetailsRequest;
import com.example.userloginproject.model.request.CompanyRequest;
import com.example.userloginproject.model.response.CustomResponse;
import com.example.userloginproject.model.response.EntityResponse;
import com.example.userloginproject.repository.CarrierDetailsRepository;
import com.example.userloginproject.service.ICompanyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("/companyApi")
public class CompanyController {

    @Autowired
    private ICompanyService iCompanyService;


    @PostMapping("/signUpCompany")
    public ResponseEntity<?>signUpUser(@RequestBody CompanyRequest companyRequest){
        try{
            return new ResponseEntity<>(new EntityResponse(iCompanyService.signUpUser(companyRequest),0), HttpStatus.OK);
        }catch (Exception e){
            return new ResponseEntity<>(new CustomResponse(e.getMessage(),-1),HttpStatus.OK);
        }
    }

    @GetMapping("/loginCompany")
    public ResponseEntity<?>userLogin(@RequestParam String email,String password){
        try{
            return new ResponseEntity<>(new EntityResponse(iCompanyService.getLoginUser(email,password),0),HttpStatus.OK);
        }catch (Exception e){
            return new ResponseEntity<>(new CustomResponse(e.getMessage(),-1),HttpStatus.OK);
        }
    }
    @PostMapping("/saveOrUpdateCompanyAccountDetails")
    public ResponseEntity<?> saveOrUpdateCompanyAccountDetails(@RequestBody CompanyRequest companyRequest){
        try{
            return new ResponseEntity<>(new EntityResponse(iCompanyService.saveOrUpdateCompanyAccountDetails(companyRequest),0),HttpStatus.OK);
        }catch (Exception e){
            return new ResponseEntity<>(new CustomResponse(e.getMessage(),-1),HttpStatus.OK);
        }
    }
    @PostMapping("/saveOrUpdateCompanyCarrier")
    public ResponseEntity<?>saveOrUpdateCompanyCarrier(@RequestBody CarrierDetailsRequest carrierDetailsRequest){
        try{System.out.println("in controller"+carrierDetailsRequest.getCarrierDetailsId());
            return new ResponseEntity<>(new EntityResponse(iCompanyService.saveOrUpdateCompanyCarrier(carrierDetailsRequest.getCarrierDetailsId(),carrierDetailsRequest.getCarrierIds()),0),HttpStatus.OK);
        }catch (Exception e){
            return new ResponseEntity<>(new CustomResponse(e.getMessage(),-1),HttpStatus.OK);
        }
    }
    @PostMapping("/saveOrUpdateBrandDetails")
    public ResponseEntity<?>saveOrUpdateBrandDetails(@ModelAttribute BrandDetailsRequest brandDetailsRequest){
        try{
            return new ResponseEntity<>(new EntityResponse(iCompanyService.saveOrUpdateBrandDetails(brandDetailsRequest),0),HttpStatus.OK);
        }catch (Exception e){
            return new ResponseEntity<>(new CustomResponse(e.getMessage(),-1),HttpStatus.OK);
        }
    }
    @PostMapping("/brandLogoImage")
    public ResponseEntity<?>barrierLogoImage(@ModelAttribute("logo") MultipartFile logo){
        try{
            return new ResponseEntity<>(new EntityResponse(iCompanyService.brandLogoImage(logo),0),HttpStatus.OK);
        }catch (Exception e){
            return new ResponseEntity<>(new CustomResponse(e.getMessage(),-1),HttpStatus.OK);
        }
    }

}
