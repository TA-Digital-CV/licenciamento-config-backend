package cv.igrp.license.configuration.domain.models;

import cv.igrp.license.shared.domain.valueobject.Identificador;
import cv.igrp.license.shared.domain.valueobject.Metadata;
import lombok.Getter;

import java.math.BigDecimal;
import java.util.Objects;

@Getter
public class LicenseType {

    private final Identificador id;
    private String name;
    private String description;
    private String code;
    private String licensingModelKey;
    private Integer validityPeriod;
    private String validityUnitKey;
    private boolean renewable;
    private boolean autoRenewal;
    private boolean requiresInspection;
    private boolean requiresPublicConsultation;
    private Integer maxProcessingDays;
    private boolean hasFees;
    private BigDecimal baseFee;
    private String currencyCode;
    private boolean active;
    private Metadata metadata;
    private Integer sortOrder;
    private Identificador categoryId;

    private LicenseType(Identificador id,
                        String name,
                        String description,
                        String code,
                        String licensingModelKey,
                        Integer validityPeriod,
                        String validityUnitKey,
                        boolean renewable,
                        boolean autoRenewal,
                        boolean requiresInspection,
                        boolean requiresPublicConsultation,
                        Integer maxProcessingDays,
                        boolean hasFees,
                        BigDecimal baseFee,
                        String currencyCode,
                        boolean active,
                        Metadata metadata,
                        Integer sortOrder,
                        Identificador categoryId) {

        this.id = Objects.requireNonNull(id, "Identificador não pode ser nulo");
        this.name = Objects.requireNonNull(name, "Nome não pode ser nulo");
        this.description = description;
        this.code = Objects.requireNonNull(code, "Código não pode ser nulo");
        this.licensingModelKey = Objects.requireNonNull(licensingModelKey, "LicensingModelKey não pode ser nulo");
        this.validityPeriod = validityPeriod;
        this.validityUnitKey = validityUnitKey;
        this.renewable = renewable;
        this.autoRenewal = autoRenewal;
        this.requiresInspection = requiresInspection;
        this.requiresPublicConsultation = requiresPublicConsultation;
        this.maxProcessingDays = maxProcessingDays;
        this.hasFees = hasFees;
        this.baseFee = baseFee;
        this.currencyCode = currencyCode;
        this.active = active;
        this.metadata = metadata;
        this.sortOrder = sortOrder;
        this.categoryId = Objects.requireNonNull(categoryId, "CategoryId não pode ser nulo");
    }

    public static LicenseType criarNovo(String name,
                                        String description,
                                        String code,
                                        String licensingModelKey,
                                        Integer validityPeriod,
                                        String validityUnitKey,
                                        boolean renewable,
                                        boolean autoRenewal,
                                        boolean requiresInspection,
                                        boolean requiresPublicConsultation,
                                        Integer maxProcessingDays,
                                        boolean hasFees,
                                        BigDecimal baseFee,
                                        String currencyCode,
                                        Integer sortOrder,
                                        Metadata metadata,
                                        Identificador categoryId) {

        return new LicenseType(
                Identificador.gerarNovo(),
                name,
                description,
                code,
                licensingModelKey,
                validityPeriod,
                validityUnitKey,
                renewable,
                autoRenewal,
                requiresInspection,
                requiresPublicConsultation,
                maxProcessingDays,
                hasFees,
                baseFee,
                currencyCode,
                true, // ativo por padrão
                metadata,
                sortOrder,
                categoryId
        );
    }

    public static LicenseType reconstruir(Identificador id,
                                          String name,
                                          String description,
                                          String code,
                                          String licensingModelKey,
                                          Integer validityPeriod,
                                          String validityUnitKey,
                                          boolean renewable,
                                          boolean autoRenewal,
                                          boolean requiresInspection,
                                          boolean requiresPublicConsultation,
                                          Integer maxProcessingDays,
                                          boolean hasFees,
                                          BigDecimal baseFee,
                                          String currencyCode,
                                          boolean active,
                                          Metadata metadata,
                                          Integer sortOrder,
                                          Identificador categoryId) {

        return new LicenseType(
                id,
                name,
                description,
                code,
                licensingModelKey,
                validityPeriod,
                validityUnitKey,
                renewable,
                autoRenewal,
                requiresInspection,
                requiresPublicConsultation,
                maxProcessingDays,
                hasFees,
                baseFee,
                currencyCode,
                active,
                metadata,
                sortOrder,
                categoryId
        );
    }

    public void atualizar(String name,
                          String description,
                          String code,
                          String licensingModelKey,
                          Integer validityPeriod,
                          String validityUnitKey,
                          boolean renewable,
                          boolean autoRenewal,
                          boolean requiresInspection,
                          boolean requiresPublicConsultation,
                          Integer maxProcessingDays,
                          boolean hasFees,
                          BigDecimal baseFee,
                          String currencyCode,
                          Integer sortOrder,
                          Metadata metadata,
                          Identificador categoryId) {

        this.name = Objects.requireNonNull(name, "Nome não pode ser nulo");
        this.description = description;
        this.code = Objects.requireNonNull(code, "Código não pode ser nulo");
        this.licensingModelKey = Objects.requireNonNull(licensingModelKey, "LicensingModelKey não pode ser nulo");
        this.validityPeriod = validityPeriod;
        this.renewable = renewable;
        this.autoRenewal = autoRenewal;
        this.requiresInspection = requiresInspection;
        this.requiresPublicConsultation = requiresPublicConsultation;
        this.maxProcessingDays = maxProcessingDays;
        this.hasFees = hasFees;
        this.baseFee = baseFee;
        this.currencyCode = currencyCode;
        this.metadata = metadata;
        this.sortOrder = sortOrder;
        this.categoryId = Objects.requireNonNull(categoryId, "CategoryId não pode ser nulo");
    }

    public void ativar() {
        this.active = true;
    }

    public void desativar() {
        this.active = false;
    }

    public boolean isAtivo() {
        return active;
    }
}
