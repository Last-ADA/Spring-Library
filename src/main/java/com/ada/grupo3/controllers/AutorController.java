package com.ada.grupo3.controllers;


import com.ada.grupo3.dto.request.AutorRequestDTO;
import com.ada.grupo3.dto.response.AutorDetailsResponseDTO;
import com.ada.grupo3.dto.response.IdResponseDTO;
import com.ada.grupo3.service.AutorService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/autores")
public class AutorController {

    private final AutorService autorService;

    public AutorController(AutorService autorService) {
        this.autorService = autorService;
    }

    @Operation(summary = "Retorna um autor por id", description = "Retorna as informações de um autor, dado um Id")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Operação realizada com sucesso"),
            @ApiResponse(responseCode = "404", description = "Autor não encontrado")
    })
    @GetMapping("/{autorId}")
    public ResponseEntity<AutorDetailsResponseDTO> findById(@PathVariable(name = "autorId") Long id) {
        var autorDTO = autorService.findById(id);
        return ResponseEntity.ok(autorDTO);
    }

    @Operation(summary = "Retorna todos os autores", description = "Retorna todos os autores")
    @ApiResponse(responseCode = "200", description = "Operação realizada com sucesso")
    @GetMapping
    public ResponseEntity<List<AutorDetailsResponseDTO>> listAllAutores() {
        var autoresDTO = autorService.listAll();
        return ResponseEntity.ok(autoresDTO);
    }

    @Operation(summary = "Cria um novo autor", description = "Cria um novo autor com as informações passadas no DTO")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Autor criado com sucesso"),
            @ApiResponse(responseCode = "400", description = "Erro de validação")
    })
    @PostMapping
    public ResponseEntity<IdResponseDTO> createAutor(@RequestBody AutorRequestDTO autorRequestDTO) {
        var autor = autorService.create(autorRequestDTO);
        var autorResponse = new IdResponseDTO(autor.getId());
        return ResponseEntity.status(HttpStatus.CREATED).body(autorResponse);
    }

    @Operation(summary = "Atualiza um autor", description = "Atualiza um autor por Id")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "Autor atualizado com sucesso"),
            @ApiResponse(responseCode = "404", description = "Autor não encontrado")
    })
    @PutMapping("/{autorId}")
    public ResponseEntity<?> updateAutor(@PathVariable Long autorId, @RequestBody AutorRequestDTO autorRequestDTO) {
        autorService.updateAll(autorId, autorRequestDTO);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }

    @Operation(summary = "Deleta um autor", description = "Deleta um autor por Id")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "Autor deletado com sucesso"),
            @ApiResponse(responseCode = "404", description = "Autor não encontrado")
    })
    @DeleteMapping("/{autorId}")
    public ResponseEntity<?> deleteAutor(@PathVariable Long autorId) {
        autorService.deleteById(autorId);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }

}
