package cv.igrp.license.shared.infrastructure.persistence.repository;

import cv.igrp.license.shared.infrastructure.persistence.entity.OptionsEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;


@Repository
public interface OptionsEntityRepository extends
    JpaRepository<OptionsEntity, UUID>,
    JpaSpecificationExecutor<OptionsEntity>
{
  List<OptionsEntity> findAllByActiveTrue();

  boolean existsByCcode(String ccode);

  Optional<OptionsEntity> findByCcode(String code);

}
