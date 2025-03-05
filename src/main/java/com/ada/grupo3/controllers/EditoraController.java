package com.ada.grupo3.controllers;


import com.ada.grupo3.dto.request.EditoraRequestDTO;
import com.ada.grupo3.dto.response.EditoraDetailsResponseDTO;
import com.ada.grupo3.service.EditoraService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/editoras")
public class EditoraController {

    private final EditoraService editoraService;

    public EditoraController(EditoraService editoraService) {
        this.editoraService = editoraService;
    }

    @Operation(summary = "Retorna editoras baseado no nome", description = "Retorna as editoras que contém o nome providenciado. Caso o nome não seja providenciado, retorna todas as editoras.")
    @ApiResponse(responseCode = "200", description = "Operação realizada com sucesso")
    @GetMapping
    public ResponseEntity<List<EditoraDetailsResponseDTO>> findByNome(@RequestParam(required = false) String nome) {
        List<EditoraDetailsResponseDTO> editoras;
        if (nome != null) {
            editoras = editoraService.findByNome(nome);
        } else {
            editoras = editoraService.findAll();
        }
        return ResponseEntity.ok(editoras);
    }

    @Operation(summary = "Retorna uma editora por CNPJ", description = "Retorna as informaçoes de uma editora, dado seu CNPJ")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Operação realizada com sucesso"),
            @ApiResponse(responseCode = "404", description = "Editora não encontrada")
    })
    @GetMapping("/{editoraCnpj}")
    public ResponseEntity<EditoraDetailsResponseDTO> findByCnpj(@PathVariable String editoraCnpj) {
        var editora = editoraService.findByCnpj(editoraCnpj);
        return ResponseEntity.ok(editora);
    }

    @Operation(summary = "Cria uma nova editora", description = "Cria uma nova editora com as informações passadas no DTO")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Editora criada com sucesso"),
            @ApiResponse(responseCode = "400", description = "Erro de validação")
    })
    @PostMapping
    public ResponseEntity<?> createEditora(@RequestBody EditoraRequestDTO editoraRequestDTO) {
        editoraService.create(editoraRequestDTO);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @Operation(summary = "Atualiza uma editora por CNPJ", description = "Atualiza uma editora por CNPJ")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "Editora atualizada com sucesso"),
            @ApiResponse(responseCode = "404", description = "Editora não encontrada")
    })
    @PutMapping("/{editoraCnpj}")
    public ResponseEntity<?> updateEditora(@PathVariable String editoraCnpj, @RequestBody EditoraRequestDTO editoraRequestDTO) {
        editoraService.updateAll(editoraCnpj, editoraRequestDTO);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }

    @Operation(summary = "Deleta uma editora por CNPJ", description = "Deleta uma editora por CNPJ")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "Editora deletada com sucesso"),
            @ApiResponse(responseCode = "404", description = "Editora não encontrada")
    })
    @DeleteMapping("/{editoraCnpj}")
    public ResponseEntity<?> deleteEditora(@PathVariable String editoraCnpj) {
        editoraService.deleteByCnpj(editoraCnpj);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }




}
