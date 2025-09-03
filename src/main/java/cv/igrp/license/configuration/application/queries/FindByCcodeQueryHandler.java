package cv.igrp.license.configuration.application.queries;

import cv.igrp.framework.core.domain.QueryHandler;
import cv.igrp.framework.stereotype.IgrpQueryHandler;
import cv.igrp.license.configuration.application.dto.OptionResponseDTO;
import cv.igrp.license.configuration.domain.repository.OptionRepository;
import cv.igrp.license.configuration.infrastructure.mappers.OptionMapper;
import cv.igrp.license.shared.domain.exceptions.IgrpResponseStatusException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Component
public class FindByCcodeQueryHandler implements QueryHandler<FindByCcodeQuery, ResponseEntity<OptionResponseDTO>>{

  private static final Logger LOGGER = LoggerFactory.getLogger(FindByCcodeQueryHandler.class);
  private final OptionRepository optionRepository;
  private final OptionMapper optionMapper;

  public FindByCcodeQueryHandler(OptionRepository optionRepository, OptionMapper optionMapper) {
    this.optionRepository = optionRepository;
    this.optionMapper = optionMapper;
  }

   @IgrpQueryHandler
   @Transactional(readOnly = true)
  public ResponseEntity<OptionResponseDTO> handle(FindByCcodeQuery query) {
    // TODO: Implement the query handling logic here
     var ccode = query.getCcode();

     if (ccode == null || ccode.isBlank()) {
       throw IgrpResponseStatusException.badRequest("The field <ccode> is required");
     }

     var sector = optionRepository.findByCcode(ccode)
         .orElseThrow(() -> IgrpResponseStatusException.notFound(
             "Sector with id '" + ccode + "' not found"));

     var responseDTO = optionMapper.toResponseDTO(sector);

     return ResponseEntity.ok(responseDTO);
  }

}
