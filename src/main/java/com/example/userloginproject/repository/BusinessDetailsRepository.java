package com.example.userloginproject.repository;

import com.example.userloginproject.model.BusinessDetails;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BusinessDetailsRepository extends JpaRepository<BusinessDetails,Long> {
}
