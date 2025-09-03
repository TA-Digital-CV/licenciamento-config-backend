package cv.igrp.license.configuration.application.commands;

import cv.igrp.framework.core.domain.CommandHandler;
import cv.igrp.framework.stereotype.IgrpCommandHandler;
import cv.igrp.license.configuration.domain.repository.OptionRepository;
import cv.igrp.license.configuration.domain.valueobject.OptionId;
import cv.igrp.license.shared.domain.exceptions.IgrpResponseStatusException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

import java.util.Map;


@Component
public class DisableOptionCommandHandler implements CommandHandler<DisableOptionCommand, ResponseEntity<Map<String, ?>>> {

   private static final Logger LOGGER = LoggerFactory.getLogger(DisableOptionCommandHandler.class);
   private final OptionRepository optionRepository;


  public DisableOptionCommandHandler(OptionRepository optionRepository) {
    this.optionRepository = optionRepository;
   }

   @IgrpCommandHandler
   public ResponseEntity<Map<String, ?>> handle(DisableOptionCommand command) {
      // TODO: Implement the command handling logic here
     var optionId = OptionId.from(command.getOptionId());

     var existing = optionRepository.findById(optionId)
         .orElseThrow(() -> IgrpResponseStatusException.notFound(
             "Option not found for id: " + command.getOptionId()));

     existing.desativar();

     optionRepository.save(existing);

     return ResponseEntity.ok(Map.of("message","Option disable successfully"));

   }

}
