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
@Table(name = "sp_store_details")
public class StoreDetails {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)

    @Column(name = "store_id")
    private Long storeId;

    @Column(name = "company_id")
    private Long companyId;

    @Column(name = "store_name")
    private String storeName;

    @Column(name = "industry")
    private String industry;

    @Column(name = "country")
    private String country;

    @Column(name = "phone_number")
    private String phoneNumber;

    @Column(name = "parcel_counts")
    private String parcelCounts;

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
