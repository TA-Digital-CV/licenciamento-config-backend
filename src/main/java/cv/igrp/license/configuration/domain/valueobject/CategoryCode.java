package cv.igrp.license.configuration.domain.valueobject;

import cv.igrp.license.shared.domain.exceptions.IgrpResponseStatusException;
import lombok.Getter;

import java.util.Objects;
import java.util.regex.Pattern;

@Getter
public class CategoryCode {

  private static final Pattern CODE_PATTERN = Pattern.compile("^[A-Z]{3}(\\.\\d{3})*$");

  private final String value;


  private CategoryCode(String value) {
    if (value == null || value.isBlank()) {
      throw IgrpResponseStatusException.badRequest("Código da categoria não pode ser vazio");
    }
   /* if (!CODE_PATTERN.matcher(value).matches()) {
      throw IgrpResponseStatusException.badRequest("Formato de código inválido. Ex: AGR.001 ou AGR.001.002");
    }*/
    this.value = value.toUpperCase();
  }

  public static CategoryCode of(String value) {
    return new CategoryCode(value);
  }

  /**
   * Retorna o nível da categoria a partir do código.
   * Ex: AGR      -> level 1
   *     AGR.001  -> level 2
   *     AGR.001.002 -> level 3
   */
  /*public int getLevel() {
    int level = value.split("\\.").length;
    if (level < 1 || level > 5) {
      throw IgrpResponseStatusException.badRequest(
          "Nível de hierarquia inválido derivado do código. Deve estar entre 1 e 5."
      );
    }
    return level;
  }*/


  @Override
  public String toString() {
    return value;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (!(o instanceof CategoryCode that)) return false;
    return value.equals(that.value);
  }

  @Override
  public int hashCode() {
    return Objects.hash(value);
  }


}
