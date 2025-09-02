package cv.igrp.license.shared.infrastructure.persistence.repository;

import cv.igrp.license.shared.infrastructure.persistence.entity.CategoryEntity;
import org.springframework.stereotype.Repository;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import java.util.List;
import java.util.Optional;
import java.util.UUID;
import org.springframework.data.repository.history.RevisionRepository;

@Repository
public interface CategoryEntityRepository extends
    JpaRepository<CategoryEntity, UUID>,
    JpaSpecificationExecutor<CategoryEntity>,
    RevisionRepository<CategoryEntity, UUID, Integer>
{

  boolean existsByCode(String code);

  boolean existsById(UUID id);

  Optional<CategoryEntity> findByCode(String code);

  boolean existsByCodeAndActive(String code, boolean active);

  List<CategoryEntity> findAllByActiveTrue();
}
