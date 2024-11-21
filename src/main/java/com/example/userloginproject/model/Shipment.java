package com.example.userloginproject.model;

import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Table(name = "sp_shipment")
public class Shipment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)

    @Column(name = "shipment_id")
    private Long shipmentId;

    @Column(name = "company_id")
    private Long companyId;

    @Column(name = "Shipment_from_country")
    private String shipmentFromCountry;

    @Column(name = "shipment_from_company_name")
    private String shipmentFromCompanyName;

    @Column(name = "shipment_from_contact_person_name")
    private String shipmentFromContactPersonName;

    @Column(name = "shipment_from_address")
    private String shipmentFromAddress;

    @Column(name = "shipping_from_address_line2")
    private String shippingFromAddressLine2;

    @Column(name = "shipping_from_city")
    private String shippingFromCity;

    @Column(name = "shipping_from_state")
    private String shippingFromState;

    @Column(name = "shipping_from_zip")
    private String shippingFromZip;

    @Column(name = "shipping_from_phone_number")
    private String shipmentFromPhoneNumber;

    @Column(name = "shipping_from_email_address")
    private String shippingFromEmailAddress;

    @Column(name = "Shipment_to_country")
    private String shipmentToCountry;

    @Column(name = "shipment_to_company_name")
    private String shipmentToCompanyName;

    @Column(name = "shipment_to_contact_person_name")
    private String shipmentToContactPersonName;

    @Column(name = "shipment_to_address")
    private String shipmentToAddress;

    @Column(name = "shipping_to_address_line2")
    private String shippingToAddressLine2;

    @Column(name = "shipping_to_city")
    private String shippingToCity;

    @Column(name = "shipping_to_state")
    private String shippingToState;

    @Column(name = "shipping_to_zip")
    private String shippingToZip;

    @Column(name = "shipping_to_phone_number")
    private String shipmentToPhoneNumber;

    @Column(name = "shipping_to_email_address")
    private String shippingToEmailAddress;

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

}
