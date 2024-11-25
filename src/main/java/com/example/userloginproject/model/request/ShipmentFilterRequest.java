package com.example.userloginproject.model.request;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;


@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class ShipmentFilterRequest {
    private String shipmentToCountry;
    private String shipmentFromCountry;
    private String shipmentToCompanyName;
    private String startDate;
    private String endDate;
    private String sortBy;
    private String orderBy;
    private Integer pageNo;
    private Integer pageSize;


}
