package com.example.userloginproject.controller;

import com.example.userloginproject.config.TokenProvider;
import com.example.userloginproject.model.Company;
import com.example.userloginproject.model.request.LoginRequest;
import com.example.userloginproject.model.response.CustomResponse;
import com.example.userloginproject.model.response.EntityResponse;
import com.example.userloginproject.model.response.LoginResponse;
import com.example.userloginproject.service.IAdminUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private IAdminUserService iAdminUserService;

    @Autowired
    private TokenProvider jwtTokenUtil;

    @PostMapping("/userRegister")
    public ResponseEntity<?> signUpUser(@RequestParam String email, @RequestParam String password) {
        try {
            return new ResponseEntity<>(new CustomResponse(iAdminUserService.register(email, password), 0), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(new EntityResponse(e.getMessage(), -1), HttpStatus.OK);
        }
    }

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody LoginRequest loginRequest) throws Exception {

        try {
            final Authentication authentication = authenticate(loginRequest.getEmail(), loginRequest.getPassword());
            final UserDetails candidateDetails = iAdminUserService.loadUserByUsername(loginRequest.getEmail());
            //Team team = iAdminUser.findByEmail(candidateDetails.getUsername());
            Company company = iAdminUserService.findByEmail(candidateDetails.getUsername());

            if (!company.getIsActive() || company.getIsDeleted()) {
                throw new Exception("User Not Active ");
            }
            String token = "";
            try {
                token = jwtTokenUtil.generateToken(authentication);
            } catch (Exception e) {
                e.printStackTrace();
            }
            LoginResponse loginResponse = new LoginResponse();
            loginResponse.setToken(token);
            loginResponse.setId(company.getId());
            loginResponse.setName(company.getName());
            //loginResponse.setMobileNo(company.getMobileNo());
            //loginResponse.setCompanyId(company.getId());

           /* if (team.getUserRole()!=null){
                if (rolePrivilegeRepository.existsById(team.getUserRole())){

                }
            }*/
           /* if (loginRequest.getUserType()!=null){
                String role=rolesRepository.getRoleName(team.getUserRole());
                if (role.trim().equalsIgnoreCase("Field Representative".trim())) {
                    System.out.println("in field representative-->");
                    if (role.trim().equalsIgnoreCase(loginRequest.getUserType().trim())) {
                        team.setIsOnline(true);
                        teamRepository.save(team);
                    }
                }else{
                    throw new Exception("You are not allowed to login..!");
                }
            }*/
            // userActivityService.saveActivity(loginResponse.getTeamId(), UserType.ADMIN, ActivityType.LOGIN, loginResponse.getName() + " Logged in.");
            return new ResponseEntity<>(new EntityResponse(loginResponse, 0), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(new EntityResponse(e.getMessage(), -1), HttpStatus.UNAUTHORIZED);

        }
    }

    private Authentication authenticate(String username, String password) throws Exception {
        try {
            return authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(username, password));
        } catch (DisabledException e) {
            throw new Exception("USER_DISABLED", e);
        } catch (BadCredentialsException e) {
            // throw new Exception("INVALID_CREDENTIALS", e);
            throw new Exception("Please Check Username and Password", e);
        }
    }




}
