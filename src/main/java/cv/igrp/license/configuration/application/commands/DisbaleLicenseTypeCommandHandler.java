package cv.igrp.license.configuration.application.commands;

import cv.igrp.framework.core.domain.CommandHandler;
import cv.igrp.framework.stereotype.IgrpCommandHandler;
import cv.igrp.license.configuration.domain.repository.LicenseTypeRepository;
import cv.igrp.license.configuration.domain.valueobject.LicenseTypeId;
import cv.igrp.license.shared.domain.exceptions.IgrpResponseStatusException;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Map;

@Component
public class DisbaleLicenseTypeCommandHandler implements CommandHandler<DisbaleLicenseTypeCommand, ResponseEntity<Map<String, ?>>> {

   private static final Logger LOGGER = LoggerFactory.getLogger(DisbaleLicenseTypeCommandHandler.class);

   private final LicenseTypeRepository licenseTypeRepository;

   public DisbaleLicenseTypeCommandHandler(LicenseTypeRepository licenseTypeRepository) {

     this.licenseTypeRepository = licenseTypeRepository;
   }

   @IgrpCommandHandler
   public ResponseEntity<Map<String, ?>> handle(DisbaleLicenseTypeCommand command) {
      // TODO: Implement the command handling logic here
     var licenseTypeId = LicenseTypeId.from(command.getLicenseTypeId());

     var licenseType = licenseTypeRepository.findById(licenseTypeId).orElseThrow(
         () ->
             IgrpResponseStatusException.notFound("License Type not found with id: " + command.getLicenseTypeId())
     );

      licenseType.disable();

      licenseTypeRepository.save(licenseType);

      return ResponseEntity.ok(Map.of("message", "License Type disabled successfully"));

   }

}
