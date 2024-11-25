package com.example.userloginproject.repository;

import com.example.userloginproject.model.Shipment;
import com.example.userloginproject.model.response.ShipResponse;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;

@Repository
public interface ShipmentRepository extends JpaRepository<Shipment,Long> {
    boolean existsByCompanyId(Long companyId);

    Shipment findByCompanyId(Long companyId);

    @Query(value = "SELECT s from Shipment as s where \n" +
            "(COALESCE(:shipmentToCompanyName, '')='' OR TRIM(s.shipmentToCompanyName) IN (:shipmentToCompanyName)) and \n" +
            "(COALESCE(:shipmentToCountry, '')='' OR TRIM(s.shipmentToCountry) IN (:shipmentToCountry)) and \n" +
            "(COALESCE(:shipmentFromCountry, '')='' OR TRIM(s.shipmentFromCountry) IN (:shipmentFromCountry))",nativeQuery = false)
    Page<Shipment> shipmentSearching(List<String> shipmentToCompanyName, List<String> shipmentToCountry, List<String> shipmentFromCountry,Pageable pageable);

    @Query("SELECT new com.example.userloginproject.model.response.ShipResponse(s.shipmentToCountry,s.shipmentFromCountry,s.shipmentToCompanyName) from Shipment as s where " +
            " (COALESCE(:shipmentFromCountry, '') = '' OR TRIM(LOWER(s.shipmentFromCountry)) IN (:shipmentFromCountry)) AND " +
            " (COALESCE(:getShipmentToCountry, '') = '' OR TRIM(LOWER(s.shipmentToCountry)) IN (:getShipmentToCountry)) AND " +
            " (COALESCE(:shipmentToCompanyName, '') = '' OR TRIM(LOWER(s.shipmentToCompanyName)) IN (:shipmentToCompanyName)) AND " +
            " (COALESCE(:startDate,'')='' OR s.createdAt >= :startDate) AND " +
            " (COALESCE(:endDate,'')='' OR s.createdAt <= :endDate)")
    Page<ShipResponse> searchShipmentByFiltersWithDates(String shipmentFromCountry,String shipmentToCompanyName,String getShipmentToCountry, LocalDateTime startDate, LocalDateTime endDate, Pageable pageable);


    @Query(value = "SELECT s from Shipment as s where \n" +
            "(COALESCE(:shipmentToCompanyName, '')='' OR TRIM(s.shipmentToCompanyName) IN (:shipmentToCompanyName)) and \n" +
            "(COALESCE(:shipmentToCountry, '')='' OR TRIM(s.shipmentToCountry) IN (:shipmentToCountry)) and \n" +
            "(COALESCE(:shipmentFromCountry, '')='' OR TRIM(s.shipmentFromCountry) IN (:shipmentFromCountry))",nativeQuery = false)
    Page<Shipment> searchShipmentByFiltersWithoutDates(List<String> shipmentFromCountry, List<String> shipmentToCompanyName, List<String> shipmentToCountry, Pageable pageable);
}
