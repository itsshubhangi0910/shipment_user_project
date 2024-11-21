package com.example.userloginproject.model;

import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import org.springframework.core.SpringVersion;
import org.springframework.web.multipart.MultipartFile;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.List;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Table(name = "sp_cargo_box_details")
public class CargoBoxDetails {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)

    @Column(name = "cargo_box_id")
    private Long cargoBoxId;

    @Column(name = "company_id")
    private Long companyId;

    @Column(name = "shipment_id")
    private Long shipmentId;

    @Column(name = "cargo_id")
    private Long cargoId;

    @Column(name = "quantity")
    private String quantity;

    @Column(name = "item_name")
    private String itemName;

    @Column(name = "weight")
    private Double weight;

    @Column(name = "length")
    private Double length;

    @Column(name = "width")
    private Double width;

    @Column(name = "heigth")
    private Double height;

    @Column(name = "value")
    private Double value;

    @Column(name = "upload_document")
    private String uploadDocument;

    @Column(name = "cargo_details_example")
    private String cargoDetailsExample;

    @Column(name = "is_deleted")
    private Boolean isDeleted=false;

    @Column(name = "is_active")
    private Boolean isActive=true;

    @UpdateTimestamp
    @Column(name = "updated_at")
    private LocalDateTime updatedAt;

    @CreationTimestamp
    @Column(name = "careated_at")
    private LocalDateTime createdAt;

//    @Transient
//    private List<CargoDetails> cargoDetailsList;
}

