package com.ada.grupo3.controllers;


import com.ada.grupo3.dto.request.UsuarioRequestDTO;
import com.ada.grupo3.dto.response.IdResponseDTO;
import com.ada.grupo3.dto.response.UsuarioDetailsResponseDTO;
import com.ada.grupo3.service.UsuarioService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/usuarios")
public class UsuarioController {
    private final UsuarioService usuarioService;

    public UsuarioController(UsuarioService usuarioService) { this.usuarioService = usuarioService;}

    @Operation(summary = "Retorna uma lista de usuários", description = "Retorna uma lista com todos os usuários")
    @ApiResponse(responseCode = "200", description = "Usuários encontrados com sucesso")
    @GetMapping
    public ResponseEntity<List<UsuarioDetailsResponseDTO>> findAll() {
        var users = usuarioService.findAll();
        return ResponseEntity.ok(users);
    }

    @Operation(summary = "Retorna um usuário por ID", description = "Retorna um usuário por ID")
    @ApiResponses( value = {
            @ApiResponse(responseCode = "200", description = "Usuário encontrado com sucesso"),
            @ApiResponse(responseCode = "404", description = "Usuário não encontrado")
    })
    @GetMapping("/{usuarioId}")
    public ResponseEntity<UsuarioDetailsResponseDTO> findById(@PathVariable Long usuarioId) {
        var user = usuarioService.findById(usuarioId);
        return ResponseEntity.ok(user);
    }

    @Operation(summary = "Cria um novo usuário", description = "Cria um novo usuário")
    @ApiResponses( value = {
            @ApiResponse(responseCode = "201", description = "Usuário criado"),
            @ApiResponse(responseCode = "400", description = "Erro de validação")
    })
    @PostMapping
    public ResponseEntity<?> createUser(@RequestBody UsuarioRequestDTO usuarioRequestDTO) {
        var usuario = usuarioService.create(usuarioRequestDTO);
        var usuarioResponse = new IdResponseDTO(usuario.getId());
        return ResponseEntity.status(HttpStatus.CREATED).body(usuarioResponse);
    }

    @Operation(summary = "Atualiza um usuário", description = "Atualiza um usuário")
    @ApiResponses( value = {
            @ApiResponse(responseCode = "204", description = "Usuário atualizado"),
            @ApiResponse(responseCode = "404", description = "Usuário não encontrado")
    })
    @PutMapping("/{usuarioId}")
    public ResponseEntity<?> updateUser(@PathVariable Long usuarioId, @RequestBody UsuarioRequestDTO usuarioRequestDTO) {
        usuarioService.updateAll(usuarioId, usuarioRequestDTO);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }

    @Operation(summary = "Deleta um usuário por Id", description = "Deleta um usuário por Id")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "Usuário deletado"),
            @ApiResponse(responseCode = "404", description = "Usuário não encontrado")
    })
    @DeleteMapping("/{usuarioId}")
    public ResponseEntity<?> deleteUser(@PathVariable Long usuarioId) {
        usuarioService.deleteUsuario(usuarioId);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }

}
