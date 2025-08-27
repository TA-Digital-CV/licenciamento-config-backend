package cv.igrp.license.configuration.application.queries;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import cv.igrp.framework.core.domain.QueryHandler;
import cv.igrp.framework.stereotype.IgrpQueryHandler;
import org.springframework.context.event.EventListener;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

import cv.igrp.license.configuration.application.dto.LicenseTypeResponseDTO;

@Component
public class GetLicenseTypeByIdQueryHandler implements QueryHandler<GetLicenseTypeByIdQuery, ResponseEntity<LicenseTypeResponseDTO>>{

  private static final Logger LOGGER = LoggerFactory.getLogger(GetLicenseTypeByIdQueryHandler.class);


  public GetLicenseTypeByIdQueryHandler() {

  }

   @IgrpQueryHandler
  public ResponseEntity<LicenseTypeResponseDTO> handle(GetLicenseTypeByIdQuery query) {
    // TODO: Implement the query handling logic here
    return null;
  }

}