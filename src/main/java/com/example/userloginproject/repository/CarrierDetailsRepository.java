package com.example.userloginproject.repository;

import com.example.userloginproject.model.CarrierDetails;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
public interface CarrierDetailsRepository extends JpaRepository<CarrierDetails,Long> {
    boolean existsByCompanyId(Long companyId);

    @Transactional
    @Modifying
    void deleteAllByCompanyId(Long companyId);
}
