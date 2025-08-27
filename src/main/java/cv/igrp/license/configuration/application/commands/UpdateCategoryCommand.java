package cv.igrp.license.configuration.application.commands;

import cv.igrp.framework.core.domain.Command;
import jakarta.validation.constraints.*;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;
import cv.igrp.license.configuration.application.dto.CategoryRequestDTO;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UpdateCategoryCommand implements Command {

  
  private CategoryRequestDTO categoryrequest;
  @NotBlank(message = "The field <categoryId> is required")
  private String categoryId;

}