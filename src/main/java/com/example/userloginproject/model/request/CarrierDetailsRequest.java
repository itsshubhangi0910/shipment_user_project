package com.example.userloginproject.model.request;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.List;


@Getter
@Setter
@AllArgsConstructor
public class CarrierDetailsRequest {
    private Long carrierDetailsId;
    List<Long> carrierIds;
}
