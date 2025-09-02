package cv.igrp.license.configuration.application.queries;

import cv.igrp.license.configuration.application.dto.LicenseTypeResponseDTO;
import cv.igrp.license.configuration.domain.filter.LicenseTypeFilter;
import cv.igrp.license.configuration.domain.models.LicenseType;
import cv.igrp.license.configuration.domain.repository.LicenseTypeRepository;
import cv.igrp.license.configuration.domain.valueobject.CategoryId;
import cv.igrp.license.configuration.infrastructure.mappers.LicenseTypeMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import cv.igrp.framework.core.domain.QueryHandler;
import cv.igrp.framework.stereotype.IgrpQueryHandler;
import org.springframework.context.event.EventListener;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

import cv.igrp.license.configuration.application.dto.WrapperListLicenseTypeDTO;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class GetListLicenseTypesQueryHandler implements QueryHandler<GetListLicenseTypesQuery, ResponseEntity<WrapperListLicenseTypeDTO>>{

  private static final Logger LOGGER = LoggerFactory.getLogger(GetListLicenseTypesQueryHandler.class);

  private final LicenseTypeRepository licenseTypeRepository;
  private final LicenseTypeMapper licenseTypeMapper;

  public GetListLicenseTypesQueryHandler(LicenseTypeRepository licenseTypeRepository, LicenseTypeMapper licenseTypeMapper) {

    this.licenseTypeRepository = licenseTypeRepository;
    this.licenseTypeMapper = licenseTypeMapper;
  }

   @IgrpQueryHandler
  public ResponseEntity<WrapperListLicenseTypeDTO> handle(GetListLicenseTypesQuery query) {
     LicenseTypeFilter filter = LicenseTypeFilter.builder()
         .categoryId(query.getCategoryId() != null && !query.getCategoryId().isBlank()
             ? CategoryId.from(query.getCategoryId()) : null)
         .licensingModel(query.getLicensingModel())
         .active(query.isActive())
         .renewable(query.isRenewable())
         .name(query.getName())
         .code(query.getCode())
         .pageNumber(Integer.parseInt(query.getPageNumber()))
         .pageSize(Integer.parseInt(query.getPageSize()))
         .build();

     List<LicenseType> allResults = licenseTypeRepository.findAll(filter);

     int pageNumber = filter.getPageNumber() != null ? filter.getPageNumber() : 0;
     int pageSize = filter.getPageSize() != null ? filter.getPageSize() : allResults.size();
     int fromIndex = Math.min(pageNumber * pageSize, allResults.size());
     int toIndex = Math.min(fromIndex + pageSize, allResults.size());

     List<LicenseType> pagedResults = allResults.subList(fromIndex, toIndex);

     List<LicenseTypeResponseDTO> content = pagedResults.stream()
         .map(licenseTypeMapper::toResponseDTO)
         .collect(Collectors.toList());

     WrapperListLicenseTypeDTO wrapper = new WrapperListLicenseTypeDTO();
     wrapper.setContent(content);
     wrapper.setPageNumber(pageNumber);
     wrapper.setPageSize(pageSize);
     wrapper.setTotalElements((long) allResults.size());
     wrapper.setTotalPages((int) Math.ceil((double) allResults.size() / pageSize));
     wrapper.setFirst(pageNumber == 0);
     wrapper.setLast(pageNumber >= wrapper.getTotalPages() - 1);

     return ResponseEntity.ok(wrapper);
  }

}
