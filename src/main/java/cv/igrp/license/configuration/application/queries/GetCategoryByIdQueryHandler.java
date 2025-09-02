package cv.igrp.license.configuration.application.queries;

import cv.igrp.license.configuration.domain.repository.CategoryRepository;
import cv.igrp.license.configuration.domain.valueobject.CategoryId;
import cv.igrp.license.configuration.infrastructure.mappers.CategoryMapper;
import cv.igrp.license.shared.domain.exceptions.IgrpResponseStatusException;
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

  private final CategoryRepository categoryRepository;
  private final CategoryMapper categoryMapper;

  public GetCategoryByIdQueryHandler(CategoryRepository categoryRepository, CategoryMapper categoryMapper) {

    this.categoryRepository = categoryRepository;
    this.categoryMapper = categoryMapper;
  }

   @IgrpQueryHandler
  public ResponseEntity<CategoryResponseDTO> handle(GetCategoryByIdQuery query) {
     var categoryId = CategoryId.from(query.getCategoryId());

     var category = categoryRepository.findById(categoryId)
         .orElseThrow(() -> IgrpResponseStatusException.notFound(
             "Category with ID '" + query.getCategoryId() + "' not found"));

     var responseDTO = categoryMapper.toDTO(category);

     return ResponseEntity.ok(responseDTO);
  }

}
