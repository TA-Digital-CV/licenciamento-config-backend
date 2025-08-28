package cv.igrp.license.configuration.application.commands;

import cv.igrp.framework.core.domain.CommandHandler;
import cv.igrp.framework.stereotype.IgrpCommandHandler;
import cv.igrp.license.configuration.domain.models.Category;
import cv.igrp.license.configuration.domain.repository.CategoryRepository;
import cv.igrp.license.configuration.domain.repository.SectorRepository;
import cv.igrp.license.configuration.domain.valueobject.CategoryId;
import cv.igrp.license.configuration.domain.valueobject.SectorId;
import cv.igrp.license.configuration.infrastructure.mappers.CategoryMapper;
import cv.igrp.license.shared.domain.exceptions.IgrpResponseStatusException;
import cv.igrp.license.shared.domain.valueobject.Metadata;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import cv.igrp.license.configuration.application.dto.CategoryResponseDTO;

@Component
public class CreateCategoryCommandHandler implements CommandHandler<CreateCategoryCommand, ResponseEntity<CategoryResponseDTO>> {

   private static final Logger LOGGER = LoggerFactory.getLogger(CreateCategoryCommandHandler.class);

  private final CategoryRepository categoryRepository;
  private final CategoryMapper categoryMapper;
  private final SectorRepository sectorRepository;

   public CreateCategoryCommandHandler(CategoryRepository categoryRepository, CategoryMapper categoryMapper, SectorRepository sectorRepository) {

     this.categoryRepository = categoryRepository;
     this.categoryMapper = categoryMapper;
     this.sectorRepository = sectorRepository;
   }

   @IgrpCommandHandler
   public ResponseEntity<CategoryResponseDTO> handle(CreateCategoryCommand command) {
     var dto = command.getCategoryrequest();

     if (categoryRepository.existsByCode(dto.getCode())) {
       throw IgrpResponseStatusException.badRequest(
           "Category with code '" + dto.getCode() + "' already exists");
     }

     var sector = sectorRepository.findById(SectorId.from(dto.getSectorId()))
         .orElseThrow(() -> IgrpResponseStatusException.notFound("Sector not found"));

     var parent = categoryRepository.findById(CategoryId.from(dto.getParentId())).orElseThrow(
          () -> IgrpResponseStatusException.notFound("Parent Category not found")
     );

     var metadata = Metadata.fromMap(dto.getMetadata());

     var category = Category.criarNovo(
         dto.getName(),
         dto.getDescription(),
         dto.getCode(),
         dto.getSortOrder(),
         metadata,
         parent,
         sector
     );

     var savedCategory = categoryRepository.save(category);

     var responseDTO = categoryMapper.toDTO(savedCategory);

     return ResponseEntity.ok(responseDTO);
   }

}
