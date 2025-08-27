package cv.igrp.license.configuration.domain.valueobject;

import cv.igrp.license.shared.domain.valueobject.Identificador;

import java.util.Objects;

public class LicenseTypeId {

  private final Identificador identificador;

  private LicenseTypeId(Identificador identificador) {
    if (identificador == null) {
      throw new IllegalArgumentException("LicenseTypeId não pode ser nulo.");
    }
    this.identificador = identificador;
  }

  public static LicenseTypeId from(Identificador identificador) {
    return new LicenseTypeId(identificador);
  }

  public static LicenseTypeId from(String uuid) {
    return new LicenseTypeId(Identificador.from(uuid));
  }

  public static LicenseTypeId from(java.util.UUID uuid) {
    return new LicenseTypeId(Identificador.from(uuid));
  }

  public static LicenseTypeId gerarNovo() {
    return new LicenseTypeId(Identificador.gerarNovo());
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
    if (!(o instanceof LicenseTypeId licenseTypeId)) return false;
    return Objects.equals(identificador, licenseTypeId.identificador);
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
