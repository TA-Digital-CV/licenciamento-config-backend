package cv.igrp.license.configuration.application.queries;

import cv.igrp.license.configuration.domain.repository.LicenseTypeRepository;
import cv.igrp.license.configuration.domain.valueobject.LicenseTypeId;
import cv.igrp.license.configuration.infrastructure.mappers.LicenseTypeMapper;
import cv.igrp.license.shared.domain.exceptions.IgrpResponseStatusException;
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

  private final LicenseTypeRepository licenseTypeRepository;
  private final LicenseTypeMapper licenseTypeMapper;

  public GetLicenseTypeByIdQueryHandler(LicenseTypeRepository licenseTypeRepository, LicenseTypeMapper licenseTypeMapper) {

    this.licenseTypeRepository = licenseTypeRepository;
    this.licenseTypeMapper = licenseTypeMapper;
  }

   @IgrpQueryHandler
  public ResponseEntity<LicenseTypeResponseDTO> handle(GetLicenseTypeByIdQuery query) {
     var licenseTypeId = LicenseTypeId.from(query.getLicenseTypeId());

     var licenseType = licenseTypeRepository.findById(licenseTypeId)
         .orElseThrow(() -> IgrpResponseStatusException.notFound(
             "LicenseType not found for id: " + query.getLicenseTypeId()));

     LicenseTypeResponseDTO dto = licenseTypeMapper.toResponseDTO(licenseType);

     return ResponseEntity.ok(dto);
  }

}
