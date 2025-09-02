package cv.igrp.license.configuration.domain.valueobject;

import cv.igrp.license.shared.domain.exceptions.IgrpResponseStatusException;
import lombok.Getter;

import java.util.Objects;

@Getter
public class CategoryPath {

  private final String value;

  private CategoryPath(String value) {
    if (value == null || value.isBlank()) {
      throw IgrpResponseStatusException.badRequest("Path da categoria não pode ser vazio");
    }
    if (value.length() > 500) {
      throw IgrpResponseStatusException.badRequest("Path da categoria excede limite de 500 caracteres");
    }
    this.value = value;
  }

  public static CategoryPath of(String value) {
    return new CategoryPath(value);
  }

  @Override
  public String toString() {
    return value;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (!(o instanceof CategoryPath that)) return false;
    return value.equals(that.value);
  }

  @Override
  public int hashCode() {
    return Objects.hash(value);
  }
}
