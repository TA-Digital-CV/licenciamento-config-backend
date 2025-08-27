package cv.igrp.license.configuration.domain.models;

import cv.igrp.license.shared.domain.valueobject.Identificador;
import cv.igrp.license.shared.domain.valueobject.Metadata;
import lombok.Getter;

import java.util.Objects;

@Getter
public class Sector {

    private final Identificador id;   // Identificador único (UUID encapsulado)
    private String name;
    private String description;
    private String sectorTypeKey;
    private String code;
    private boolean active;
    private Integer sortOrder;
    private Metadata metadata;

    private Sector(Identificador id,
                   String name,
                   String description,
                   String sectorTypeKey,
                   String code,
                   boolean active,
                   Integer sortOrder,
                   Metadata metadata) {

        this.id = Objects.requireNonNull(id, "Identificador não pode ser nulo");
        this.name = Objects.requireNonNull(name, "Nome não pode ser nulo");
        this.description = description;
        this.sectorTypeKey = Objects.requireNonNull(sectorTypeKey, "SectorTypeKey não pode ser nulo");
        this.code = Objects.requireNonNull(code, "Código não pode ser nulo");
        this.active = active;
        this.sortOrder = sortOrder;
        this.metadata = metadata;
    }

    /**
     * Criar um novo Sector
     */
    public static Sector criarNovo(String name,
                                   String description,
                                   String sectorTypeKey,
                                   String code,
                                   Integer sortOrder,
                                   Metadata metadata) {

        return new Sector(
                Identificador.gerarNovo(),
                name,
                description,
                sectorTypeKey,
                code,
                true, // ativo por padrão
                sortOrder,
                metadata
        );
    }

    /**
     * Reconstruir um Sector já existente (ex: vindo da base de dados)
     */
    public static Sector reconstruir(Identificador id,
                                     String name,
                                     String description,
                                     String sectorTypeKey,
                                     String code,
                                     boolean active,
                                     Integer sortOrder,
                                     Metadata metadata) {
        return new Sector(id, name, description, sectorTypeKey, code, active, sortOrder, metadata);
    }

    /**
     * Atualizar atributos principais
     */
    public void atualizar(String name, String description, String sectorTypeKey, String code, Integer sortOrder,Metadata metadata) {
        this.name = Objects.requireNonNull(name, "Nome não pode ser nulo");
        this.description = description;
        this.sectorTypeKey = Objects.requireNonNull(sectorTypeKey, "SectorTypeKey não pode ser nulo");
        this.code = Objects.requireNonNull(code, "Código não pode ser nulo");
        this.sortOrder = sortOrder;
        this.metadata = metadata;
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
