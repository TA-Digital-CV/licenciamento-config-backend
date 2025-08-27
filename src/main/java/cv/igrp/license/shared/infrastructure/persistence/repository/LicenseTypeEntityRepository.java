package cv.igrp.license.shared.infrastructure.persistence.repository;

import cv.igrp.license.shared.infrastructure.persistence.entity.LicenseTypeEntity;
import org.springframework.stereotype.Repository;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import java.util.List;
import java.util.Optional;
import java.util.UUID;
import org.springframework.data.repository.history.RevisionRepository;

@Repository
public interface LicenseTypeEntityRepository extends
    JpaRepository<LicenseTypeEntity, UUID>,
    JpaSpecificationExecutor<LicenseTypeEntity>,
    RevisionRepository<LicenseTypeEntity, UUID, Integer>
{

  Optional<LicenseTypeEntity> findByCode(String code);

  boolean existsByCode(String code);

  List<LicenseTypeEntity> findAllByActiveTrue();
}
