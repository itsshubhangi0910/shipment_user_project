package com.example.userloginproject.repository;

import com.example.userloginproject.model.CarrierDetails;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CarrierDetailsRepository extends JpaRepository<CarrierDetails,Long> {
}
