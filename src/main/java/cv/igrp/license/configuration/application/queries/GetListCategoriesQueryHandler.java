package cv.igrp.license.configuration.application.queries;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import cv.igrp.framework.core.domain.QueryHandler;
import cv.igrp.framework.stereotype.IgrpQueryHandler;
import org.springframework.context.event.EventListener;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

import cv.igrp.license.configuration.application.dto.WrapperListCategoryDTO;

@Component
public class GetListCategoriesQueryHandler implements QueryHandler<GetListCategoriesQuery, ResponseEntity<WrapperListCategoryDTO>>{

  private static final Logger LOGGER = LoggerFactory.getLogger(GetListCategoriesQueryHandler.class);


  public GetListCategoriesQueryHandler() {

  }

   @IgrpQueryHandler
  public ResponseEntity<WrapperListCategoryDTO> handle(GetListCategoriesQuery query) {
    // TODO: Implement the query handling logic here
    return null;
  }

}