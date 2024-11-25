package com.example.userloginproject.service.impl;

import com.example.userloginproject.config.GetUserFromToken;
import com.example.userloginproject.model.CargoBoxDetails;
import com.example.userloginproject.model.CargoDetails;
import com.example.userloginproject.model.Shipment;
import com.example.userloginproject.model.request.CargoBoxDetailsRequest;
import com.example.userloginproject.model.request.CargoDetailsRequest;
import com.example.userloginproject.model.request.ShipmentFilterRequest;
import com.example.userloginproject.model.request.ShipmentRequest;
import com.example.userloginproject.model.response.PageDto;
import com.example.userloginproject.model.response.ShipResponse;
import com.example.userloginproject.repository.CargoBoxDetailsRepository;
import com.example.userloginproject.repository.CargoDetailsRepository;
import com.example.userloginproject.repository.ShipmentRepository;
import com.example.userloginproject.service.IShipmentService;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.multipart.MultipartFile;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Service
public class ShipmentService implements IShipmentService {

    @Autowired
    private ShipmentRepository shipmentRepository;

    @Autowired
    private CargoDetailsRepository cargoDetailsRepository;

    @Autowired
    private GetUserFromToken getUserFromToken;

    @Autowired
    private CargoBoxDetailsRepository cargoBoxDetailsRepository;
    private static final String IMGBB_API_URL = "https://api.imgbb.com/1/upload";
    private static final String API_KEY = "009ed1216b5e3e2c157464b5b46f8b4a";

    @Override
    public Object saveOrUpdateShipment(ShipmentRequest shipmentRequest) {
        if (shipmentRepository.existsById(shipmentRequest.getShipmentId())){
            Shipment shipment = shipmentRepository.findById(shipmentRequest.getShipmentId()).get();
            saveOrUpdateShipment(shipment,shipmentRequest);
            return "update successfully";
        }else {
            Shipment shipment = new Shipment();
            saveOrUpdateShipment(shipment,shipmentRequest);
            shipment.setIsActive(true);
            shipment.setIsDeleted(false);
            return "save successfully";
        }
    }

    @Override
    public Object getShipmentById(Long shipmentId) throws Exception {
        if (shipmentRepository.existsById(shipmentId)){
            Shipment shipment = shipmentRepository.findById(shipmentId).get();
            return shipment;
        }else {
            throw new Exception("id not found");
        }
    }

    @Override
    public Object saveOrUpdateCargoDetails(CargoDetailsRequest cargoDetailsRequest) {
        if (cargoDetailsRepository.existsById(cargoDetailsRequest.getCargoId())){
            CargoDetails cargoDetails = cargoDetailsRepository.findById(cargoDetailsRequest.getCargoId()).get();
            saveOrUpdateCargoDetails(cargoDetails,cargoDetailsRequest);
            return "update successfully";
        }else {
            CargoDetails cargoDetails = new CargoDetails();
            saveOrUpdateCargoDetails(cargoDetails,cargoDetailsRequest);
            cargoDetails.setIsActive(true);
            cargoDetails.setIsDeleted(false);
            return "save successfully";
        }
    }

    @Override
    public Object getCargoDetailsById(Long cargoId) throws Exception {
        if (cargoDetailsRepository.existsById(cargoId)){
            CargoDetails cargoDetails = cargoDetailsRepository.findById(cargoId).get();
            return cargoDetails;
        }else {
            throw new Exception("id not found");
        }
    }

    @Override
    public Object saveOrUpdateCargoBoxDetails(CargoBoxDetailsRequest cargoBoxDetailsRequest) {
        if (cargoBoxDetailsRepository.existsById(cargoBoxDetailsRequest.getCargoBoxId())){
            CargoBoxDetails cargoBoxDetails = cargoBoxDetailsRepository.findById(cargoBoxDetailsRequest.getCargoBoxId()).get();
             saveOrUpdateCargoBoxDetails(cargoBoxDetails,cargoBoxDetailsRequest);
             return "update successfully";
        }else {
            CargoBoxDetails cargoBoxDetails = new CargoBoxDetails();
            saveOrUpdateCargoBoxDetails(cargoBoxDetails,cargoBoxDetailsRequest);
            cargoBoxDetails.setIsActive(true);
            cargoBoxDetails.setIsDeleted(false);
//            cargoBoxDetails.setUploadDocument(uploadImage(cargoBoxDetailsRequest.getUploadDocument()));
            return "save successfully";

        }
    }

    @Override
    public Object getCargoBoxDetailsById(Long cargoBoxId) throws Exception {
        if (cargoBoxDetailsRepository.existsById(cargoBoxId)){
            CargoBoxDetails cargoBoxDetails = cargoBoxDetailsRepository.findById(cargoBoxId).get();
            return cargoBoxDetails;
        }else {
            throw new Exception("id not found");
        }
    }

    @Override
    public String uploadImage(MultipartFile file) {
        try {
            // Convert MultipartFile to Base64
            String base64Image = java.util.Base64.getEncoder().encodeToString(file.getBytes());

            // Set up the request parameters
            MultiValueMap<String, Object> params = new LinkedMultiValueMap<>();
            params.add("key", API_KEY);
            params.add("image", base64Image);

            // Set up the headers
            HttpHeaders headers = new HttpHeaders();
            headers.add("Content-Type", "application/x-www-form-urlencoded");

            // Create the request entity
            HttpEntity<MultiValueMap<String, Object>> requestEntity = new HttpEntity<>(params, headers);

            // Send the request
            RestTemplate restTemplate = new RestTemplate();
            ResponseEntity<String> response = restTemplate.exchange(IMGBB_API_URL, HttpMethod.POST, requestEntity, String.class);

            // Parse the response to get the image URL
            // Assuming the response JSON contains a field "url"
            String imageUrl = parseImageUrlFromResponse(response.getBody());
            return imageUrl;

        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public Object getAllCargoDetails() {
        List<CargoDetails> cargoDetailsList = cargoDetailsRepository.findAllByIsDeleted(false);
        if (cargoDetailsList != null && !cargoDetailsList.isEmpty()) {
            for (CargoDetails cd : cargoDetailsList) {
               // cd.setCargoBoxDetailsList(cargoBoxDetailsRepository.getAllByCargoBoxIdIsDeleted(cd.getCargoId(),false));
                cd.setCargoBoxDetailsList(cargoBoxDetailsRepository.findAllByCargoIdAndIsDeleted(cd.getCargoId(),false));
            }
        }
        return cargoDetailsList;
    }

    @Override
    public Object getShippingDetailsByCompanyId(Long companyId) throws Exception {
        if (shipmentRepository.existsByCompanyId(companyId)){
            Shipment shipment=shipmentRepository.findByCompanyId(getUserFromToken.getUserFromToken().getCompanyId());
            return shipment;
        }else {
            throw new Exception("id not found");
        }

    }

//    @Override
//    public Object getAllSearchByShipmentDetails(List<String> shipmentToCompanyName, List<String> shipmentToCountry, List<String> shipmentFromCountry,Pageable pageable) {
//        List<ShipmentResponse> shipmentResponses = new ArrayList<>();
//
//        if (shipmentToCompanyName != null && !shipmentToCompanyName.isEmpty()) {
//            shipmentToCompanyName = shipmentToCompanyName.stream()
//                    .map(String::trim)
//                    .collect(Collectors.toList());
//        }
//        if (shipmentToCountry != null && !shipmentToCountry.isEmpty()) {
//            shipmentToCountry = shipmentToCountry.stream()
//                    .map(String::trim)
//                    .collect(Collectors.toList());
//        }
//        if (shipmentFromCountry != null && !shipmentFromCountry.isEmpty()) {
//            shipmentFromCountry = shipmentFromCountry.stream()
//                    .map(String::trim)
//                    .collect(Collectors.toList());
//        }
//        List<Shipment> shipments = new ArrayList<>();
//        Page<Shipment> searchingRequestPage=new PageImpl<>(shipments);
//
//        searchingRequestPage = shipmentRepository.shipmentSearching(shipmentToCompanyName, shipmentToCountry, shipmentFromCountry,pageable);
//
//        //System.out.println("searchingRequestPage size-->"+searchingRequestPage.getContent().size());
//         if (searchingRequestPage !=null){
//             for (Shipment s: searchingRequestPage){
//                 ShipmentResponse response = new ShipmentResponse();
//                 response.setShipmentId(s.getShipmentId());
//                 response.setCompanyId(s.getCompanyId());
//                 response.setShipmentFromAddress(s.getShipmentFromAddress());
//                 response.setShipmentFromCompanyName(s.getShipmentFromCompanyName());
//                 response.setShipmentFromContactPersonName(s.getShipmentFromContactPersonName());
//                 response.setShipmentFromCountry(s.getShipmentFromCountry());
//                 response.setShipmentFromPhoneNumber(s.getShipmentFromPhoneNumber());
//                 response.setShippingFromAddressLine2(s.getShippingFromAddressLine2());
//                 response.setShippingFromCity(s.getShippingFromCity());
//                 response.setShippingFromEmailAddress(s.getShippingFromEmailAddress());
//                 response.setShippingFromState(s.getShippingFromState());
//                 response.setShippingFromZip(s.getShippingFromZip());
//                 response.setShipmentToAddress(s.getShipmentToAddress());
//                 response.setShipmentToCompanyName(s.getShipmentToCompanyName());
//                 response.setShipmentToContactPersonName(s.getShipmentToContactPersonName());
//                 response.setShipmentToCountry(s.getShipmentToCountry());
//                 response.setShipmentToPhoneNumber(s.getShipmentToPhoneNumber());
//                 response.setShippingToAddressLine2(s.getShippingToAddressLine2());
//                 response.setShippingToCity(s.getShippingToCity());
//                 response.setShippingToEmailAddress(s.getShippingToEmailAddress());
//                 response.setShippingToState(s.getShippingToState());
//                 response.setShippingToZip(s.getShippingToZip());
//                 shipmentResponses.add(response);
//             }
//         }
//        return new PageDto(shipmentResponses,searchingRequestPage.getTotalElements(),searchingRequestPage.getTotalPages(),searchingRequestPage.getNumber());
//    }

    @Override
    public Object getAllSearchByShipmentDetails(ShipmentFilterRequest shipmentFilterRequest, Pageable pageable) {
        List<ShipResponse> shipmentList = new ArrayList<>();
        LocalDateTime stDate = null;
        LocalDateTime edDate = null;
        if (shipmentFilterRequest.getShipmentFromCountry() != null && !shipmentFilterRequest.getShipmentFromCountry().isEmpty()) {
            shipmentFilterRequest.getShipmentFromCountry().trim().toLowerCase();
        }

        if (shipmentFilterRequest.getShipmentToCountry() != null && !shipmentFilterRequest.getShipmentToCountry().isEmpty()) {
            shipmentFilterRequest.getShipmentToCountry().trim().toLowerCase();
        }

        if (shipmentFilterRequest.getShipmentToCompanyName() != null && !shipmentFilterRequest.getShipmentToCompanyName().isEmpty()) {
            shipmentFilterRequest.getShipmentToCompanyName().trim().toLowerCase();
        }
        if (shipmentFilterRequest.getStartDate() != null && !shipmentFilterRequest.getStartDate() .trim().isEmpty()) {
            LocalDate date = LocalDate.parse(shipmentFilterRequest.getStartDate() );//2024-11-21
            stDate = date.atStartOfDay();//00:00:00
        }
        if (shipmentFilterRequest.getEndDate()  != null && !shipmentFilterRequest.getEndDate() .trim().isEmpty()) {
            LocalDate date = LocalDate.parse(shipmentFilterRequest.getEndDate() );
            edDate = date.atStartOfDay();
        }
            Page<ShipResponse> shipmentPage = new PageImpl<>(shipmentList);

                shipmentPage = shipmentRepository.searchShipmentByFiltersWithDates(shipmentFilterRequest.getShipmentFromCountry()
                        , shipmentFilterRequest.getShipmentToCompanyName(),shipmentFilterRequest.getShipmentToCountry()
                        ,stDate, edDate,pageable);
                System.out.println("in if of st ed dates-->" + shipmentPage.getContent());

        if ("desc".equalsIgnoreCase(shipmentFilterRequest.getSortBy())) {
            List<ShipResponse> content = new ArrayList<>(shipmentPage.getContent());

            if ("shipmentToCountry".equals(shipmentFilterRequest.getOrderBy())) {
                Collections.reverse(content);
                return content;
            } else if ("shipmentFromCountry".equalsIgnoreCase(shipmentFilterRequest.getOrderBy())) {
                Collections.reverse(content);
                return content;
            } else if ("shipmentToCompanyName".equalsIgnoreCase(shipmentFilterRequest.getOrderBy())) {
                Collections.reverse(content);
                return content;
            }
            Collections.reverse(content);
            return content;
        } else {
            //return shipment.getContent();
            return new PageDto(shipmentPage.getContent(), shipmentPage.getTotalElements(), shipmentPage.getTotalPages(), shipmentPage.getNumber());
        }
    }


    private String parseImageUrlFromResponse(String response) {
        ObjectMapper objectMapper = new ObjectMapper();
        try {
            // Parse the response JSON string into a JsonNode
            JsonNode rootNode = objectMapper.readTree(response);

            // Navigate to the 'data' object

            JsonNode dataNode = rootNode.path("data");

            // Extract the 'url' field from the 'data' object
            JsonNode imageUrlNode = dataNode.path("url");

            // Return the URL as a string
            System.out.println("imageUrlNode-->" + imageUrlNode.asText());
            return imageUrlNode.asText();
        } catch (Exception e) {
            e.printStackTrace(); // Handle the exception as needed
            return null;
        }
    }

    private  void saveOrUpdateCargoBoxDetails(CargoBoxDetails cargoBoxDetails,CargoBoxDetailsRequest cargoBoxDetailsRequest){
        cargoBoxDetails.setCompanyId(getUserFromToken.getUserFromToken().getCompanyId());
        cargoBoxDetails.setShipmentId(cargoBoxDetailsRequest.getShipmentId());
        cargoBoxDetails.setCargoId(cargoBoxDetailsRequest.getCargoId());
        cargoBoxDetails.setQuantity(cargoBoxDetailsRequest.getQuantity());
        cargoBoxDetails.setItemName(cargoBoxDetailsRequest.getItemName());
        cargoBoxDetails.setLength(cargoBoxDetailsRequest.getLength());
        cargoBoxDetails.setHeight(cargoBoxDetailsRequest.getHeight());
        cargoBoxDetails.setWeight(cargoBoxDetailsRequest.getWeight());
        cargoBoxDetails.setWidth(cargoBoxDetailsRequest.getWidth());
        cargoBoxDetails.setValue(cargoBoxDetailsRequest.getValue());
        cargoBoxDetails.setUploadDocument(uploadImage(cargoBoxDetailsRequest.getUploadDocument()));
        cargoBoxDetails.setCargoDetailsExample(cargoBoxDetailsRequest.getCargoDetailsExample());
        cargoBoxDetailsRepository.save(cargoBoxDetails);
    }

    private void saveOrUpdateCargoDetails(CargoDetails cargoDetails,CargoDetailsRequest cargoDetailsRequest){
        cargoDetails.setCompanyId(getUserFromToken.getUserFromToken().getCompanyId());
        cargoDetails.setShipmentId(cargoDetailsRequest.getShipmentId());
        cargoDetails.setDescriptionOfGoods(cargoDetailsRequest.getDescriptionOfGoods());
        cargoDetails.setShipmentReferenceId(cargoDetailsRequest.getShipmentReferenceId());
        cargoDetails.setPackageType(cargoDetailsRequest.getPackageType());
        cargoDetailsRepository.save(cargoDetails);
    }

    private void saveOrUpdateShipment(Shipment shipment,ShipmentRequest shipmentRequest){
        shipment.setCompanyId(getUserFromToken.getUserFromToken().getCompanyId());
        shipment.setShipmentFromCountry(shipmentRequest.getShipmentFromCountry());
        shipment.setShipmentFromCompanyName(shipmentRequest.getShipmentFromCompanyName());
        shipment.setShipmentFromContactPersonName(shipmentRequest.getShipmentFromContactPersonName());
        shipment.setShipmentFromAddress(shipmentRequest.getShipmentFromAddress());
        shipment.setShippingFromAddressLine2(shipmentRequest.getShippingFromAddressLine2());
        shipment.setShippingFromCity(shipmentRequest.getShippingFromCity());
        shipment.setShippingFromState(shipmentRequest.getShippingFromState());
        shipment.setShippingFromZip(shipmentRequest.getShippingFromZip());
        shipment.setShipmentFromPhoneNumber(shipmentRequest.getShipmentFromPhoneNumber());
        shipment.setShippingFromEmailAddress(shipmentRequest.getShippingFromEmailAddress());
        shipment.setShipmentToCountry(shipmentRequest.getShipmentToCountry());
        shipment.setShipmentToCompanyName(shipmentRequest.getShipmentToCompanyName());
        shipment.setShipmentToContactPersonName(shipmentRequest.getShipmentToContactPersonName());
        shipment.setShipmentToAddress(shipmentRequest.getShipmentToAddress());
        shipment.setShippingToAddressLine2(shipmentRequest.getShippingToAddressLine2());
        shipment.setShippingToCity(shipmentRequest.getShippingToCity());
        shipment.setShippingToState(shipmentRequest.getShippingToState());
        shipment.setShippingToZip(shipmentRequest.getShippingToZip());
        shipment.setShipmentToPhoneNumber(shipmentRequest.getShipmentToPhoneNumber());
        shipment.setShippingToEmailAddress(shipmentRequest.getShippingToEmailAddress());
        shipmentRepository.save(shipment);
    }
}
