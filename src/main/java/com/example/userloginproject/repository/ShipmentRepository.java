package com.example.userloginproject.repository;

import com.example.userloginproject.model.Shipment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Map;

@Repository
public interface ShipmentRepository extends JpaRepository<Shipment,Long> {
    boolean existsByCompanyId(Long companyId);

    Shipment findByCompanyId(Long companyId);
}
