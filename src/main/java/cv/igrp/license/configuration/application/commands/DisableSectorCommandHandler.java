package cv.igrp.license.configuration.application.commands;

import cv.igrp.framework.core.domain.CommandHandler;
import cv.igrp.framework.stereotype.IgrpCommandHandler;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Collection;

@Component
public class DisableSectorCommandHandler implements CommandHandler<DisableSectorCommand, ResponseEntity<Collection<String>>> {

   private static final Logger LOGGER = LoggerFactory.getLogger(DisableSectorCommandHandler.class);

   public DisableSectorCommandHandler() {

   }

   @IgrpCommandHandler
   public ResponseEntity<Collection<String>> handle(DisableSectorCommand command) {
      // TODO: Implement the command handling logic here
      return null;
   }

}