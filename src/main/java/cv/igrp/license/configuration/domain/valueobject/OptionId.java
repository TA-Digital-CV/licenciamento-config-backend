package cv.igrp.license.configuration.domain.valueobject;

import cv.igrp.license.shared.domain.valueobject.Identificador;

import java.util.Objects;

public class OptionId {

  private final Identificador identificador;

  private OptionId(Identificador identificador) {
    if (identificador == null) {
      throw new IllegalArgumentException("OptionId não pode ser nulo.");
    }
    this.identificador = identificador;
  }

  public static OptionId from(Identificador identificador) {
    return new OptionId(identificador);
  }

  public static OptionId from(String uuid) {
    return new OptionId(Identificador.from(uuid));
  }

  public static OptionId from(java.util.UUID uuid) {
    return new OptionId(Identificador.from(uuid));
  }

  public static OptionId gerarNovo() {
    return new OptionId(Identificador.gerarNovo());
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
    if (!(o instanceof OptionId optionId)) return false;
    return Objects.equals(identificador, optionId.identificador);
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
