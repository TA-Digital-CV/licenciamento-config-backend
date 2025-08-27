package cv.igrp.license.configuration.domain.repository;



import cv.igrp.license.configuration.domain.filter.SectorFilter;
import cv.igrp.license.configuration.domain.models.Sector;
import cv.igrp.license.shared.domain.valueobject.Identificador;

import java.util.List;
import java.util.Optional;

public interface SectorRepository {

    Sector save(Sector sector);

    Optional<Sector> findById(Identificador id);

    List<Sector> findAll();

    List<Sector> findAll(SectorFilter filter);

    void delete(Identificador id);

    boolean existsByCode(String code);
}
