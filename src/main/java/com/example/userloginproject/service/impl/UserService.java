package com.example.userloginproject.service.impl;

import com.example.userloginproject.config.GetUserFromToken;
import com.example.userloginproject.model.StoreDetails;
import com.example.userloginproject.model.User;
import com.example.userloginproject.model.request.StoreDetailsRequest;
import com.example.userloginproject.model.request.UserRequest;
import com.example.userloginproject.model.response.PageDto;
import com.example.userloginproject.repository.StoreDetailsRepository;
import com.example.userloginproject.repository.UserRepository;
import com.example.userloginproject.service.IUserService;
import com.example.userloginproject.utils.Role;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class UserService implements IUserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private StoreDetailsRepository storeDetailsRepository;

    @Autowired
    private GetUserFromToken getUserFromToken;


    @Override
    public Object saveOrUpdateUser(UserRequest userRequest) throws Exception {
        if (userRepository.existsById(userRequest.getUserId())){
            User user = userRepository.findById(userRequest.getUserId()).get();
            user.setFirstName(userRequest.getFirstName());
            user.setLastName(userRequest.getLastName());
            List<Long> userIds = new ArrayList<>();
            userIds.add(userRequest.getUserId());
            if (userRepository.existsByMobileNoAndUserIdNotIn(userRequest.getMobileNo(),userIds)){
                throw new Exception("mobile number already exists");
            }else {
                user.setMobileNo(userRequest.getMobileNo());
            }
            if (userRepository.existsByEmailAndUserIdNotIn(userRequest.getEmail(),userIds)){
                throw new Exception("email already exists");
            }else {
                user.setEmail(userRequest.getEmail());
            }
            user.setPassword(userRequest.getPassword());
            user.setRole(Role.SHIPPER);
            userRepository.save(user);
            return "user updated successfully";
        }else {
            User user = new User();
            user.setFirstName(userRequest.getFirstName());
            user.setLastName(userRequest.getLastName());
            if (userRepository.existsByMobileNo(userRequest.getMobileNo())){
                throw new Exception("mobile number already exists");
            }else {
                user.setMobileNo(userRequest.getMobileNo());
            }
            if (userRepository.existsByEmailAndIsDeleted(userRequest.getEmail(),false)){
                throw new Exception("email already exists");
            }else {
                user.setEmail(userRequest.getEmail());
            }
            user.setPassword(userRequest.getPassword());
            user.setRole(Role.SHIPPER);

            user.setIsActive(true);
            user.setIsDeleted(false);
            userRepository.save(user);
            user.setCompanyId(getUserFromToken.getUserFromToken().getCompanyId());
            userRepository.save(user);
            return "save data successfully";
        }
    }

    @Override
    public Object getByUSerId(Long userId) throws Exception {
        if (userRepository.existsById(userId)){
            User user = userRepository.findById(userId).get();
            return user;
        }else {
            throw new Exception("id not found");
        }

    }

    @Override
    public Object getAllUser(Pageable pageable) {
        Page<User>userPage = userRepository.getAllUser(pageable);
        return new PageDto(userPage.getContent(),userPage.getTotalElements(),userPage.getTotalPages(),userPage.getNumber());
    }

    @Override
    public Object deleteUserById(Long userId) throws Exception {
        if (userRepository.existsById(userId)){
            User user = userRepository.findById(userId).get();
            user.setIsDeleted(true);
            userRepository.save(user);
            return "deleted";
        }else{
            throw new Exception("id not found");
        }

    }

    @Override
    public Object saveOrUpdateCompanyStoreDetails(StoreDetailsRequest storeDetailsRequest) {
        if (storeDetailsRepository.existsByCompanyId(getUserFromToken.getUserFromToken().getCompanyId())){
            StoreDetails storeDetails = storeDetailsRepository.findByCompanyId(storeDetailsRequest.getStoreId());
            storeDetails.setStoreName(storeDetailsRequest.getStoreName());
            storeDetails.setIndustry(storeDetailsRequest.getIndustry());
            storeDetails.setCountry(storeDetailsRequest.getCountry());
            storeDetails.setPhoneNumber(storeDetailsRequest.getPhoneNumber());
            storeDetails.setParcelCounts(storeDetailsRequest.getParcelCounts());
            storeDetailsRepository.save(storeDetails);
            return "updated successfully";
        }else {
          //  try {
                StoreDetails storeDetails = new StoreDetails();
                storeDetails.setStoreName(storeDetailsRequest.getStoreName());
                storeDetails.setIndustry(storeDetailsRequest.getIndustry());
                storeDetails.setCountry(storeDetailsRequest.getCountry());
                storeDetails.setPhoneNumber(storeDetailsRequest.getPhoneNumber());
                storeDetails.setParcelCounts(storeDetailsRequest.getParcelCounts());
                storeDetails.setCompanyId(getUserFromToken.getUserFromToken().getCompanyId());
                storeDetails.setIsActive(true);
                storeDetails.setIsDeleted(false);
                storeDetailsRepository.save(storeDetails);

                //storeDetailsRepository.save(storeDetails);
          /*  }catch (Exception e){
                e.printStackTrace();
            }*/
            return "save successfully";
        }

    }

    @Override
    public Object getAllStoreDetailsByCompanyId(Long companyId) throws Exception {
        if (storeDetailsRepository.existsByCompanyId(companyId)){
            StoreDetails storeDetails = storeDetailsRepository.findByCompanyId(companyId);
            return storeDetails;
        }else {
            throw new Exception("id not found");
        }
    }
}
