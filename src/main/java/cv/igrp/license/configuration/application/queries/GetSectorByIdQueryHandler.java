package cv.igrp.license.configuration.application.queries;

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


  public GetSectorByIdQueryHandler() {

  }

   @IgrpQueryHandler
  public ResponseEntity<SectorResponseDTO> handle(GetSectorByIdQuery query) {
    // TODO: Implement the query handling logic here
    return null;
  }

}