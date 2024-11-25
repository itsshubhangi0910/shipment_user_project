package com.example.userloginproject.controller;

import com.example.userloginproject.model.request.CargoBoxDetailsRequest;
import com.example.userloginproject.model.request.CargoDetailsRequest;
import com.example.userloginproject.model.request.ShipmentFilterRequest;
import com.example.userloginproject.model.request.ShipmentRequest;
import com.example.userloginproject.model.response.CustomResponse;
import com.example.userloginproject.model.response.EntityResponse;
import com.example.userloginproject.service.IShipmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.Optional;

@RestController
@RequestMapping("/shipmentApi")
public class ShipmentController {

    @Autowired
    private IShipmentService iShipmentService;

    @PostMapping("/saveOrUpdateShipment")
    public ResponseEntity<?>saveOrUpdateShipment(@RequestBody ShipmentRequest shipmentRequest){
        try{
            return new ResponseEntity<>(new EntityResponse(iShipmentService.saveOrUpdateShipment(shipmentRequest),0), HttpStatus.OK);
        }catch (Exception e){
            return new ResponseEntity<>(new CustomResponse(e.getMessage(),-1),HttpStatus.OK);
        }
    }
    @GetMapping("/getShipmentById")
    public ResponseEntity<?>getShipmentById(@RequestParam Long shipmentId){
        try{
            return new ResponseEntity<>(new EntityResponse(iShipmentService.getShipmentById(shipmentId),0),HttpStatus.OK);
        }catch (Exception e){
            return new ResponseEntity<>(new CustomResponse(e.getMessage(),-1),HttpStatus.OK);
        }
    }

    @PostMapping("/saveOrUpdateCargoDetails")
    public ResponseEntity<?>saveOrUpdateCargoDetails(@RequestBody CargoDetailsRequest cargoDetailsRequest){
        try{
            return new ResponseEntity<>(new EntityResponse(iShipmentService.saveOrUpdateCargoDetails(cargoDetailsRequest),0),HttpStatus.OK);
        }catch (Exception e){
            return new ResponseEntity<>(new CustomResponse(e.getMessage(),-1),HttpStatus.OK);
        }
    }

    @GetMapping("/getCargoDetailsById")
    public ResponseEntity<?>getCargoDetailsById(@RequestParam Long cargoId){
        try{
            return new ResponseEntity<>(new EntityResponse(iShipmentService.getCargoDetailsById(cargoId),-1),HttpStatus.OK);
        }catch (Exception e){
            return new ResponseEntity<>(new CustomResponse(e.getMessage(),-1),HttpStatus.OK);
        }
    }

    @PostMapping("/saveOrUpdateCargoBoxDetails")
    public ResponseEntity<?>saveOrUpdateCargoBoxDetails(@ModelAttribute CargoBoxDetailsRequest cargoBoxDetailsRequest){
        try{
            return new ResponseEntity<>(new EntityResponse(iShipmentService.saveOrUpdateCargoBoxDetails(cargoBoxDetailsRequest),0),HttpStatus.OK);
        }catch (Exception e){
            return new ResponseEntity<>(new CustomResponse(e.getMessage(),-1),HttpStatus.OK);
        }
    }
    @GetMapping("/getCargoBoxDetailsById")
    public ResponseEntity<?>getCargoBoxDetailsById(@RequestParam Long cargoBoxId){
        try{
            return new ResponseEntity<>(new EntityResponse(iShipmentService.getCargoBoxDetailsById(cargoBoxId),0),HttpStatus.OK);
        }catch (Exception e){
            return new ResponseEntity<>(new CustomResponse(e.getMessage(),-1),HttpStatus.OK);
        }
    }
    @PostMapping("/upload-image")
    public ResponseEntity<String> uploadImage(@RequestParam("file") MultipartFile file) {
        String imageUrl = iShipmentService.uploadImage(file);
        if (imageUrl != null) {
            return ResponseEntity.ok(imageUrl);
        } else {
            return ResponseEntity.status(500).body("Failed to upload image");
        }
    }
    @GetMapping("/getAllCargoDetails")
    public ResponseEntity<?>getAllCargoDetails(){
        try{
            return new ResponseEntity<>(new EntityResponse(iShipmentService.getAllCargoDetails(),0),HttpStatus.OK);
        }catch (Exception e){
            return new ResponseEntity<>(new CustomResponse(e.getMessage(),-1),HttpStatus.OK);
        }
    }
    @GetMapping("/getShippingDetailsByCompanyId")
    public ResponseEntity<?>getShippingDetailsByCompanyId(@RequestParam Long companyId){
        try{
            return new ResponseEntity<>(new EntityResponse(iShipmentService.getShippingDetailsByCompanyId(companyId),0),HttpStatus.OK);
        }catch (Exception e){
            return new ResponseEntity<>(new CustomResponse(e.getMessage(),-1),HttpStatus.OK);
        }
    }

    @PostMapping("/getAllSearchByShipmentDetails")
    public ResponseEntity<?>getAllSearchByShipmentDetails(@RequestBody ShipmentFilterRequest shipmentFilterRequest){
        try{
            Pageable pageable = PageRequest.of(Optional.ofNullable(shipmentFilterRequest.getPageNo()).orElse(0), Optional.ofNullable(shipmentFilterRequest.getPageSize()).orElse(50));
            return new ResponseEntity<>(new EntityResponse(iShipmentService.getAllSearchByShipmentDetails(shipmentFilterRequest,pageable),0),HttpStatus.OK);
        }catch (Exception e){
            return new ResponseEntity<>(new CustomResponse(e.getMessage(),-1),HttpStatus.OK);
        }

    }

//    @PostMapping("/getAllSearchByShipmentDetails")
//    public ResponseEntity<?>getAllSearchByShipmentDetails(@RequestBody ShipmentFilterRequest filterRequest){
//        try{
//            Pageable pageable = PageRequest.of(Optional.ofNullable(filterRequest.getPageNo()).orElse(0), Optional.ofNullable(filterRequest.getPageSize()).orElse(50));
//            return new ResponseEntity<>(new EntityResponse(iShipmentService.getAllSearchByShipmentDetails(filterRequest.getShipmentToCompanyName(),filterRequest.getShipmentToCountry(),filterRequest.getShipmentFromCountry(),pageable),0),HttpStatus.OK);
//        }catch (Exception e){
//            return new ResponseEntity<>(new CustomResponse(e.getMessage(),-1),HttpStatus.OK);
//        }
//    }


}
