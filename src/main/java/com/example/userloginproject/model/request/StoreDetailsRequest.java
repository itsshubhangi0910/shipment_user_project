package com.example.userloginproject.model.request;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class StoreDetailsRequest {
    private Long storeId;
    private String storeName;
    private String industry;
    private String country;
    private String phoneNumber;
    private String parcelCounts;

}
