package cv.igrp.license.configuration.domain.filter;

import cv.igrp.license.shared.domain.valueobject.Identificador;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class CategoryFilter {

    private Identificador sectorId;
    private Identificador parentId;
    private Integer level;
    private String name;
    private String code;
    private boolean active;
    private Integer pageNumber;
    private Integer pageSize;
}
