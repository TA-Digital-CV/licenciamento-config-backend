package cv.igrp.license.configuration.application.commands;

import cv.igrp.framework.core.domain.CommandHandler;
import cv.igrp.framework.stereotype.IgrpCommandHandler;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import cv.igrp.license.configuration.application.dto.LicenseTypeResponseDTO;

@Component
public class UpdateLicenseTypeCommandHandler implements CommandHandler<UpdateLicenseTypeCommand, ResponseEntity<LicenseTypeResponseDTO>> {

   private static final Logger LOGGER = LoggerFactory.getLogger(UpdateLicenseTypeCommandHandler.class);

   public UpdateLicenseTypeCommandHandler() {

   }

   @IgrpCommandHandler
   public ResponseEntity<LicenseTypeResponseDTO> handle(UpdateLicenseTypeCommand command) {
      // TODO: Implement the command handling logic here
      return null;
   }

}