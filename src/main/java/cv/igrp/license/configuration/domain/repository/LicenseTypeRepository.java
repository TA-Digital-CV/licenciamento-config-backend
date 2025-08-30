package cv.igrp.license.configuration.domain.repository;

import cv.igrp.license.configuration.domain.filter.LicenseTypeFilter;
import cv.igrp.license.configuration.domain.models.LicenseType;
import cv.igrp.license.configuration.domain.valueobject.LicenseTypeId;
import cv.igrp.license.shared.domain.valueobject.Identificador;

import java.util.List;
import java.util.Optional;

public interface LicenseTypeRepository {

    LicenseType save(LicenseType licenseType);

    Optional<LicenseType> findById(LicenseTypeId id);

    List<LicenseType> findAll();

    List<LicenseType> findAll(LicenseTypeFilter filter);

    void delete(LicenseTypeId id);

    boolean existsByCode(String code);

  boolean existsByCodeAndActive(String code);

}
