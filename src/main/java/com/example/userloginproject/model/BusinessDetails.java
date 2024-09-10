package com.example.userloginproject.model;

import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Getter
@Setter
@Table(name = "sp_business_details")
public class BusinessDetails {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)

    @Column(name = "business_details_id")
    private Long businessDetailsId;

    @Column(name = "company_id")
    private Long companyId;

    @Column(name = "EORI_number")
    private String EORINumber;

    @Column(name = "VAT_number")
    private String VATNumber;

    @Column(name = "loss_number")
    private String lossNumber;

    @Column(name = "full_name")
    private String fullName;

    @Column(name = "initials")
    private String initials;

    @Column(name = "file")
    private String file;

    @Column(name = "is_deleted")
    private Boolean isDeleted=false;

    @Column(name = "is_active")
    private  Boolean isActive=true;

    @CreationTimestamp
    @Column(name = "created_at")
    private LocalDateTime createdAt;

    @UpdateTimestamp
    @Column(name = "updated_at")
    private LocalDateTime updatedAt;

}
