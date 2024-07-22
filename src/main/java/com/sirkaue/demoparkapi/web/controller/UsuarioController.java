package com.sirkaue.demoparkapi.web.controller;

import com.sirkaue.demoparkapi.entity.Usuario;
import com.sirkaue.demoparkapi.service.UsuarioService;
import com.sirkaue.demoparkapi.web.dto.UsuarioCreateDto;
import com.sirkaue.demoparkapi.web.dto.UsuarioResponseDto;
import com.sirkaue.demoparkapi.web.dto.UsuarioSenhaDto;
import com.sirkaue.demoparkapi.web.dto.mapper.UsuarioMapper;
import com.sirkaue.demoparkapi.web.exception.ErrorMessage;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Tag(name = "Usuários", description = "Contém todas as operações relativas aos recursos para cadastro, edição e " +
        "leitura de um usuário.")
@RestController
@RequestMapping("api/v1/usuarios")
public class UsuarioController {

    @Autowired
    private UsuarioService usuarioService;

    @Operation(summary = "Adicionar um novo usuário", description = "Recurso para adicionar um novo usuário",
            responses = {
                    @ApiResponse(
                            responseCode = "201",
                            description = "Recurso criado com sucesso",
                            content = @Content(mediaType = "application/json",
                                    schema = @Schema(implementation = UsuarioResponseDto.class))),

                    @ApiResponse(
                            responseCode = "409",
                            description = "Usuário e-mail já cadastrado no sistema",
                            content = @Content(mediaType = "application/json",
                                    schema = @Schema(implementation = ErrorMessage.class))),

                    @ApiResponse(
                            responseCode = "422",
                            description = "Recursos não processado por dados de entrada inválidos",
                            content = @Content(mediaType = "application/json",
                                    schema = @Schema(implementation = ErrorMessage.class)))
            })
    @PostMapping
    public ResponseEntity<UsuarioResponseDto> create(@Valid @RequestBody UsuarioCreateDto createDto) {
        Usuario user = usuarioService.salvar(UsuarioMapper.toUsuario(createDto));
        return ResponseEntity.status(HttpStatus.CREATED).body(UsuarioMapper.toDto(user));
    }

    @Operation(summary = "Recuperar um usuário pelo id", description = "Recurso para recuperar um usuário pelo id",
            responses = {
                    @ApiResponse(
                            responseCode = "200",
                            description = "Recurso recuperado com sucesso",
                            content = @Content(mediaType = "application/json",
                                    schema = @Schema(implementation = UsuarioResponseDto.class))),

                    @ApiResponse(
                            responseCode = "404",
                            description = "Recursos não encontrado",
                            content = @Content(mediaType = "application/json",
                                    schema = @Schema(implementation = ErrorMessage.class)))
            })
    @GetMapping("/{id}")
    public ResponseEntity<UsuarioResponseDto> getById(@PathVariable Long id) {
        Usuario user = usuarioService.buscarPorId(id);
        return ResponseEntity.ok(UsuarioMapper.toDto(user));
    }

    @Operation(summary = "Atualizar senha", description = "Atualizar senha",
            responses = {
                    @ApiResponse(
                            responseCode = "204",
                            description = "Senha atualizada com sucesso",
                            content = @Content(mediaType = "application/json",
                                    schema = @Schema(implementation = Void.class))),

                    @ApiResponse(
                            responseCode = "400",
                            description = "Senha não confere",
                            content = @Content(mediaType = "application/json",
                                    schema = @Schema(implementation = ErrorMessage.class))),

                    @ApiResponse(
                            responseCode = "404",
                            description = "Recursos não encontrado",
                            content = @Content(mediaType = "application/json",
                                    schema = @Schema(implementation = ErrorMessage.class)))
            })
    @PatchMapping("/{id}")
    public ResponseEntity<Void> updatePassword(@PathVariable Long id, @Valid @RequestBody UsuarioSenhaDto dto) {
        Usuario user = usuarioService.editarSenha(id, dto.getSenhaAtual(), dto.getNovaSenha(), dto.getConfirmaSenha());
        return ResponseEntity.noContent().build();
    }

    @GetMapping
    public ResponseEntity<List<UsuarioResponseDto>> getAll() {
        List<Usuario> users = usuarioService.buscarTodos();
        return ResponseEntity.ok(UsuarioMapper.toListDto(users));
    }
}
