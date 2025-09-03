package cv.igrp.license.configuration.infrastructure.mappers;


import cv.igrp.license.configuration.application.dto.CategoryResponseDTO;
import cv.igrp.license.configuration.domain.models.Category;
import cv.igrp.license.configuration.domain.models.Sector;
import cv.igrp.license.configuration.domain.valueobject.CategoryId;
import cv.igrp.license.configuration.domain.valueobject.SectorId;
import cv.igrp.license.shared.infrastructure.persistence.entity.CategoryEntity;
import cv.igrp.license.shared.infrastructure.persistence.entity.LicenseTypeEntity;
import cv.igrp.license.shared.infrastructure.persistence.entity.SectorEntity;
import jakarta.persistence.EntityManager;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class CategoryMapper {

  private final MetadataMapper metadataMapper;

  private final EntityManager entityManager;

  private final LicenseTypeMapper licenseTypeMapper;


  public CategoryMapper(MetadataMapper metadataMapper, EntityManager entityManager, LicenseTypeMapper licenseTypeMapper) {
    this.metadataMapper = metadataMapper;
    this.entityManager = entityManager;
    this.licenseTypeMapper = licenseTypeMapper;
  }

  // Mapeamento interno para evitar circular dependency
  private Sector toDomainInternalMapper(SectorEntity sectorEntity) {
    if (sectorEntity == null) return null;

    return Sector.reconstruir(
        SectorId.from(sectorEntity.getId()),
        sectorEntity.getName(),
        sectorEntity.getDescription(),
        sectorEntity.getSectorTypeKey(),
        sectorEntity.getCode(),
        sectorEntity.isActive(),
        sectorEntity.getSortOrder(),
        sectorEntity.getMetadata() != null ? metadataMapper.toDomain(sectorEntity.getMetadata()) : null,
        null
    );
  }

  public Category toDomain(CategoryEntity entity) {
    if (entity == null) return null;

    // Mapear filhos recursivamente
    List<Category> children = entity.getChildrens() != null
        ? entity.getChildrens().stream()
        .map(this::toDomain)
        .toList()
        : new ArrayList<>();

    Category parent = null;
    if (entity.getParentId() != null) {

      List<LicenseTypeEntity> licenseTypeEntities = entity.getParentId().getLicencetypes();

      var licenseTypes = licenseTypeEntities != null
          ? licenseTypeEntities.stream()
          .map(licenseTypeMapper::toDomain)
          .collect(Collectors.toList())  // ← mutável
          : null;

      parent = Category.reconstruir(
          CategoryId.from(entity.getParentId().getId()),
          entity.getParentId().getName(),
          entity.getParentId().getDescription(),
          entity.getParentId().getCode(),
          entity.getParentId().isActive(),
          entity.getParentId().getLevel(),
          entity.getParentId().getSortOrder(),
          metadataMapper.toDomain(entity.getParentId().getMetadata()),
          entity.getParentId().getPath(),
          null, // Evitar recursão infinita
          entity.getParentId().getSectorId() != null ? toDomainInternalMapper(entity.getParentId().getSectorId()) : null,
          new ArrayList<>(), // Evitar recursão infinita,
          licenseTypes
      );

    }


    List<LicenseTypeEntity> licenseTypeEntities = entity.getLicencetypes();
    var licenseTypes = licenseTypeEntities != null
        ? licenseTypeEntities.stream()
        .map(licenseTypeMapper::toDomain)
        .collect(Collectors.toList())  // ← mutável
        : null;

    return Category.reconstruir(
        CategoryId.from(entity.getId()),
        entity.getName(),
        entity.getDescription(),
        entity.getCode(),
        entity.isActive(),
        entity.getLevel(),
        entity.getSortOrder(),
        metadataMapper.toDomain(entity.getMetadata()),
        entity.getPath(),
        parent,
        entity.getSectorId() != null ? toDomainInternalMapper(entity.getSectorId()) : null,
        children,
        licenseTypes
    );
  }


  public CategoryEntity toEntity(Category domain) {
    if (domain == null) return null;

    CategoryEntity entity = new CategoryEntity();
    entity.setId(domain.getId().getIdentificador().getValor());
    entity.setName(domain.getName());
    entity.setDescription(domain.getDescription());
    entity.setCode(domain.getCode().getValue());
    entity.setActive(domain.isAtivo());
    entity.setLevel(domain.getLevel());
    entity.setSortOrder(domain.getSortOrder());
    entity.setMetadata(metadataMapper.toEntity(domain.getMetadata()));
    entity.setPath(domain.getPath() != null ? domain.getPath().getValue() : null);

    if (domain.getParent() != null) {
         /* entity.setParentId(entityManager
              .getReference(CategoryEntity.class, domain.getParent().getId().getIdentificador().getValor()));*/
      entity.setParentId(this.toEntity(domain.getParent()));
    }


    if (domain.getSector() != null) {
      entity.setSectorId(entityManager
          .getReference(SectorEntity.class, domain.getSector().getId().getIdentificador().getValor()));
    }

    // filhos (recursivo)
    if (domain.getChildren() != null) {
      List<CategoryEntity> childrenEntities = domain.getChildren().stream()
          .map(this::toEntity)
          .toList();
      entity.setChildrens(childrenEntities);
    }

    return entity;
  }


  public CategoryResponseDTO toDTO(Category category) {
    if (category == null) return null;

    CategoryResponseDTO dto = new CategoryResponseDTO();
    dto.setId(category.getId().getIdentificador().getStringValor());
    dto.setCode(category.getCode().getValue());
    dto.setName(category.getName());
    dto.setLevel(category.getLevel());
    dto.setPath(category.getPath() != null ? category.getPath().getValue() : "");


    if (category.getSector() != null) {
      dto.setSectorId(category.getSector().getId().getIdentificador().getStringValor());
      dto.setSectorName(category.getSector().getName());
    }


    if (category.getChildren() != null && !category.getChildren().isEmpty()) {
      List<CategoryResponseDTO> childrenDTOs = category.getChildren().stream()
          .map(this::toDTO)  // mapeamento recursivo
          .toList();
      dto.setChildren(childrenDTOs);
    }

    dto.setMetadata(category.getMetadata().getValores());

    return dto;
  }

}
