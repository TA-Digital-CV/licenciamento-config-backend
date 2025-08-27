package cv.igrp.license.configuration.domain.repository;



import cv.igrp.license.configuration.domain.filter.CategoryFilter;
import cv.igrp.license.configuration.domain.models.Category;
import cv.igrp.license.configuration.domain.valueobject.CategoryId;
import cv.igrp.license.shared.domain.valueobject.Identificador;

import java.util.List;
import java.util.Optional;

public interface CategoryRepository {

    Category save(Category category);

    Optional<Category> findById(CategoryId id);

    List<Category> findAll();

    List<Category> findAll(CategoryFilter filter);

    void delete(CategoryId id);

    boolean existsByCode(String code);

}
