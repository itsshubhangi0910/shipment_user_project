package com.example.userloginproject.model.response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;



@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ShipResponse {

    private String shipmentToCountry;
    private String shipmentFromCountry;
    private String shipmentToCompanyName;

}
