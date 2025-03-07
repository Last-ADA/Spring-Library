package com.ada.grupo3.controllers;

import com.ada.grupo3.dto.request.CategoriaRequestDTO;
import com.ada.grupo3.dto.response.CategoriaDetailsResponseDTO;
import com.ada.grupo3.dto.response.IdResponseDTO;
import com.ada.grupo3.service.CategoriaService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/categorias")
public class CategoriaController {

    private final CategoriaService categoriaService;

    public CategoriaController(CategoriaService categoriaService) {
        this.categoriaService = categoriaService;
    }

    @Operation(summary = "Retorna lista com todas as categorias", description = "Retorna lista com todas as categorias")
    @ApiResponse(responseCode = "200", description = "Operação realizada com sucesso")
    @GetMapping
    public ResponseEntity<List<CategoriaDetailsResponseDTO>> listAllCategorias() {
        var categoriasDTO = categoriaService.listAll();
        return ResponseEntity.ok(categoriasDTO);
    }

    @Operation(summary = "Cria uma nova categoria", description = "Cria uma nova categoria")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Categoria criada com sucesso"),
            @ApiResponse(responseCode = "400", description = "Erro de validação")
    })
    @PostMapping
    public ResponseEntity<IdResponseDTO> createCategoria(@RequestBody CategoriaRequestDTO categoriaRequestDTO) {
        var categoria = categoriaService.create(categoriaRequestDTO);
        var categoriaResponse = new IdResponseDTO(categoria.getId());
        return ResponseEntity.status(HttpStatus.CREATED).body(categoriaResponse);
    }

    @Operation(summary = "Atualiza uma categoria", description = "Atualiza uma nova categoria")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "Categoria atualizada com sucesso"),
            @ApiResponse(responseCode = "404", description = "Categoria não encontrada")
    })
    @PutMapping("/{categoriaId}")
    public ResponseEntity<?> updateCategoria(@PathVariable Long categoriaId, @RequestBody CategoriaRequestDTO categoriaRequestDTO) {
        categoriaService.updateAll(categoriaId, categoriaRequestDTO);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }


    @Operation(summary = "Deleta uma categoria por Id", description = "Deleta uma categoria por Id")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "Categoria deletada com sucesso"),
            @ApiResponse(responseCode = "404", description = "Categoria não encontrada")
    })
    @DeleteMapping("/{categoriaId}")
    public ResponseEntity<?> deleteCategoria(@PathVariable Long categoriaId) {
        categoriaService.deleteById(categoriaId);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }

}
