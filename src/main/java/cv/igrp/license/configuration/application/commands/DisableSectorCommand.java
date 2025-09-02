package cv.igrp.license.configuration.application.commands;

import cv.igrp.framework.core.domain.Command;
import jakarta.validation.constraints.*;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;


@Data
@NoArgsConstructor
@AllArgsConstructor
public class DisableSectorCommand implements Command {

  @NotBlank(message = "The field <sectorId> is required")
  private String sectorId;

}