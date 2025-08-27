package cv.igrp.license.configuration.application.commands;

import cv.igrp.framework.core.domain.CommandHandler;
import cv.igrp.framework.stereotype.IgrpCommandHandler;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import cv.igrp.license.configuration.application.dto.LicenseTypeResponseDTO;

@Component
public class CreateLicenseTypeCommandHandler implements CommandHandler<CreateLicenseTypeCommand, ResponseEntity<LicenseTypeResponseDTO>> {

   private static final Logger LOGGER = LoggerFactory.getLogger(CreateLicenseTypeCommandHandler.class);

   public CreateLicenseTypeCommandHandler() {

   }

   @IgrpCommandHandler
   public ResponseEntity<LicenseTypeResponseDTO> handle(CreateLicenseTypeCommand command) {
      // TODO: Implement the command handling logic here
      return null;
   }

}