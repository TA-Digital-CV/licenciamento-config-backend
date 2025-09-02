package cv.igrp.license.configuration.application.commands;

import cv.igrp.framework.core.domain.CommandHandler;
import cv.igrp.framework.stereotype.IgrpCommandHandler;
import cv.igrp.license.configuration.domain.repository.SectorRepository;
import cv.igrp.license.configuration.domain.valueobject.SectorId;
import cv.igrp.license.configuration.infrastructure.mappers.SectorMapper;
import cv.igrp.license.shared.domain.exceptions.IgrpResponseStatusException;
import cv.igrp.license.shared.domain.valueobject.Metadata;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import cv.igrp.license.configuration.application.dto.SectorResponseDTO;

@Component
public class UpdateSectorCommandHandler implements CommandHandler<UpdateSectorCommand, ResponseEntity<SectorResponseDTO>> {

   private static final Logger LOGGER = LoggerFactory.getLogger(UpdateSectorCommandHandler.class);

  private final SectorRepository sectorRepository;
  private final SectorMapper sectorMapper;

  public UpdateSectorCommandHandler(SectorRepository sectorRepository, SectorMapper sectorMapper) {

    this.sectorRepository = sectorRepository;
    this.sectorMapper = sectorMapper;
  }

   @IgrpCommandHandler
   public ResponseEntity<SectorResponseDTO> handle(UpdateSectorCommand command) {
     var dto = command.getSectorrequest();
     var sectorId = command.getSectorId();

     if (sectorId == null || sectorId.isBlank()) {
       throw IgrpResponseStatusException.badRequest("The field <sectorId> is required");
     }

     var existingSector = sectorRepository.findById(SectorId.from(sectorId))
         .orElseThrow(() -> IgrpResponseStatusException.notFound(
             "Sector with id '" + sectorId + "' not found"));

     /* if (!existingSector.getCode().equals(dto.getCode()) && sectorRepository.existsByCode(dto.getCode())) {
         throw IgrpResponseStatusException.badRequest(
                 "Sector with code '" + dto.getCode() + "' already exists");
      }*/

     // Atualizar metadata
     var metadata = Metadata.fromMap(dto.getMetadata());

     // 5. Atualizar dados do setor
     existingSector.atualizar(
         dto.getName(),
         dto.getDescription(),
         dto.getSectorTypeKey(),
         dto.getCode(),
         dto.getSortOrder(),
         metadata
     );


     var savedSector = sectorRepository.save(existingSector);

     var responseDTO = sectorMapper.toResponseDTO(savedSector);

     return ResponseEntity.ok(responseDTO);
   }

}
