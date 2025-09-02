package cv.igrp.license.configuration.application.queries;

import cv.igrp.framework.core.domain.Query;
import jakarta.validation.constraints.*;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;


@Data
@NoArgsConstructor
@AllArgsConstructor
public class GetCategoryByIdQuery implements Query {

  @NotBlank(message = "The field <categoryId> is required")
  private String categoryId;

}