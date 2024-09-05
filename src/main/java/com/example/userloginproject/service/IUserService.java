package com.example.userloginproject.service;

import com.example.userloginproject.model.request.StoreDetailsRequest;
import com.example.userloginproject.model.request.UserRequest;
import org.springframework.data.domain.Pageable;

public interface IUserService {
    Object saveOrUpdateUser(UserRequest userRequest);

    Object getByUSerId(Long userId) throws Exception;

    Object getAllUser(Pageable pageable);

    Object deleteUserById(Long userId) throws Exception;

    Object saveOrUpdateDetails(StoreDetailsRequest storeDetailsRequest);

    Object getAllStoreDetailsByCompanyId(Long companyId) throws Exception;
}
