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
@Table(name = "sp_carrier_details")
public class CarrierDetails {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)

    @Column(name = "carrier_details_id")
    private Long carrierDetailsId;

    @Column(name = "carrierId")
    private Long carrierId;

    @Column(name = "companyId")
    private Long companyId;

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
