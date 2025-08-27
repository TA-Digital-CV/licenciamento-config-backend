package cv.igrp.license.configuration.domain.filter;

import cv.igrp.license.shared.domain.valueobject.Identificador;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class LicenseTypeFilter {

    private Identificador categoryId;
    private String licensingModel;
    private boolean active;
    private boolean renewable;
    private String name;
    private String code;
    private Integer pageNumber;
    private Integer pageSize;
}
