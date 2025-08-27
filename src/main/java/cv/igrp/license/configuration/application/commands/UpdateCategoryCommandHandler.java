package cv.igrp.license.configuration.application.commands;

import cv.igrp.framework.core.domain.CommandHandler;
import cv.igrp.framework.stereotype.IgrpCommandHandler;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import cv.igrp.license.configuration.application.dto.CategoryResponseDTO;

@Component
public class UpdateCategoryCommandHandler implements CommandHandler<UpdateCategoryCommand, ResponseEntity<CategoryResponseDTO>> {

   private static final Logger LOGGER = LoggerFactory.getLogger(UpdateCategoryCommandHandler.class);

   public UpdateCategoryCommandHandler() {

   }

   @IgrpCommandHandler
   public ResponseEntity<CategoryResponseDTO> handle(UpdateCategoryCommand command) {
      // TODO: Implement the command handling logic here
      return null;
   }

}