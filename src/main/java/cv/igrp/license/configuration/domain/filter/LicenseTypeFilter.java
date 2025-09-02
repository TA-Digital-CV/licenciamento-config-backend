package cv.igrp.license.configuration.domain.filter;

import cv.igrp.license.configuration.domain.valueobject.CategoryId;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class LicenseTypeFilter {

  private CategoryId categoryId;
  private String licensingModel;
  private boolean active;
  private boolean renewable;
  private String name;
  private String code;
  private Integer pageNumber;
  private Integer pageSize;
}
