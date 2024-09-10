package com.example.userloginproject.model.request;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import org.springframework.web.multipart.MultipartFile;

@Getter
@Setter
@AllArgsConstructor
public class BrandDetailsRequest {
    private Long brandDetailsId;
    private String brandName;
    private String color;
    private MultipartFile logo;
    private String link;

}
