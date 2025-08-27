/* THIS FILE WAS GENERATED AUTOMATICALLY BY iGRP STUDIO. */
/* DO NOT MODIFY IT BECAUSE IT COULD BE REWRITTEN AT ANY TIME. */

package cv.igrp.license.configuration.application.dto;

import cv.igrp.framework.stereotype.IgrpDTO;
import jakarta.validation.*;
import jakarta.validation.constraints.*;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;
import cv.igrp.license.configuration.application.dto.CategoryResponseDTO;
import java.util.ArrayList;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor


@IgrpDTO
public class CategoryResponseDTO  {

  
  
  private String id ;
  
  
  private String code ;
  
  
  private String name ;
  
  
  private String sectorId ;
  
  
  private String sectorName ;
  
  
  private Integer level ;
  
  
  private String path ;
  
  @Valid
  private List<CategoryResponseDTO> children = new ArrayList<>();

}