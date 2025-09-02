package cv.igrp.license.shared.infrastructure.persistence.repository;

import cv.igrp.license.shared.infrastructure.persistence.entity.SectorEntity;
import org.springframework.stereotype.Repository;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import java.util.List;
import java.util.Optional;
import java.util.UUID;
import org.springframework.data.repository.history.RevisionRepository;

@Repository
public interface SectorEntityRepository extends
    JpaRepository<SectorEntity, UUID>,
    JpaSpecificationExecutor<SectorEntity>,
    RevisionRepository<SectorEntity, UUID, Integer>
{

  boolean existsByCode(String code);

  List<SectorEntity> findAllByActiveTrue();

  boolean existsByCodeAndActive(String code, boolean active);

}
