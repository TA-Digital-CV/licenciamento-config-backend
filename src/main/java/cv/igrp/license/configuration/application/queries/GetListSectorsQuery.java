package cv.igrp.license.configuration.application.queries;

import cv.igrp.framework.core.domain.Query;
import jakarta.validation.constraints.*;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;


@Data
@NoArgsConstructor
@AllArgsConstructor
public class GetListSectorsQuery implements Query {

  @NotBlank(message = "The field <sectorType> is required")
  private String sectorType;
  @NotBlank(message = "The field <name> is required")
  private String name;
  @NotBlank(message = "The field <code> is required")
  private String code;
  @NotNull(message = "The field <active> is required")
  private boolean active;
  @NotBlank(message = "The field <pageNumber> is required")
  private String pageNumber;
  @NotBlank(message = "The field <pageSize> is required")
  private String pageSize;

}