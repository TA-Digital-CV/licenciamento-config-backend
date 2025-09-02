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

import java.util.Map;

@Component
public class EnableSectorCommandHandler implements CommandHandler<EnableSectorCommand, ResponseEntity<Map<String, ?>>> {

   private static final Logger LOGGER = LoggerFactory.getLogger(EnableSectorCommandHandler.class);

  private final SectorRepository sectorRepository;

   public EnableSectorCommandHandler(SectorRepository sectorRepository) {

     this.sectorRepository = sectorRepository;
   }

   @IgrpCommandHandler
   public ResponseEntity<Map<String, ?>> handle(EnableSectorCommand command) {
     var sectorId = SectorId.from(command.getSectorId());

     var sector = sectorRepository.findById(sectorId).orElseThrow(
         () ->
             IgrpResponseStatusException.notFound("Sector not found with id: " + command.getSectorId())
     );

     sector.enable();

     sectorRepository.save(sector);


     return ResponseEntity.ok(Map.of("message", "Sector enable successfully"));
   }

}
