package cv.igrp.license.configuration.infrastructure.mappers;


import cv.igrp.license.configuration.domain.models.Category;
import cv.igrp.license.configuration.domain.valueobject.CategoryId;
import cv.igrp.license.shared.infrastructure.persistence.entity.CategoryEntity;
import jakarta.persistence.EntityManager;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class CategoryMapper {

    private final MetadataMapper metadataMapper;

    private final EntityManager entityManager;

    private final SectorMapper sectorMapper;

    public CategoryMapper(MetadataMapper metadataMapper, EntityManager entityManager, SectorMapper sectorMapper) {
        this.metadataMapper = metadataMapper;
        this.entityManager = entityManager;
        this.sectorMapper = sectorMapper;
    }

    public Category toDomain(CategoryEntity entity) {
        if (entity == null) return null;

        // Mapear filhos recursivamente
        List<Category> children = entity.getChildrens() != null
                ? entity.getChildrens().stream()
                .map(this::toDomain)
                .toList()
                : new ArrayList<>();

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
                entity.getParentId() != null ? CategoryId.from(entity.getParentId().getId()) : null,
                entity.getSectorId() != null ? sectorMapper.toDomain(entity.getSectorId()) : null,
                children
        );
    }

    /*public CategoryEntity toEntity(Category domain) {
        CategoryEntity entity = new CategoryEntity();
        entity.setId(domain.getId().getValor());
        entity.setName(domain.getName());
        entity.setDescription(domain.getDescription());
        entity.setCode(domain.getCode());
        entity.setActive(domain.isAtivo());
        entity.setLevel(domain.getLevel());
        entity.setSortOrder(domain.getSortOrder());
        entity.setMetadata(metadataMapper.toEntity(domain.getMetadata()));
        entity.setPath(domain.getPath());

        if (domain.getParentId() != null) {
            entity.setParentId(entityManager
                    .getReference(CategoryEntity.class, domain.getParentId().getValor()));
        }

        if (domain.getSectorId() != null) {
            entity.setSectorId(entityManager
                    .getReference(SectorEntity.class, domain.getSectorId().getValor()));
        }

        return entity;
    }*/

    public CategoryEntity toEntity(Category domain) {
        if (domain == null) return null;

        CategoryEntity entity = new CategoryEntity();
        entity.setId(domain.getId().getIdentificador().getValor());
        entity.setName(domain.getName());
        entity.setDescription(domain.getDescription());
        entity.setCode(domain.getCode());
        entity.setActive(domain.isAtivo());
        entity.setLevel(domain.getLevel());
        entity.setSortOrder(domain.getSortOrder());
        entity.setMetadata(metadataMapper.toEntity(domain.getMetadata()));
        entity.setPath(domain.getPath());

        if (domain.getParentId() != null) {
            CategoryEntity parentEntity = new CategoryEntity();
            parentEntity.setId(domain.getParentId().getIdentificador().getValor());
            entity.setParentId(parentEntity);
        }

        if (domain.getSector() != null) {
            entity.setSectorId(sectorMapper.toEntity(domain.getSector()));
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


    /*public CategoryResponseDTO toDTO(Category category) {
        if (category == null) return null;

        CategoryResponseDTO dto = new CategoryResponseDTO();
        dto.setId(category.getId().getStringValor());
        dto.setCode(category.getCode());
        dto.setName(category.getName());
        dto.setLevel(category.getLevel());
        dto.setPath(category.getPath());

        if (category.getSector() != null) {
            dto.setSectorId(category.getSector().getId().getStringValor());
            dto.setSectorName(category.getSector().getName());
        }

        if (category.getChildren() != null && !category.getChildren().isEmpty()) {
            List<CategoryResponseDTO> childrenDTOs = category.getChildren().stream()
                    .map(this::toDTO)  // mapeamento recursivo
                    .toList();
            dto.setChildren(childrenDTOs);
        }

        return dto;
    }*/

}
