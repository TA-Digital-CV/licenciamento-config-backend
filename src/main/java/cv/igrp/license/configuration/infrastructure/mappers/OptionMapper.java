package cv.igrp.license.configuration.infrastructure.mappers;

import cv.igrp.license.configuration.application.dto.OptionResponseDTO;
import cv.igrp.license.configuration.domain.models.Option;
import cv.igrp.license.configuration.domain.valueobject.OptionId;
import cv.igrp.license.shared.infrastructure.persistence.entity.OptionsEntity;
import org.springframework.stereotype.Component;

@Component
public class OptionMapper {
  private final MetadataMapper metadataMapper;

  public OptionMapper(MetadataMapper metadataMapper){
    this.metadataMapper = metadataMapper;
  }

  public Option toDomain(OptionsEntity entity) {
    if (entity == null) return null;

    return Option.reconstruir(
        OptionId.from(entity.getId()),
        entity.getCcode(),
        entity.getCkey(),
        entity.getCvalue(),
        entity.getLocale(),
        entity.getSortOrder(),
        entity.isActive(),
        metadataMapper.toDomain(entity.getMetadata()),
        //entity.getMetadata(),
        entity.getDescription()
    );
  }

  public OptionsEntity toEntity(Option domain) {
    if (domain == null) return null;

    OptionsEntity entity = new OptionsEntity();
    entity.setId(domain.getId().getIdentificador().getValor());
    entity.setCcode(domain.getCcode());
    entity.setCkey(domain.getCkey());
    entity.setCvalue(domain.getCvalue());
    entity.setLocale(domain.getLocale());
    entity.setSortOrder(domain.getSort_order());
    entity.setActive(domain.isAtivo());
    entity.setMetadata(metadataMapper.toEntity(domain.getMetadata()));
    //entity.setMetadata(domain.getMetadata());
    entity.setDescription(domain.getDescription());

    return entity;
  }

  public OptionResponseDTO toResponseDTO(Option option) {
    if (option == null) return null;

    OptionResponseDTO dto = new OptionResponseDTO();
    dto.setId(option.getId().getIdentificador().getStringValor());
    dto.setCcode(option.getCcode());
    dto.setCkey(option.getCkey());
    dto.setCvalue(option.getCvalue());
    dto.setLocale(option.getLocale());
    //dto.setActive(option.isAtivo());
    //dto.setMetadata(option.getMetadata());
    //dto.setSortOrder(option.getSortOrder());
    //dto.setDescription(option.getDescription());

    return dto;
  }
}
