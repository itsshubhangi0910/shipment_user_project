package com.example.userloginproject.controller;

import com.example.userloginproject.model.request.UserRequest;
import com.example.userloginproject.model.response.CustomResponse;
import com.example.userloginproject.model.response.EntityResponse;
import com.example.userloginproject.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/userApi")
public class UserController {

    @Autowired
    private IUserService iUserService;

    @PostMapping("/saveOrUpdateUser")
    public ResponseEntity<?>saveOrUpdateUser(@RequestBody UserRequest userRequest) {
        try {
            return new ResponseEntity<>(new EntityResponse(iUserService.saveOrUpdateUser(userRequest), 0), HttpStatus.OK);
        }catch (Exception e){
            return new ResponseEntity<>(new CustomResponse(e.getMessage(),-1),HttpStatus.OK);
        }
    }
    @GetMapping("/getUserById")
    public ResponseEntity<?>getByUserId(@RequestParam Long userId) {
        try {
            return new ResponseEntity<>(new EntityResponse(iUserService.getByUSerId(userId),0),HttpStatus.OK);
        }catch (Exception e){
            return new ResponseEntity<>(new CustomResponse(e.getMessage(),-1),HttpStatus.OK);
        }
    }
    @GetMapping("/getAllUser")
    public ResponseEntity<?>getAllUser(@RequestParam(value = "pageNumber",defaultValue = "0",required = false)Integer pageNumber,
                                       @RequestParam(value = "pageSize",defaultValue = "50",required = false)Integer pageSize){
        try{
            Pageable pageable= PageRequest.of(Optional.ofNullable(pageNumber).orElse(0),Optional.ofNullable(pageSize).orElse(50));
            return new ResponseEntity<>(new EntityResponse(iUserService.getAllUser(pageable),0),HttpStatus.OK);
        }catch (Exception e){
            return new ResponseEntity<>(new CustomResponse(e.getMessage(),-1),HttpStatus.OK);
        }
    }
    @DeleteMapping("/deleteUserById")
    public ResponseEntity<?>deleteUserById(@RequestParam Long userId){
        try{
            return new ResponseEntity<>(new EntityResponse(iUserService.deleteUserById(userId),0),HttpStatus.OK);
        }catch (Exception e){
            return new ResponseEntity<>(new CustomResponse(e.getMessage(),-1),HttpStatus.OK);
        }
    }


}
