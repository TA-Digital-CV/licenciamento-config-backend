/* THIS FILE WAS GENERATED AUTOMATICALLY BY iGRP STUDIO. */
/* DO NOT MODIFY IT BECAUSE IT COULD BE REWRITTEN AT ANY TIME. */

package cv.igrp.license.shared.infrastructure.persistence.entity;

import cv.igrp.license.shared.config.AuditEntity;
import cv.igrp.framework.stereotype.IgrpEntity;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.envers.Audited;
import java.util.UUID;
import jakarta.validation.constraints.NotBlank;
import java.util.List;

@Audited
@Getter
@Setter
@IgrpEntity
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "t_category")
public class CategoryEntity extends AuditEntity {

    @Id
    @Column(name = "id", unique = true, nullable = false)
    private UUID id;

  
    @NotBlank(message = "name is mandatory")
    @Column(name="name", nullable = false)
    private String name;

  
    @Column(name="description")
    private String description;

  
    @Column(name="code", unique = true)
    private String code;

  
    @Column(name="active")
    private boolean active;

  
    @Column(name="level")
    private Integer level;

  
    @Column(name="sort_order")
    private Integer sortOrder;

  
    @Lob
    @Column(name="metadata", columnDefinition="TEXT")
    private String metadata;

  
    @Column(name="path")
    private String path;

  


  @OneToMany(mappedBy = "parentId", fetch = FetchType.LAZY)
private List<CategoryEntity> childrens;


  @OneToMany(mappedBy = "categoryId", fetch = FetchType.LAZY)
private List<LicenseTypeEntity> licencetypes;   @ManyToOne(fetch = FetchType.LAZY)
   @JoinColumn(name = "parent_id")
   private CategoryEntity parentId;

   @ManyToOne(fetch = FetchType.LAZY)
   @JoinColumn(name = "sector_id")
   private SectorEntity sectorId;


}