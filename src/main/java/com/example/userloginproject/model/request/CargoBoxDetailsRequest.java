package com.example.userloginproject.model.request;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import org.springframework.web.multipart.MultipartFile;

@Getter
@Setter
@AllArgsConstructor
public class CargoBoxDetailsRequest {
    private Long cargoBoxId;
    private Long shipmentId;
    private Long cargoId;
    private String quantity;
    private String itemName;
    private Double weight;
    private Double length;
    private Double width;
    private Double height;
    private Double value;
    private MultipartFile uploadDocument;
    private String cargoDetailsExample;

}
