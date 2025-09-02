package cv.igrp.license.configuration.application.queries;

import cv.igrp.framework.core.domain.Query;
import jakarta.validation.constraints.*;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;


@Data
@NoArgsConstructor
@AllArgsConstructor
public class GetListLicenseTypesQuery implements Query {

  @NotBlank(message = "The field <categoryId> is required")
  private String categoryId;
  @NotBlank(message = "The field <licensingModel> is required")
  private String licensingModel;
  @NotNull(message = "The field <active> is required")
  private boolean active;
  @NotNull(message = "The field <renewable> is required")
  private boolean renewable;
  @NotBlank(message = "The field <name> is required")
  private String name;
  @NotBlank(message = "The field <code> is required")
  private String code;
  @NotBlank(message = "The field <pageNumber> is required")
  private String pageNumber;
  @NotBlank(message = "The field <pageSize> is required")
  private String pageSize;

}