package cv.igrp.license.configuration.application.queries;

import cv.igrp.license.configuration.domain.repository.SectorRepository;
import cv.igrp.license.configuration.domain.valueobject.SectorId;
import cv.igrp.license.configuration.infrastructure.mappers.SectorMapper;
import cv.igrp.license.shared.domain.exceptions.IgrpResponseStatusException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import cv.igrp.framework.core.domain.QueryHandler;
import cv.igrp.framework.stereotype.IgrpQueryHandler;
import org.springframework.context.event.EventListener;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

import cv.igrp.license.configuration.application.dto.SectorResponseDTO;

@Component
public class GetSectorByIdQueryHandler implements QueryHandler<GetSectorByIdQuery, ResponseEntity<SectorResponseDTO>>{

  private static final Logger LOGGER = LoggerFactory.getLogger(GetSectorByIdQueryHandler.class);

  private final SectorRepository sectorRepository;
  private final SectorMapper sectorMapper;


  public GetSectorByIdQueryHandler(SectorRepository sectorRepository, SectorMapper sectorMapper) {

    this.sectorRepository = sectorRepository;
    this.sectorMapper = sectorMapper;
  }

   @IgrpQueryHandler
  public ResponseEntity<SectorResponseDTO> handle(GetSectorByIdQuery query) {
     var sectorId = query.getSectorId();

     if (sectorId == null || sectorId.isBlank()) {
       throw IgrpResponseStatusException.badRequest("The field <sectorId> is required");
     }

     var sector = sectorRepository.findById(SectorId.from(sectorId))
         .orElseThrow(() -> IgrpResponseStatusException.notFound(
             "Sector with id '" + sectorId + "' not found"));

     var responseDTO = sectorMapper.toResponseDTO(sector);

     return ResponseEntity.ok(responseDTO);
  }

}
