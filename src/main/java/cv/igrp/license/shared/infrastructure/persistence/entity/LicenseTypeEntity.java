/* THIS FILE WAS GENERATED AUTOMATICALLY BY iGRP STUDIO. */
/* DO NOT MODIFY IT BECAUSE IT COULD BE REWRITTEN AT ANY TIME. */

package cv.igrp.license.shared.infrastructure.persistence.entity;

import cv.igrp.license.shared.config.AuditEntity;
import cv.igrp.framework.stereotype.IgrpEntity;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.envers.Audited;
import java.util.UUID;
import jakarta.validation.constraints.NotBlank;
import java.math.BigDecimal;
import java.util.List;

@Audited
@Getter
@Setter
@IgrpEntity
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "t_license_type")
public class LicenseTypeEntity extends AuditEntity {

    @Id
    @Column(name = "id", unique = true, nullable = false)
    private UUID id;

  
    @NotBlank(message = "name is mandatory")
    @Column(name="name", nullable = false)
    private String name;

  
    @Column(name="description")
    private String description;

  
    @Column(name="code", unique = true)
    private String code;

  
    @Column(name="licensing_model_key")
    private String licensingModelKey;

  
    @Column(name="validity_period")
    private Integer validityPeriod;

  
    @Column(name="validity_unit_key")
    private String validityUnitKey;

  
    @Column(name="renewable")
    private boolean renewable;

  
    @Column(name="auto_renewal")
    private boolean autoRenewal;

  
    @Column(name="requires_inspection")
    private boolean requiresInspection;

  
    @Column(name="requires_public_consultation")
    private boolean requiresPublicConsultation;

  
    @Column(name="max_processing_days")
    private Integer maxProcessingDays;

  
    @Column(name="has_fees")
    private boolean hasFees;

  
    @Column(name="base_fee")
    private BigDecimal baseFee;

  
    @Column(name="currency_code")
    private String currencyCode;

  
    @Column(name="active")
    private boolean active;

  
    @Lob
    @Column(name="metadata", columnDefinition="TEXT")
    private String metadata;

  
    @Column(name="sort_order")
    private Integer sortOrder;

     @ManyToOne(fetch = FetchType.LAZY)
   @JoinColumn(name = "category_id")
   private CategoryEntity categoryId;


}