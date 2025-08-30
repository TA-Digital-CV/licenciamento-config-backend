/* THIS FILE WAS GENERATED AUTOMATICALLY BY iGRP STUDIO. */
/* DO NOT MODIFY IT BECAUSE IT COULD BE REWRITTEN AT ANY TIME. */

package cv.igrp.license.configuration.interfaces.rest;

import cv.igrp.framework.stereotype.IgrpController;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.http.HttpStatus;
import io.swagger.v3.oas.annotations.tags.Tag;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.media.ExampleObject;
import jakarta.validation.Valid;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import cv.igrp.framework.core.domain.CommandBus;
import cv.igrp.framework.core.domain.QueryBus;
import cv.igrp.license.configuration.application.commands.*;
import cv.igrp.license.configuration.application.queries.*;


import cv.igrp.license.configuration.application.dto.WrapperListSectorDTO;
import cv.igrp.license.configuration.application.dto.SectorRequestDTO;
import cv.igrp.license.configuration.application.dto.SectorResponseDTO;
import java.util.Map;
import java.util.Collection;

@IgrpController
@RestController
@RequestMapping(path = "api/v1/sectors")
@Tag(name = "Sector", description = "gestao de sectores")
public class SectorController {

  private static final Logger LOGGER = LoggerFactory.getLogger(SectorController.class);


  private final CommandBus commandBus;
  private final QueryBus queryBus;


  public SectorController(
    CommandBus commandBus, QueryBus queryBus
  ) {
    this.commandBus = commandBus;
    this.queryBus = queryBus;
  }

  @GetMapping(
  )
  @Operation(
    summary = "GET method to handle operations for getListSectors",
    description = "GET method to handle operations for getListSectors",
    responses = {
      @ApiResponse(
          responseCode = "200",
          description = "",
          content = @Content(
              mediaType = "application/json",
              schema = @Schema(
                  implementation = WrapperListSectorDTO.class,
                  type = "object")
          )
      )
    }
  )

  public ResponseEntity<WrapperListSectorDTO> getListSectors(
    @RequestParam(value = "sectorType", required = false) String sectorType,
    @RequestParam(value = "name", required = false) String name,
    @RequestParam(value = "code", required = false) String code,
    @RequestParam(value = "active", required = false, defaultValue = "true") boolean active,
    @RequestParam(value = "pageNumber", defaultValue = "0") String pageNumber,
    @RequestParam(value = "pageSize", defaultValue = "20") String pageSize)
  {

      LOGGER.debug("Operation started");

      final var query = new GetListSectorsQuery(sectorType, name, code, active, pageNumber, pageSize);

      ResponseEntity<WrapperListSectorDTO> response = queryBus.handle(query);

      LOGGER.debug("Operation finished");

      return ResponseEntity.status(response.getStatusCode())
              .headers(response.getHeaders())
              .body(response.getBody());
  }

  @PostMapping(
  )
  @Operation(
    summary = "POST method to handle operations for createSector",
    description = "POST method to handle operations for createSector",
    responses = {
      @ApiResponse(
          responseCode = "200",
          description = "",
          content = @Content(
              mediaType = "application/json",
              schema = @Schema(
                  implementation = SectorResponseDTO.class,
                  type = "object")
          )
      )
    }
  )

  public ResponseEntity<SectorResponseDTO> createSector(@Valid @RequestBody SectorRequestDTO createSectorRequest
    )
  {

      LOGGER.debug("Operation started");

      final var command = new CreateSectorCommand(createSectorRequest);

       ResponseEntity<SectorResponseDTO> response = commandBus.send(command);

       LOGGER.debug("Operation finished");

        return ResponseEntity.status(response.getStatusCode())
              .headers(response.getHeaders())
              .body(response.getBody());
  }

  @PutMapping(
    value = "{sectorId}"
  )
  @Operation(
    summary = "PUT method to handle operations for updateSector",
    description = "PUT method to handle operations for updateSector",
    responses = {
      @ApiResponse(
          responseCode = "200",
          description = "",
          content = @Content(
              mediaType = "application/json",
              schema = @Schema(
                  implementation = SectorResponseDTO.class,
                  type = "object")
          )
      )
    }
  )

  public ResponseEntity<SectorResponseDTO> updateSector(@Valid @RequestBody SectorRequestDTO updateSectorRequest
    , @PathVariable(value = "sectorId") String sectorId)
  {

      LOGGER.debug("Operation started");

      final var command = new UpdateSectorCommand(updateSectorRequest, sectorId);

       ResponseEntity<SectorResponseDTO> response = commandBus.send(command);

       LOGGER.debug("Operation finished");

        return ResponseEntity.status(response.getStatusCode())
              .headers(response.getHeaders())
              .body(response.getBody());
  }

  @GetMapping(
    value = "{sectorId}"
  )
  @Operation(
    summary = "GET method to handle operations for getSectorById",
    description = "GET method to handle operations for getSectorById",
    responses = {
      @ApiResponse(
          responseCode = "200",
          description = "",
          content = @Content(
              mediaType = "application/json",
              schema = @Schema(
                  implementation = SectorResponseDTO.class,
                  type = "object")
          )
      )
    }
  )

  public ResponseEntity<SectorResponseDTO> getSectorById(
    @PathVariable(value = "sectorId") String sectorId)
  {

      LOGGER.debug("Operation started");

      final var query = new GetSectorByIdQuery(sectorId);

      ResponseEntity<SectorResponseDTO> response = queryBus.handle(query);

      LOGGER.debug("Operation finished");

      return ResponseEntity.status(response.getStatusCode())
              .headers(response.getHeaders())
              .body(response.getBody());
  }

  @PatchMapping(
    value = "{sectorId}/ativar"
  )
  @Operation(
    summary = "PATCH method to handle operations for enableSector",
    description = "PATCH method to handle operations for enableSector",
    responses = {
      @ApiResponse(
          responseCode = "200",
          description = "",
          content = @Content(
              mediaType = "application/json",
              schema = @Schema(
                  implementation = String.class,
                  type = "String")
          )
      )
    }
  )

  public ResponseEntity<Map<String, ?>> enableSector(
    @PathVariable(value = "sectorId") String sectorId)
  {

      LOGGER.debug("Operation started");

      final var command = new EnableSectorCommand(sectorId);

       ResponseEntity<Map<String, ?>> response = commandBus.send(command);

       LOGGER.debug("Operation finished");

        return ResponseEntity.status(response.getStatusCode())
              .headers(response.getHeaders())
              .body(response.getBody());
  }

  @PatchMapping(
    value = "{sectorId}/desativar"
  )
  @Operation(
    summary = "PATCH method to handle operations for disableSector",
    description = "PATCH method to handle operations for disableSector",
    responses = {
      @ApiResponse(
          responseCode = "200",
          description = "",
          content = @Content(
              mediaType = "application/json",
              schema = @Schema(
                  implementation = String.class,
                  type = "String")
          )
      )
    }
  )

  public ResponseEntity<Map<String, ?>> disableSector(
    @PathVariable(value = "sectorId") String sectorId)
  {

      LOGGER.debug("Operation started");

      final var command = new DisableSectorCommand(sectorId);

       ResponseEntity<Map<String, ?>> response = commandBus.send(command);

       LOGGER.debug("Operation finished");

        return ResponseEntity.status(response.getStatusCode())
              .headers(response.getHeaders())
              .body(response.getBody());
  }

}
