package cv.igrp.license.configuration.domain.valueobject;

import cv.igrp.license.shared.domain.valueobject.Identificador;

import java.util.Objects;

public class SectorId {

  private final Identificador identificador;

  private SectorId(Identificador identificador) {
    if (identificador == null) {
      throw new IllegalArgumentException("SectorId não pode ser nulo.");
    }
    this.identificador = identificador;
  }

  public static SectorId from(Identificador identificador) {
    return new SectorId(identificador);
  }

  public static SectorId from(String uuid) {
    return new SectorId(Identificador.from(uuid));
  }

  public static SectorId gerarNovo() {
    return new SectorId(Identificador.gerarNovo());
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
    if (!(o instanceof SectorId sectorId)) return false;
    return Objects.equals(identificador, sectorId.identificador);
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
