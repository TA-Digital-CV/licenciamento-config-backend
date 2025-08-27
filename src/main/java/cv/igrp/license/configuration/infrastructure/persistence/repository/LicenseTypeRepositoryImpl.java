package cv.igrp.license.configuration.infrastructure.persistence.repository;


import cv.igrp.license.configuration.domain.filter.LicenseTypeFilter;
import cv.igrp.license.configuration.domain.models.LicenseType;
import cv.igrp.license.configuration.domain.repository.LicenseTypeRepository;
import cv.igrp.license.configuration.domain.valueobject.LicenseTypeId;
import cv.igrp.license.configuration.infrastructure.mappers.LicenseTypeMapper;
import cv.igrp.license.shared.infrastructure.persistence.entity.LicenseTypeEntity;
import cv.igrp.license.shared.infrastructure.persistence.repository.LicenseTypeEntityRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Repository
@RequiredArgsConstructor
public class LicenseTypeRepositoryImpl implements LicenseTypeRepository {

    private final LicenseTypeEntityRepository licenseTypeEntityRepository;
    private final LicenseTypeMapper licenseTypeMapper;

    @Override
    @Transactional
    public LicenseType save(LicenseType licenseType) {
        LicenseTypeEntity entity = licenseTypeMapper.toEntity(licenseType);
        return licenseTypeMapper.toDomain(licenseTypeEntityRepository.save(entity));
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<LicenseType> findById(LicenseTypeId id) {
        return licenseTypeEntityRepository.findById(id.getIdentificador().getValor())
                .map(licenseTypeMapper::toDomain);
    }

    @Override
    @Transactional(readOnly = true)
    public List<LicenseType> findAll() {
        return licenseTypeEntityRepository.findAllByActiveTrue().stream()
                .map(licenseTypeMapper::toDomain)
                .toList();
    }

    @Override
    @Transactional(readOnly = true)
    public List<LicenseType> findAll(LicenseTypeFilter filter) {
        var pageable = PageRequest.of(
                filter.getPageNumber() != null ? filter.getPageNumber() : 0,
                filter.getPageSize() != null ? filter.getPageSize() : 20
        );

        Specification<LicenseTypeEntity> spec = (root, query, cb) -> {
            var predicates = cb.conjunction();

            if (filter.getName() != null && !filter.getName().isBlank()) {
                predicates = cb.and(predicates,
                        cb.like(cb.lower(root.get("name")), "%" + filter.getName().trim().toLowerCase() + "%"));
            }

            if (filter.getCode() != null && !filter.getCode().isBlank()) {
                predicates = cb.and(predicates,
                        cb.equal(root.get("code"), filter.getCode().trim()));
            }

            if (filter.getLicensingModel() != null && !filter.getLicensingModel().isBlank()) {
                predicates = cb.and(predicates,
                        cb.equal(root.get("licensingModelKey"), filter.getLicensingModel().trim()));
            }

            if (filter.getCategoryId() != null) {
                predicates = cb.and(predicates,
                        cb.equal(root.get("categoryId").get("id"), filter.getCategoryId().getValor()));
            }

            if (filter.isRenewable()) {
                predicates = cb.and(predicates,
                        cb.equal(root.get("renewable"), true));
            }

            // Ativo por padrão: true. Só mostra inativos se filter.isActive() for false
            boolean activeFilter = filter.isActive();
            predicates = cb.and(predicates, cb.equal(root.get("active"), activeFilter));

            return predicates;
        };

        var page = licenseTypeEntityRepository.findAll(spec, pageable);
        return page.stream()
                .map(licenseTypeMapper::toDomain)
                .toList();
    }

    @Override
    public void delete(LicenseTypeId id) {
        throw new UnsupportedOperationException("Delete não implementado ainda");
    }

    @Override
    @Transactional(readOnly = true)
    public boolean existsByCode(String code) {
        return licenseTypeEntityRepository.existsByCode(code);
    }
}
