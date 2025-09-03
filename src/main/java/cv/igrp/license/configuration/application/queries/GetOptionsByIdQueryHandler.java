package cv.igrp.license.configuration.application.queries;

import cv.igrp.framework.core.domain.QueryHandler;
import cv.igrp.framework.stereotype.IgrpQueryHandler;
import cv.igrp.license.configuration.application.dto.OptionResponseDTO;
import cv.igrp.license.configuration.domain.repository.OptionRepository;
import cv.igrp.license.configuration.domain.valueobject.OptionId;
import cv.igrp.license.configuration.infrastructure.mappers.OptionMapper;
import cv.igrp.license.shared.domain.exceptions.IgrpResponseStatusException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;



@Component
public class GetOptionsByIdQueryHandler implements QueryHandler<GetOptionsByIdQuery, ResponseEntity<OptionResponseDTO>>{

  private static final Logger LOGGER = LoggerFactory.getLogger(GetOptionsByIdQueryHandler.class);

  private final OptionRepository optionRepository;
  private final OptionMapper optionMapper;

  public GetOptionsByIdQueryHandler(OptionRepository optionRepository, OptionMapper optionMapper) {

    this.optionRepository = optionRepository;
    this.optionMapper = optionMapper;
  }

   @IgrpQueryHandler
  public ResponseEntity<OptionResponseDTO> handle(GetOptionsByIdQuery query) {
     LOGGER.info("CHEGOU___________________________________");
     var optionId = query.getOptionId();

     if (optionId == null || optionId.isBlank()) {
       throw IgrpResponseStatusException.badRequest("The field <optionId> is required");
     }
     var sector = optionRepository.findById(OptionId.from(optionId))
         .orElseThrow(() -> IgrpResponseStatusException.notFound(
             "Sector with id '" + optionId + "' not found"));

     var responseDTO = optionMapper.toResponseDTO(sector);

     return ResponseEntity.ok(responseDTO);
  }

}
