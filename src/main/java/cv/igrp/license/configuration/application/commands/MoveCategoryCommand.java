package cv.igrp.license.configuration.application.commands;

import cv.igrp.framework.core.domain.Command;
import jakarta.validation.constraints.*;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;
import cv.igrp.license.configuration.application.dto.MoveCategoryDTO;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class MoveCategoryCommand implements Command {

  
  private MoveCategoryDTO movecategory;
  @NotBlank(message = "The field <categoryId> is required")
  private String categoryId;

}