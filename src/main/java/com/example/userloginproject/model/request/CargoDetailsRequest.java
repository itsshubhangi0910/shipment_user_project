package com.example.userloginproject.model.request;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;

@Getter
@Setter
@AllArgsConstructor
public class CargoDetailsRequest {
    private Long cargoId;
    private Long shipmentId;
    private String descriptionOfGoods;
    private Long shipmentReferenceId;
    private String packageType;
}
