package cv.igrp.license.configuration.application.commands;

import cv.igrp.framework.core.domain.CommandHandler;
import cv.igrp.framework.stereotype.IgrpCommandHandler;
import cv.igrp.license.configuration.domain.repository.SectorRepository;
import cv.igrp.license.configuration.domain.valueobject.SectorId;
import cv.igrp.license.shared.domain.exceptions.IgrpResponseStatusException;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Collection;
import java.util.Map;

@Component
public class DisableSectorCommandHandler implements CommandHandler<DisableSectorCommand, ResponseEntity<Map<String, ?>>> {

   private static final Logger LOGGER = LoggerFactory.getLogger(DisableSectorCommandHandler.class);

   private final SectorRepository sectorRepository;

   public DisableSectorCommandHandler(SectorRepository sectorRepository) {

     this.sectorRepository = sectorRepository;
   }

   @IgrpCommandHandler
   public ResponseEntity<Map<String, ?>> handle(DisableSectorCommand command) {
      var sectorId = SectorId.from(command.getSectorId());

      var sector = sectorRepository.findById(sectorId).orElseThrow(
          () ->
              IgrpResponseStatusException.notFound("Sector not found with id: " + command.getSectorId())
      );

      sector.disable();

      sectorRepository.save(sector);


      return ResponseEntity.ok(Map.of("message", "Sector disabled successfully"));
   }

}
