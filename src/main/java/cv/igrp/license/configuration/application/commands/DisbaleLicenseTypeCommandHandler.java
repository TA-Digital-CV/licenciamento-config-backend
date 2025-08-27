package cv.igrp.license.configuration.application.commands;

import cv.igrp.framework.core.domain.CommandHandler;
import cv.igrp.framework.stereotype.IgrpCommandHandler;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Map;

@Component
public class DisbaleLicenseTypeCommandHandler implements CommandHandler<DisbaleLicenseTypeCommand, ResponseEntity<Map<String, ?>>> {

   private static final Logger LOGGER = LoggerFactory.getLogger(DisbaleLicenseTypeCommandHandler.class);

   public DisbaleLicenseTypeCommandHandler() {

   }

   @IgrpCommandHandler
   public ResponseEntity<Map<String, ?>> handle(DisbaleLicenseTypeCommand command) {
      // TODO: Implement the command handling logic here
      return null;
   }

}