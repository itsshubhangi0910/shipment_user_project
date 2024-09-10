package com.example.userloginproject.model.request;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import org.springframework.web.multipart.MultipartFile;

@Getter
@Setter
@AllArgsConstructor
public class BusinessDetailsRequest {
    private Long businessDetailsId;
    private Long companyId;
    private String EORINumber;
    private String VATNumber;
    private String lossNumber;
    private String fullName;
    private String initials;
    private MultipartFile file;
}
