package cv.igrp.license.configuration.application.queries;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import cv.igrp.framework.core.domain.QueryHandler;
import cv.igrp.framework.stereotype.IgrpQueryHandler;
import org.springframework.context.event.EventListener;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

import cv.igrp.license.configuration.application.dto.CategoryResponseDTO;

@Component
public class GetCategoryByIdQueryHandler implements QueryHandler<GetCategoryByIdQuery, ResponseEntity<CategoryResponseDTO>>{

  private static final Logger LOGGER = LoggerFactory.getLogger(GetCategoryByIdQueryHandler.class);


  public GetCategoryByIdQueryHandler() {

  }

   @IgrpQueryHandler
  public ResponseEntity<CategoryResponseDTO> handle(GetCategoryByIdQuery query) {
    // TODO: Implement the query handling logic here
    return null;
  }

}