/* THIS FILE WAS GENERATED AUTOMATICALLY BY iGRP STUDIO. */
/* DO NOT MODIFY IT BECAUSE IT COULD BE REWRITTEN AT ANY TIME. */

package cv.igrp.license.configuration.application.dto;

import cv.igrp.framework.stereotype.IgrpDTO;
import jakarta.validation.*;
import jakarta.validation.constraints.*;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;
import cv.igrp.license.configuration.application.dto.LicenseTypeResponseDTO;
import cv.igrp.license.shared.application.dto.PageDTO;
import java.util.ArrayList;
import java.util.List;
import lombok.EqualsAndHashCode;
@Data
@NoArgsConstructor
@AllArgsConstructor

@EqualsAndHashCode(callSuper = true)
@IgrpDTO
public class WrapperListLicenseTypeDTO extends PageDTO {

  
  @Valid
  private List<LicenseTypeResponseDTO> content = new ArrayList<>();

}