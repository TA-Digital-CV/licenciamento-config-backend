package cv.igrp.license.configuration.application.commands;

import cv.igrp.framework.core.domain.Command;
import cv.igrp.license.configuration.application.dto.OptionRequestDTO;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UpdateOptionCommand implements Command {


  private OptionRequestDTO optionrequest;
  @NotBlank(message = "The field <optionId> is required")
  private String optionId;

}
