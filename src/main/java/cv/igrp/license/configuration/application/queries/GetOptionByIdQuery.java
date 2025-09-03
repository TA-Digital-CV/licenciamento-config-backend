package cv.igrp.license.configuration.application.queries;

import cv.igrp.framework.core.domain.Query;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@NoArgsConstructor
@AllArgsConstructor
public class GetOptionByIdQuery implements Query {

  @NotBlank(message = "The field <optionId> is required")
  private String optionId;

}
