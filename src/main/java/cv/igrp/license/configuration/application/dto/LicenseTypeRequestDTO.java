/* THIS FILE WAS GENERATED AUTOMATICALLY BY iGRP STUDIO. */
/* DO NOT MODIFY IT BECAUSE IT COULD BE REWRITTEN AT ANY TIME. */

package cv.igrp.license.configuration.application.dto;

import cv.igrp.framework.stereotype.IgrpDTO;
import jakarta.validation.*;
import jakarta.validation.constraints.*;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;
import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;

@Data
@NoArgsConstructor
@AllArgsConstructor


@IgrpDTO
public class LicenseTypeRequestDTO  {

  
  
  private String name ;
  
  
  private String description ;
  
  
  private String code ;
  
  
  private String categoryId ;
  
  
  private String licensingModelKey ;
  
  
  private Integer validityPeriod ;
  
  
  private String validityUnitKey ;
  
  
  private boolean renewable ;
  
  
  private boolean autoRenewal ;
  
  
  private boolean requiresInspection ;
  
  
  private boolean requiresPublicConsultation ;
  
  
  private Integer maxProcessingDays ;
  
  
  private boolean hasFees ;
  
  
  private BigDecimal baseFee ;
  
  
  private String currencyCode ;
  
  
  private Map<String, ?> metadata = new HashMap<>();

}