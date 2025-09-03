package cv.igrp.license.configuration.application.queries;

import cv.igrp.framework.core.domain.QueryHandler;
import cv.igrp.framework.stereotype.IgrpQueryHandler;
import cv.igrp.license.configuration.domain.repository.OptionRepository;
import cv.igrp.license.configuration.infrastructure.mappers.OptionMapper;
import cv.igrp.license.shared.domain.exceptions.IgrpResponseStatusException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;


@Component
public class ExistsByCcodeQueryHandler implements QueryHandler<ExistsByCcodeQuery, ResponseEntity<Boolean>>{

  private static final Logger LOGGER = LoggerFactory.getLogger(ExistsByCcodeQueryHandler.class);
  private final OptionRepository optionRepository;
  private final OptionMapper optionMapper;

  public ExistsByCcodeQueryHandler(OptionRepository optionRepository, OptionMapper optionMapper) {
    this.optionRepository = optionRepository;
    this.optionMapper = optionMapper;
  }

   @IgrpQueryHandler
   @Transactional(readOnly = true)

   public ResponseEntity<Boolean> handle(ExistsByCcodeQuery query) {
     var ccode = query.getCcode();

     if (ccode == null || ccode.isBlank()) {
       throw IgrpResponseStatusException.badRequest("The field <ccode> is required");
     }
     var sector = optionRepository.existsByCcode(ccode);

     //var responseDTO = optionMapper.toResponseDTO(sector);

     return ResponseEntity.ok(sector);
  }

}
