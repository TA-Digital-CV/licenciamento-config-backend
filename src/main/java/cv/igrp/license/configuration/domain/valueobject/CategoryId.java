package cv.igrp.license.configuration.domain.valueobject;

import cv.igrp.license.shared.domain.valueobject.Identificador;

import java.util.Objects;

public class CategoryId {

  private final Identificador identificador;

  private CategoryId(Identificador identificador) {
    if (identificador == null) {
      throw new IllegalArgumentException("CategoryId não pode ser nulo.");
    }
    this.identificador = identificador;
  }

  public static CategoryId from(Identificador identificador) {
    return new CategoryId(identificador);
  }

  public static CategoryId from(String uuid) {
    return new CategoryId(Identificador.from(uuid));
  }

  public static CategoryId from(java.util.UUID uuid) {
    return new CategoryId(Identificador.from(uuid));
  }

  public static CategoryId gerarNovo() {
    return new CategoryId(Identificador.gerarNovo());
  }

  public Identificador getIdentificador() {
    return identificador;
  }

  public String getValorComoString() {
    return identificador.getStringValor();
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (!(o instanceof CategoryId categoryId)) return false;
    return Objects.equals(identificador, categoryId.identificador);
  }

  @Override
  public int hashCode() {
    return Objects.hash(identificador);
  }

  @Override
  public String toString() {
    return getValorComoString();
  }
}
