package cv.igrp.license.configuration.application.commands;

import cv.igrp.framework.core.domain.CommandHandler;
import cv.igrp.framework.stereotype.IgrpCommandHandler;
import cv.igrp.license.configuration.application.dto.OptionResponseDTO;
import cv.igrp.license.configuration.domain.models.Option;
import cv.igrp.license.configuration.domain.repository.OptionRepository;
import cv.igrp.license.configuration.infrastructure.mappers.OptionMapper;
import cv.igrp.license.shared.domain.valueobject.Metadata;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;



@Component
public class CreateOptionsCommandHandler implements CommandHandler<CreateOptionsCommand, ResponseEntity<OptionResponseDTO>> {

   private static final Logger LOGGER = LoggerFactory.getLogger(CreateOptionsCommandHandler.class);

  private final OptionRepository optionRepository;
  private final OptionMapper optionMapper;

  public CreateOptionsCommandHandler(OptionRepository optionRepository, OptionMapper optionMapper) {
    this.optionRepository = optionRepository;
    this.optionMapper = optionMapper;
  }

  @IgrpCommandHandler
  public ResponseEntity<OptionResponseDTO> handle(CreateOptionsCommand command) {
    var dto = command.getOptionrequest();

    // 1. Verificar se já existe Option com mesmo ccode + ckey (regra típica para opções)
    //if (optionRepository.existsByCcodeAndCkey(dto.getCcode(), dto.getCkey())) {
      //throw IgrpResponseStatusException.badRequest(
       //   "Option with code '" + dto.getCcode() + "' and key '" + dto.getCkey() + "' already exists");
    //}

    Metadata metadata = Metadata.fromMap(dto.getMetadata());

    // 2. Criar o objeto de domínio
    var option = Option.criarNova(
        dto.getCcode(),
        dto.getCkey(),
        dto.getCvalue(),
        dto.getLocale(),
        dto.getSort_order(),
        metadata,
        ""
    );

    // 3. Persistir via repositório
    var savedOption = optionRepository.save(option);

    // 4. Converter para DTO de resposta
    var responseDTO = optionMapper.toResponseDTO(savedOption);

    return ResponseEntity.ok(responseDTO);
  }

}
