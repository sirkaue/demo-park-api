package com.sirkaue.demoparkapi.web.controller;

import com.sirkaue.demoparkapi.entity.ClienteVaga;
import com.sirkaue.demoparkapi.jwt.JwtUserDetails;
import com.sirkaue.demoparkapi.repository.projection.ClienteVagaProjection;
import com.sirkaue.demoparkapi.service.ClienteVagaService;
import com.sirkaue.demoparkapi.service.EstacionamentoService;
import com.sirkaue.demoparkapi.web.dto.EstacionamentoCreateDto;
import com.sirkaue.demoparkapi.web.dto.EstacionamentoResponseDto;
import com.sirkaue.demoparkapi.web.dto.PageableDto;
import com.sirkaue.demoparkapi.web.dto.mapper.ClienteVagaMapper;
import com.sirkaue.demoparkapi.web.dto.mapper.PageableMapper;
import com.sirkaue.demoparkapi.web.exception.ErrorMessage;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.headers.Header;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;

import static io.swagger.v3.oas.annotations.enums.ParameterIn.PATH;
import static io.swagger.v3.oas.annotations.enums.ParameterIn.QUERY;

@Tag(name = "Estacionamentos", description = "Operações de registros de entrada e saída de um veículo do estacionamento.")
@RestController
@RequestMapping("api/v1/estacionamentos")
public class EstacionamentoController {

    @Autowired
    private EstacionamentoService estacionamentoService;

    @Autowired
    private ClienteVagaService clienteVagaService;

    @Operation(summary = "Operação de check-in", description = "Recurso para dar entrada de um veículo no estacionamento." +
            "Requisição exige um Bearer Token, Acesso restrito a Role='ADMIN'",
            security = @SecurityRequirement(name = "security"),
            responses = {
                    @ApiResponse(
                            responseCode = "201",
                            description = "Recurso criado com sucesso",
                            headers = @Header(name = HttpHeaders.LOCATION, description = "URL de acesso ao recurso criado"),
                            content = @Content(mediaType = "application/json",
                                    schema = @Schema(implementation = EstacionamentoResponseDto.class))),
                    @ApiResponse(
                            responseCode = "404",
                            description = "Causas possiveis: <br/>" +
                                    "- CPF do cliente não cadastrado no sistema; <br/>" +
                                    "- Nenhuma vaga livre foi localizada;",
                            content = @Content(mediaType = "application/json",
                                    schema = @Schema(implementation = ErrorMessage.class))
                    ),
                    @ApiResponse(
                            responseCode = "403",
                            description = "Recurso não permitido ao perfil de CLIENTE",
                            content = @Content(mediaType = "application/json",
                                    schema = @Schema(implementation = ErrorMessage.class))),
                    @ApiResponse(
                            responseCode = "422",
                            description = "Recurso não processado por falta de dados ou dados inválidos",
                            content = @Content(mediaType = "application/json",
                                    schema = @Schema(implementation = ErrorMessage.class)))
            })
    @PostMapping("/check-in")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<EstacionamentoResponseDto> checkIn(@RequestBody @Valid EstacionamentoCreateDto dto) {
        ClienteVaga clienteVaga = ClienteVagaMapper.toClienteVaga(dto);
        estacionamentoService.checkIn(clienteVaga);
        EstacionamentoResponseDto responseDto = ClienteVagaMapper.toDto(clienteVaga);

        URI location = ServletUriComponentsBuilder
                .fromCurrentRequestUri()
                .path("/{recibo}")
                .buildAndExpand(clienteVaga.getRecibo())
                .toUri();
        return ResponseEntity.created(location).body(responseDto);
    }

    @Operation(summary = "Operação de buscar veículo estacionado",
            description = "Recurso para retornar um veículo estacionado pelo n° de recibo. Requisição exige" +
                    " um Bearer Token. Acesso restrito a roles: ADMIN e CLIENTE.",
            security = @SecurityRequirement(name = "security"),
            responses = {
                    @ApiResponse(
                            responseCode = "200",
                            description = "Recurso buscado com sucesso",
                            content = @Content(mediaType = "application/json",
                                    schema = @Schema(implementation = EstacionamentoResponseDto.class))
                    ),
                    @ApiResponse(
                            responseCode = "404",
                            description = "Número do recibo não encontrado no sistema.",
                            content = @Content(mediaType = "application/json",
                                    schema = @Schema(implementation = ErrorMessage.class))
                    )
            })
    @GetMapping("/check-in/{recibo}")
    @PreAuthorize("hasAnyRole('ADMIN', 'CLIENTE')")
    public ResponseEntity<EstacionamentoResponseDto> getByRecibo(@PathVariable String recibo) {
        ClienteVaga clienteVaga = clienteVagaService.buscarPorRecibo(recibo);
        EstacionamentoResponseDto dto = ClienteVagaMapper.toDto(clienteVaga);
        return ResponseEntity.ok(dto);
    }


    @Operation(summary = "Operação de check-out",
            description = "Recurso para realizar o check-out de um veículo no estacionamento. " +
                    "Requisição exige um Bearer Token. Acesso restrito a Role='ADMIN'.",
            security = @SecurityRequirement(name = "security"),
            parameters = @Parameter(in = PATH, name = "recibo", description = "Número de recibo gerado pelo check-in"),
            responses = {
                    @ApiResponse(
                            responseCode = "200",
                            description = "Check-out realizado com sucesso",
                            content = @Content(mediaType = "application/json",
                                    schema = @Schema(implementation = EstacionamentoResponseDto.class))
                    ),
                    @ApiResponse(
                            responseCode = "404",
                            description = "Recibo não encontrado no sistema ou check-out já realizado",
                            content = @Content(mediaType = "application/json",
                                    schema = @Schema(implementation = ErrorMessage.class))
                    ),
                    @ApiResponse(
                            responseCode = "403",
                            description = "Recurso não permitido ao perfil de CLIENTE",
                            content = @Content(mediaType = "application/json",
                                    schema = @Schema(implementation = ErrorMessage.class))
                    )
            })
    @PatchMapping("/check-out/{recibo}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<EstacionamentoResponseDto> checkOut(@PathVariable String recibo) {
        ClienteVaga clienteVaga = estacionamentoService.checkOut(recibo);
        EstacionamentoResponseDto dto = ClienteVagaMapper.toDto(clienteVaga);
        return ResponseEntity.ok(dto);
    }

    @Operation(summary = "Localizar os registros de estacionamentos do cliente pelo CPF",
            description = "Localizar os registros de estacionamentos do cliente pelo CPF." +
                    "Requisição exige um Bearer Token. Acesso restrito a Role='ADMIN'.",
            security = @SecurityRequirement(name = "security"),
            parameters = {
                    @Parameter(in = PATH, name = "cpf", description = "N° do CPF referente ao cliente a ser consultado",
                            required = true),
                    @Parameter(in = QUERY, name = "page", description = "Representa a página retornada",
                            content = @Content(schema = @Schema(type = "integer", defaultValue = "0"))),
                    @Parameter(in = QUERY, name = "size", description = "Representa o total de elementos por página",
                            content = @Content(schema = @Schema(type = "integer", defaultValue = "5"))),
                    @Parameter(in = QUERY, name = "sort", description = "Campo de ordenação padrão 'dataEntrada,asc'. ",
                            array = @ArraySchema(schema = @Schema(type = "string", defaultValue = "dataEntrada,asc")),
                            hidden = true)
            },
            responses = {
                    @ApiResponse(
                            responseCode = "200",
                            description = "Registros localizados com sucesso",
                            content = @Content(mediaType = "application/json",
                                    schema = @Schema(implementation = PageableDto.class))
                    ),
                    @ApiResponse(
                            responseCode = "403",
                            description = "Recurso não permitido ao perfil de CLIENTE",
                            content = @Content(mediaType = "application/json",
                                    schema = @Schema(implementation = ErrorMessage.class))
                    )
            })
    @GetMapping("/cpf/{cpf}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<PageableDto<ClienteVagaProjection>> getAllEstacionamentosPorCpf(
            @PathVariable String cpf,
            @PageableDefault(
                    size = 5,
                    sort = "dataEntrada",
                    direction = Sort.Direction.ASC) Pageable pageable) {
        Page<ClienteVagaProjection> projection = clienteVagaService.buscarTodosPorClienteCpf(cpf, pageable);
        return ResponseEntity.ok(PageableMapper.toDto(projection));
    }

    @GetMapping
    @PreAuthorize("hasRole('CLIENTE')")
    public ResponseEntity<PageableDto<ClienteVagaProjection>> getAllEstacionamentosDoCliente(
            @AuthenticationPrincipal JwtUserDetails user,
            @PageableDefault(
                    size = 5,
                    sort = "dataEntrada",
                    direction = Sort.Direction.ASC) Pageable pageable) {
        Page<ClienteVagaProjection> projection = clienteVagaService.buscarTodosPorUsuarioId(user.getId(), pageable);
        return ResponseEntity.ok(PageableMapper.toDto(projection));
    }
}
