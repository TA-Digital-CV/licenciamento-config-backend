package cv.igrp.license.configuration.application.commands;

import cv.igrp.framework.core.domain.Command;
import jakarta.validation.constraints.*;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;
import cv.igrp.license.configuration.application.dto.SectorRequestDTO;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CreateSectorCommand implements Command {

  
  private SectorRequestDTO sectorrequest;

}