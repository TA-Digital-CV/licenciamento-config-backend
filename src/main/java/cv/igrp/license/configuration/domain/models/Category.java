package cv.igrp.license.configuration.domain.models;


import cv.igrp.license.configuration.domain.valueobject.CategoryCode;
import cv.igrp.license.configuration.domain.valueobject.CategoryId;
import cv.igrp.license.configuration.domain.valueobject.CategoryPath;
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
  private CategoryCode code;
  private boolean active;
  private Integer level;
  private Integer sortOrder;
  private Metadata metadata;
  private CategoryPath path;

  private Category parent;
  private Sector sector;
  private List<Category> children;

  private Category(CategoryId id,
                   String name,
                   String description,
                   CategoryCode code,
                   boolean active,
                   Integer level,
                   Integer sortOrder,
                   Metadata metadata,
                    CategoryPath path,
                   Category parent,
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
    this.parent = parent;
    this.sector = sector;
    this.children = children != null ? children : new ArrayList<>();

  }

  public static Category criarNovo(String name,
                                   String description,
                                   String code,
                                   Integer sortOrder,
                                   Metadata metadata,
                                   Category parent,
                                   Sector sector) {

    Objects.requireNonNull(name, "Nome não pode ser nulo");
    Objects.requireNonNull(code, "Código não pode ser nulo");


    CategoryCode categoryCode = CategoryCode.of(code);

    int level = (parent == null) ? 1 : parent.getLevel() + 1;

    /*// Regra 1: sem parent → nível 1
    if (parent == null && categoryCode.getLevel() != 1) {
      throw IgrpResponseStatusException.badRequest(
          "Categoria sem parent deve ter código de nível 1 (ex: 'AGR')"
      );
    }

    // Regra 2: com parent → nível = parent.level + 1
    if (parent != null && categoryCode.getLevel() != parent.getLevel() + 1) {
      throw IgrpResponseStatusException.badRequest(
          "Categoria com parent deve ter código de nível " + (parent.getLevel() + 1) +
              ". Código fornecido: '" + code + "'"
      );
    }*/

    return new Category(
        CategoryId.gerarNovo(),
        name,
        description,
        categoryCode,
        true,
        level,
        sortOrder,
        metadata,
        null,
        parent,
        sector,
        new ArrayList<>()
    );
  }


  public void atualizar(String name,
                        String description,
                        String code,
                        Integer sortOrder,
                        Metadata metadata,
                        Category parent,
                        Sector sector) {

    Objects.requireNonNull(code, "Código não pode ser nulo");

    CategoryCode categoryCode = CategoryCode.of(code);

   /* // Regra 1: sem parent → nível 1
    if (parent == null && categoryCode.getLevel() != 1) {
      throw IgrpResponseStatusException.badRequest(
          "Categoria sem parent deve ter código de nível 1 (ex: 'AGR')"
      );
    }

    // Regra 2: com parent → nível = parent.level + 1
    if (parent != null && categoryCode.getLevel() != parent.getLevel() + 1) {
      throw IgrpResponseStatusException.badRequest(
          "Categoria com parent deve ter código de nível " + (parent.getLevel() + 1) +
              ". Código fornecido: '" + code + "'"
      );
    }*/

    int level = (parent == null) ? 1 : parent.getLevel() + 1;

    this.name = Objects.requireNonNull(name, "Nome não pode ser nulo");
    this.description = description;
    this.code = categoryCode;
    this.level = level;
    this.sortOrder = sortOrder;
    this.metadata = metadata;
    this.path = null;
    this.parent = parent;
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
      Category parent,
      Sector sector,
      List<Category> children) {

    return new Category(
        id,
        name,
        description,
        CategoryCode.of(code),
        active,
        level,
        sortOrder,
        metadata,
        path!=null && !path.isBlank() ? CategoryPath.of(path) : null,
        parent,
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

  public void move(Category newParentId) {
    this.parent = newParentId;
  }

}
