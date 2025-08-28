package cv.igrp.license.configuration.domain.models;


import cv.igrp.license.configuration.domain.valueobject.CategoryId;
import cv.igrp.license.shared.domain.exceptions.IgrpResponseStatusException;
import cv.igrp.license.shared.domain.valueobject.Identificador;
import cv.igrp.license.shared.domain.valueobject.Metadata;
import lombok.Getter;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Getter
public class Category {

  private final CategoryId id;
  private String name;
  private String description;
  private String code;
  private boolean active;
  private Integer level;
  private Integer sortOrder;
  private Metadata metadata;
  private String path;

  private CategoryId parentId;       // apenas referência
  private Sector sector;                // referência completa
  private List<Category> children;  // filhos completos

  private Category(CategoryId id,
                   String name,
                   String description,
                   String code,
                   boolean active,
                   Integer level,
                   Integer sortOrder,
                   Metadata metadata,
                   String path,
                   CategoryId parentId,
                   Sector sector,
                   List<Category> children) {

    this.id = Objects.requireNonNull(id);
    this.name = Objects.requireNonNull(name);
    this.description = description;
    this.code = Objects.requireNonNull(code);
    this.active = active;
    this.level = level;
    this.sortOrder = sortOrder;
    this.metadata = metadata;
    this.path = path;
    this.parentId = parentId;
    this.sector = sector;
    this.children = children != null ? children : new ArrayList<>();

  }

  public static Category criarNovo(String name,
                                   String description,
                                   String code,
                                   Integer level,
                                   Integer sortOrder,
                                   Metadata metadata,
                                   String path,
                                   CategoryId parentId,
                                   Sector sector) {

    Objects.requireNonNull(name, "Nome não pode ser nulo");
    Objects.requireNonNull(code, "Código não pode ser nulo");

    validateCategoryLevel(level);

    return new Category(
        CategoryId.gerarNovo(),
        name,
        description,
        code,
        true,
        level,
        sortOrder,
        metadata,
        path,
        parentId,
        sector,
        new ArrayList<>()
    );
  }

  private static void validateCategoryLevel(Integer level){

    if(level < 1 || level > 5){
      throw IgrpResponseStatusException.badRequest("Nível de hierarquia inválido. Deve estar entre 1 e 5.");
    }
  }

  public void atualizar(String name,
                        String description,
                        String code,
                        Integer level,
                        Integer sortOrder,
                        Metadata metadata,
                        String path,
                        CategoryId parentId,
                        Sector sector) {

    this.name = Objects.requireNonNull(name, "Nome não pode ser nulo");
    this.description = description;
    this.code = Objects.requireNonNull(code, "Código não pode ser nulo");
    this.level = level;
    this.sortOrder = sortOrder;
    this.metadata = metadata;
    this.path = path;
    this.parentId = parentId;
    this.sector = sector;
  }


  public static Category reconstruir(
      CategoryId id,
      String name,
      String description,
      String code,
      boolean active,
      Integer level,
      Integer sortOrder,
      Metadata metadata,
      String path,
      CategoryId parentId,
      Sector sector,
      List<Category> children) {

    return new Category(
        id,
        name,
        description,
        code,
        active,
        level,
        sortOrder,
        metadata,
        path,
        parentId,
        sector,
        children != null ? children : new ArrayList<>()
    );
  }


  public void addChild(Category child) {
    if (!children.contains(child)) {
      children.add(child);
    }
  }

  public void removeChild(Category child) {
    children.remove(child);
  }

  public void ativar() {
    this.active = true;
  }

  public void desativar() {
    this.active = false;
  }

  public boolean isAtivo() {
    return this.active;
  }

  public void move(CategoryId newParentId) {
    this.parentId = newParentId;
  }

}
