package cv.igrp.license.configuration.application.commands;

import cv.igrp.framework.core.domain.CommandHandler;
import cv.igrp.framework.stereotype.IgrpCommandHandler;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import cv.igrp.license.configuration.application.dto.SectorResponseDTO;

@Component
public class UpdateSectorCommandHandler implements CommandHandler<UpdateSectorCommand, ResponseEntity<SectorResponseDTO>> {

   private static final Logger LOGGER = LoggerFactory.getLogger(UpdateSectorCommandHandler.class);

   public UpdateSectorCommandHandler() {

   }

   @IgrpCommandHandler
   public ResponseEntity<SectorResponseDTO> handle(UpdateSectorCommand command) {
      // TODO: Implement the command handling logic here
      return null;
   }

}