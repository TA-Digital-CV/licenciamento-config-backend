package cv.igrp.license.configuration.domain.filter;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class SectorFilter {

    private String sectorType;
    private String name;
    private String code;
    private boolean active;
    private Integer pageNumber;
    private Integer pageSize;
}
