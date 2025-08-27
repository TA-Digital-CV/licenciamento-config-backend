package cv.igrp.license.configuration.application.commands;

import cv.igrp.framework.core.domain.CommandHandler;
import cv.igrp.framework.stereotype.IgrpCommandHandler;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import cv.igrp.license.configuration.application.dto.SectorResponseDTO;

@Component
public class CreateSectorCommandHandler implements CommandHandler<CreateSectorCommand, ResponseEntity<SectorResponseDTO>> {

   private static final Logger LOGGER = LoggerFactory.getLogger(CreateSectorCommandHandler.class);

   public CreateSectorCommandHandler() {

   }

   @IgrpCommandHandler
   public ResponseEntity<SectorResponseDTO> handle(CreateSectorCommand command) {
      // TODO: Implement the command handling logic here
      return null;
   }

}