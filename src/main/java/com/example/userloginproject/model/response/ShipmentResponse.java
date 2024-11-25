package com.example.userloginproject.model.response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ShipmentResponse {

    private Long shipmentId;
    private Long companyId;
    private String shipmentFromCountry;
    private String shipmentFromCompanyName;
    private String shipmentFromContactPersonName;
    private String shipmentFromAddress;
    private String shippingFromAddressLine2;
    private String shippingFromCity;
    private String shippingFromState;
    private String shippingFromZip;
    private String shipmentFromPhoneNumber;
    private String shippingFromEmailAddress;
    private String shipmentToCountry;
    private String shipmentToCompanyName;
    private String shipmentToContactPersonName;
    private String shipmentToAddress;
    private String shippingToAddressLine2;
    private String shippingToCity;
    private String shippingToState;
    private String shippingToZip;
    private String shipmentToPhoneNumber;
    private String shippingToEmailAddress;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;



}
