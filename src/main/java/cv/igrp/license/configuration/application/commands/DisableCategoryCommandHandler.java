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
public class DisableCategoryCommandHandler implements CommandHandler<DisableCategoryCommand, ResponseEntity<Map<String, ?>>> {

   private static final Logger LOGGER = LoggerFactory.getLogger(DisableCategoryCommandHandler.class);

   private final CategoryRepository categoryRepository;

   public DisableCategoryCommandHandler(CategoryRepository categoryRepository) {

     this.categoryRepository = categoryRepository;
   }

   @IgrpCommandHandler
   public ResponseEntity<Map<String, ?>> handle(DisableCategoryCommand command) {

      var categoryId = CategoryId.from(command.getCategoryId());

      var category = categoryRepository.findById(categoryId).orElseThrow(
          () ->
              IgrpResponseStatusException.notFound("Category not found with id: " + command.getCategoryId())
      );

      category.disable();

      categoryRepository.save(category);

      return ResponseEntity.ok(Map.of("message", "Category disabled successfully"));
   }

}
