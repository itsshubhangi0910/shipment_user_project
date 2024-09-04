package com.example.userloginproject.controller;

import com.example.userloginproject.model.request.CompanyRequest;
import com.example.userloginproject.model.response.CustomResponse;
import com.example.userloginproject.model.response.EntityResponse;
import com.example.userloginproject.service.ICompanyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/companyApi")
public class CompanyController {

    @Autowired
    private ICompanyService iUserService;

    @PostMapping("/signUpCompany")
    public ResponseEntity<?>signUpUser(@RequestBody CompanyRequest companyRequest){
        try{
            return new ResponseEntity<>(new CustomResponse(iUserService.signUpUser(companyRequest),0), HttpStatus.OK);
        }catch (Exception e){
            return new ResponseEntity<>(new EntityResponse(e.getMessage(),-1),HttpStatus.OK);
        }
    }

    @GetMapping("/loginCompany")
    public ResponseEntity<?>userLogin(@RequestParam String email,String password){
        try{
            return new ResponseEntity<>(new CustomResponse(iUserService.getLoginUser(email,password),0),HttpStatus.OK);
        }catch (Exception e){
            return new ResponseEntity<>(new EntityResponse(e.getMessage(),-1),HttpStatus.OK);
        }
    }

}
