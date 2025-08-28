package cv.igrp.license.configuration.application.commands;

import cv.igrp.framework.core.domain.CommandHandler;
import cv.igrp.framework.stereotype.IgrpCommandHandler;
import cv.igrp.license.configuration.domain.repository.CategoryRepository;
import cv.igrp.license.configuration.domain.valueobject.CategoryId;
import cv.igrp.license.shared.domain.exceptions.IgrpResponseStatusException;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Map;

@Component
public class MoveCategoryCommandHandler implements CommandHandler<MoveCategoryCommand, ResponseEntity<Map<String, ?>>> {

   private static final Logger LOGGER = LoggerFactory.getLogger(MoveCategoryCommandHandler.class);

  private final CategoryRepository categoryRepository;

  public MoveCategoryCommandHandler(CategoryRepository categoryRepository) {

    this.categoryRepository = categoryRepository;
  }

   @IgrpCommandHandler
   public ResponseEntity<Map<String, ?>> handle(MoveCategoryCommand command) {
     var categoryId = CategoryId.from(command.getCategoryId());

     var category = categoryRepository.findById(categoryId)
         .orElseThrow(() -> IgrpResponseStatusException.notFound(
             "Category with ID '" + command.getCategoryId() + "' not found"));

     CategoryId newParentId = command.getMovecategory().getNewParentId() != null
         ? CategoryId.from(command.getMovecategory().getNewParentId())
         : null;

     if (newParentId != null && newParentId.equals(categoryId)) {
       throw IgrpResponseStatusException.badRequest(
           "A category cannot be its own parent");
     }


     var parent = categoryRepository.findById(newParentId).orElseThrow(
         () -> IgrpResponseStatusException.notFound("Parent Category not found")
     );


     category.move(parent);

     categoryRepository.save(category);

     return ResponseEntity.ok(Map.of("message", "Category moved successfully"));
   }

}
