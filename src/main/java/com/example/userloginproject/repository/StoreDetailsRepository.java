package com.example.userloginproject.repository;

import com.example.userloginproject.model.StoreDetails;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface StoreDetailsRepository extends JpaRepository<StoreDetails,Long> {
    boolean existsByCompanyId(Long storeId);

    StoreDetails findByCompanyId(Long storeId);
}
