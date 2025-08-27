package cv.igrp.license.configuration.application.commands;

import cv.igrp.framework.core.domain.CommandHandler;
import cv.igrp.framework.stereotype.IgrpCommandHandler;
import cv.igrp.license.configuration.domain.models.LicenseType;
import cv.igrp.license.configuration.domain.repository.LicenseTypeRepository;
import cv.igrp.license.configuration.domain.valueobject.CategoryId;
import cv.igrp.license.configuration.infrastructure.mappers.LicenseTypeMapper;
import cv.igrp.license.shared.domain.exceptions.IgrpResponseStatusException;
import cv.igrp.license.shared.domain.valueobject.Metadata;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import cv.igrp.license.configuration.application.dto.LicenseTypeResponseDTO;

@Component
public class CreateLicenseTypeCommandHandler implements CommandHandler<CreateLicenseTypeCommand, ResponseEntity<LicenseTypeResponseDTO>> {

   private static final Logger LOGGER = LoggerFactory.getLogger(CreateLicenseTypeCommandHandler.class);

  private final LicenseTypeRepository licenseTypeRepository;
  private final LicenseTypeMapper licenseTypeMapper;

   public CreateLicenseTypeCommandHandler(LicenseTypeRepository licenseTypeRepository, LicenseTypeMapper licenseTypeMapper) {

     this.licenseTypeRepository = licenseTypeRepository;
     this.licenseTypeMapper = licenseTypeMapper;
   }

   @IgrpCommandHandler
   public ResponseEntity<LicenseTypeResponseDTO> handle(CreateLicenseTypeCommand command) {
     var dto = command.getLicensetyperequest();

     if (licenseTypeRepository.existsByCode(dto.getCode())) {
       throw IgrpResponseStatusException.badRequest(
           "LicenseType with code '" + dto.getCode() + "' already exists");
     }

     Metadata metadata = Metadata.fromMap(dto.getMetadata());

     LicenseType licenseType = LicenseType.criarNovo(
         dto.getName(),
         dto.getDescription(),
         dto.getCode(),
         dto.getLicensingModelKey(),
         dto.getValidityPeriod(),
         dto.getValidityUnitKey(),
         dto.isRenewable(),
         dto.isAutoRenewal(),
         dto.isRequiresInspection(),
         dto.isRequiresPublicConsultation(),
         dto.getMaxProcessingDays(),
         dto.isHasFees(),
         dto.getBaseFee(),
         dto.getCurrencyCode(),
         null, // todo sortOrder, pode ser calculado depois
         metadata,
         CategoryId.from(dto.getCategoryId())
     );

     LicenseType saved = licenseTypeRepository.save(licenseType);

     LicenseTypeResponseDTO responseDTO = licenseTypeMapper.toResponseDTO(saved);

     return ResponseEntity.ok(responseDTO);
   }

}
