package cv.igrp.license.configuration.application.commands;

import cv.igrp.framework.core.domain.CommandHandler;
import cv.igrp.framework.stereotype.IgrpCommandHandler;
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
public class UpdateCategoryCommandHandler implements CommandHandler<UpdateCategoryCommand, ResponseEntity<CategoryResponseDTO>> {

   private static final Logger LOGGER = LoggerFactory.getLogger(UpdateCategoryCommandHandler.class);

  private final CategoryRepository categoryRepository;
  private final CategoryMapper categoryMapper;
  private final SectorRepository sectorRepository;

   public UpdateCategoryCommandHandler(CategoryRepository categoryRepository, CategoryMapper categoryMapper, SectorRepository sectorRepository) {

     this.categoryRepository = categoryRepository;
     this.categoryMapper = categoryMapper;
     this.sectorRepository = sectorRepository;
   }

   @IgrpCommandHandler
   public ResponseEntity<CategoryResponseDTO> handle(UpdateCategoryCommand command) {
     var dto = command.getCategoryrequest();
     var categoryId = CategoryId.from(command.getCategoryId());

     var category = categoryRepository.findById(categoryId)
         .orElseThrow(() -> IgrpResponseStatusException.notFound("Category not found"));

    /* if (!category.getCode().equals(dto.getCode()) && categoryRepository.existsByCode(dto.getCode())) {
       throw IgrpResponseStatusException.badRequest(
           "Category with code '" + dto.getCode() + "' already exists");
     }*/

     var sector = sectorRepository.findById(SectorId.from(dto.getSectorId()))
         .orElseThrow(() -> IgrpResponseStatusException.notFound("Sector not found"));

     var metadata = Metadata.fromMap(dto.getMetadata());

     category.atualizar(
         dto.getName(),
         dto.getDescription(),
         dto.getCode(),
         category.getLevel(), // manter level atual
         dto.getSortOrder(),
         metadata,
         category.getPath(),  // manter path atual
         dto.getParentId() != null ? CategoryId.from(dto.getParentId()) : null,
         sector
     );

     var savedCategory = categoryRepository.save(category);

     var responseDTO = categoryMapper.toDTO(savedCategory);

     return ResponseEntity.ok(responseDTO);
   }

}
