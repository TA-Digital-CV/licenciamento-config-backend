package cv.igrp.license.configuration.application.commands;

import cv.igrp.framework.core.domain.CommandHandler;
import cv.igrp.framework.stereotype.IgrpCommandHandler;
import cv.igrp.license.configuration.application.dto.OptionResponseDTO;
import cv.igrp.license.configuration.domain.repository.OptionRepository;
import cv.igrp.license.configuration.domain.valueobject.OptionId;
import cv.igrp.license.configuration.infrastructure.mappers.OptionMapper;
import cv.igrp.license.shared.domain.exceptions.IgrpResponseStatusException;
import cv.igrp.license.shared.domain.valueobject.Metadata;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;



@Component
public class UpdateOptionCommandHandler implements CommandHandler<UpdateOptionCommand, ResponseEntity<OptionResponseDTO>> {

   private static final Logger LOGGER = LoggerFactory.getLogger(UpdateOptionCommandHandler.class);

   private final OptionRepository optionRepository;
   private final OptionMapper optionMapper;

  public UpdateOptionCommandHandler(OptionRepository optionRepository, OptionMapper optionMapper) {
    this.optionRepository = optionRepository;
    this.optionMapper = optionMapper;
  }

  @IgrpCommandHandler
  public ResponseEntity<OptionResponseDTO> handle(UpdateOptionCommand command) {
    var dto = command.getOptionrequest();
    var optionId = OptionId.from(command.getOptionId());

    // 1. Buscar a Option existente
    var existing = optionRepository.findById(optionId)
        .orElseThrow(() -> IgrpResponseStatusException.notFound(
            "Option not found for id: " + command.getOptionId()));

    // 2. Validar se existe outra Option com mesmo ccode + ckey (caso alterados)
    //    if ((!existing.getCcode().equals(dto.getCcode()) || !existing.getCkey().equals(dto.getCkey())) &&
    //        optionRepository.existsByCcodeAndCkey(dto.getCcode(), dto.getCkey())) {
    //      throw IgrpResponseStatusException.badRequest(
    //          "Option with code '" + dto.getCcode() + "' and key '" + dto.getCkey() + "' already exists");
    //    }
    var metadata = Metadata.fromMap(dto.getMetadata());
    // 3. Atualizar atributos principais
    existing.atualizar(
        dto.getCcode(),
        dto.getCkey(),
        dto.getCvalue(),
        dto.getLocale(),
        dto.getSort_order(),
        metadata,
        ""
    );

    // 4. Persistir a atualização
    var saved = optionRepository.save(existing);

    // 5. Converter para DTO de resposta
    OptionResponseDTO responseDTO = optionMapper.toResponseDTO(saved);

    return ResponseEntity.ok(responseDTO);
  }

}
