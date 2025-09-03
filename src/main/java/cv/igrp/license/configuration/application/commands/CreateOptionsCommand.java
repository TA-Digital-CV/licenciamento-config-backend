package cv.igrp.license.configuration.application.commands;

import cv.igrp.framework.core.domain.Command;
import cv.igrp.license.configuration.application.dto.OptionRequestDTO;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CreateOptionsCommand implements Command {


  private OptionRequestDTO optionrequest;

}
