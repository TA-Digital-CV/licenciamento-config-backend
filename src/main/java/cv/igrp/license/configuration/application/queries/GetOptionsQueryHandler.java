package cv.igrp.license.configuration.application.queries;

import cv.igrp.framework.core.domain.QueryHandler;
import cv.igrp.framework.stereotype.IgrpQueryHandler;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;



@Component
public class GetOptionsQueryHandler implements QueryHandler<GetOptionsQuery, ResponseEntity<String>>{

  private static final Logger LOGGER = LoggerFactory.getLogger(GetOptionsQueryHandler.class);


  public GetOptionsQueryHandler() {

  }

   @IgrpQueryHandler
  public ResponseEntity<String> handle(GetOptionsQuery query) {
    // TODO: Implement the query handling logic here
    return null;
  }

}
