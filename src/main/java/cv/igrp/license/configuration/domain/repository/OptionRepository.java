package cv.igrp.license.configuration.domain.repository;

import cv.igrp.license.configuration.domain.filter.OptionFilter;
import cv.igrp.license.configuration.domain.models.Option;
import cv.igrp.license.configuration.domain.valueobject.OptionId;

import java.util.List;
import java.util.Optional;

public interface OptionRepository {
  Option save(Option option);

  Optional<Option> findById(OptionId id);

  List<Option> findAll();

  List<Option> findAll(OptionFilter filter);

  void delete(OptionId id);

  boolean existsByKeyAndCode(String ckey, String ccode);

  boolean existsById(OptionId id);

  boolean existsByCcode(String ccode);

  Optional<Option> findByCcode(String ccode);

}
