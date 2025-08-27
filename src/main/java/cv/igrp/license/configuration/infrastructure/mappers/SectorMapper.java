package cv.igrp.license.configuration.infrastructure.mappers;


import cv.igrp.license.configuration.domain.models.Sector;
import cv.igrp.license.configuration.domain.valueobject.SectorId;
import cv.igrp.license.shared.domain.valueobject.Identificador;
import cv.igrp.license.shared.infrastructure.persistence.entity.SectorEntity;
import org.springframework.stereotype.Component;

@Component
public class SectorMapper {

    private final MetadataMapper metadataMapper;

    public SectorMapper(MetadataMapper metadataMapper) {
        this.metadataMapper = metadataMapper;
    }

    public Sector toDomain(SectorEntity entity) {
        return Sector.reconstruir(
                SectorId.from(entity.getId()),
                entity.getName(),
                entity.getDescription(),
                entity.getSectorTypeKey(),
                entity.getCode(),
                entity.isActive(),
                entity.getSortOrder(),
                metadataMapper.toDomain(entity.getMetadata())
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

   /* public SectorResponseDTO toResponseDTO(Sector sector) {
        if (sector == null) return null;

        SectorResponseDTO dto = new SectorResponseDTO();
        dto.setId(sector.getId().getStringValor());
        dto.setName(sector.getName());
        dto.setDescription(sector.getDescription());
        dto.setCode(sector.getCode());
        dto.setSectorType(sector.getSectorTypeKey());
        dto.setActive(sector.isActive());
        dto.setSortOrder(sector.getSortOrder());

        return dto;
    }*/

}
