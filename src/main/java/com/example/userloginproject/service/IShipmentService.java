package com.example.userloginproject.service;

import com.example.userloginproject.model.request.CargoBoxDetailsRequest;
import com.example.userloginproject.model.request.CargoDetailsRequest;
import com.example.userloginproject.model.request.ShipmentFilterRequest;
import com.example.userloginproject.model.request.ShipmentRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.web.multipart.MultipartFile;

public interface IShipmentService {
    Object saveOrUpdateShipment(ShipmentRequest shipmentRequest);

    Object getShipmentById(Long shipmentId) throws Exception;

    Object saveOrUpdateCargoDetails(CargoDetailsRequest cargoDetailsRequest);

    Object getCargoDetailsById(Long cargoId) throws Exception;

    Object saveOrUpdateCargoBoxDetails(CargoBoxDetailsRequest cargoBoxDetailsRequest);

    Object getCargoBoxDetailsById(Long cargoBoxId) throws Exception;

    String uploadImage(MultipartFile file);

    Object getAllCargoDetails();

    Object getShippingDetailsByCompanyId(Long companyId) throws Exception;

    //Object getAllSearchByShipmentDetails(List<String> shipmentToCompanyName, List<String> shipmentToCountry, List<String> shipmentFromCountry,Pageable pageable);

    Object getAllSearchByShipmentDetails(ShipmentFilterRequest shipmentFilterRequest, Pageable pageable);
}
