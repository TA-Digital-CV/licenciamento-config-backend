package cv.igrp.license.configuration.infrastructure.mappers;


import cv.igrp.license.configuration.application.dto.SectorResponseDTO;
import cv.igrp.license.configuration.domain.models.Sector;
import cv.igrp.license.configuration.domain.valueobject.SectorId;
import cv.igrp.license.shared.domain.valueobject.Identificador;
import cv.igrp.license.shared.infrastructure.persistence.entity.CategoryEntity;
import cv.igrp.license.shared.infrastructure.persistence.entity.SectorEntity;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class SectorMapper {

  private final MetadataMapper metadataMapper;

  private final CategoryMapper categoryMapper;

  public SectorMapper(MetadataMapper metadataMapper, CategoryMapper categoryMapper) {
    this.metadataMapper = metadataMapper;
    this.categoryMapper = categoryMapper;
  }

  public Sector toDomain(SectorEntity entity) {

    List<CategoryEntity> categoriesEntities = entity.getCategories();

    var categories = categoriesEntities != null
        ? categoriesEntities.stream()
        .map(categoryMapper::toDomain)
        .collect(Collectors.toList())
        : null;

    return Sector.reconstruir(
        SectorId.from(entity.getId()),
        entity.getName(),
        entity.getDescription(),
        entity.getSectorTypeKey(),
        entity.getCode(),
        entity.isActive(),
        entity.getSortOrder(),
        metadataMapper.toDomain(entity.getMetadata()),
        categories
    );
  }

  public SectorEntity toEntity(Sector domain) {
    SectorEntity entity = new SectorEntity();
    entity.setId(domain.getId().getIdentificador().getValor());
    entity.setName(domain.getName());
    entity.setDescription(domain.getDescription());
    entity.setSectorTypeKey(domain.getSectorTypeKey());
    entity.setCode(domain.getCode());
    entity.setActive(domain.isAtivo());
    entity.setSortOrder(domain.getSortOrder());
    entity.setMetadata(metadataMapper.toEntity(domain.getMetadata()));
    return entity;
  }

  public SectorResponseDTO toResponseDTO(Sector sector) {
    if (sector == null) return null;

    SectorResponseDTO dto = new SectorResponseDTO();
    dto.setId(sector.getId().getIdentificador().getStringValor());
    dto.setName(sector.getName());
    dto.setDescription(sector.getDescription());
    dto.setCode(sector.getCode());
    dto.setSectorType(sector.getSectorTypeKey());
    dto.setActive(sector.isActive());
    dto.setSortOrder(sector.getSortOrder());
    dto.setMetadata(sector.getMetadata().getValores());

    return dto;
  }

}
