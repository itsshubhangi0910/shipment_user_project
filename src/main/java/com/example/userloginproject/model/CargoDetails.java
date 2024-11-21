package com.example.userloginproject.model;

import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.List;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Table(name = "sp_cargo_details")
public class CargoDetails {

    @Id

    @GeneratedValue(strategy = GenerationType.IDENTITY)

    @Column(name = "cargo_id")
    private Long cargoId;

    @Column(name = "company_id")
    private Long companyId;

    @Column(name = "shipment_id")
    private Long shipmentId;

    @Column(name = "description_of_goods")
    private String descriptionOfGoods;

    @Column(name = "shipment_reference_id")
    private Long shipmentReferenceId;

    @Column(name = "package_type")
    private String packageType;

    @Column(name = "is_deleted")
    private Boolean isDeleted = false;

    @Column(name = "is_active")
    private Boolean isActive = true;

    @CreationTimestamp
    @Column(name = "created_at")
    private LocalDateTime createdAt;

    @UpdateTimestamp
    @Column(name = "updated_at")
    private LocalDateTime updatedAt;

    @Transient
    private List<CargoBoxDetails> cargoBoxDetailsList;

}
