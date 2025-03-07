package com.ada.grupo3.controllers;

import com.ada.grupo3.dto.request.LivroRequestDTO;
import com.ada.grupo3.dto.response.LivroDetailsResponseDTO;
import com.ada.grupo3.service.LivroService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/livros")
public class LivroController {

    private final LivroService livroService;

    public LivroController(LivroService livroService) {
        this.livroService = livroService;
    }


    @Operation(summary = "Procura livros", description = "Procura livros por título, nome do autor, nome da editora e disponibilidades. Na ausência de parâmetros, retorna todos os livros.")
    @ApiResponse(responseCode = "200", description = "Operação realizada com sucesso")
    @GetMapping
    public ResponseEntity<List<LivroDetailsResponseDTO>> multiParamSearch(@RequestParam(required = false) String titulo,
                                                                          @RequestParam(required = false) String autorNome,
                                                                          @RequestParam(required = false) String editoraNome,
                                                                          @RequestParam(required = false) Boolean disponivel,
                                                                          @RequestParam(required = false) String categoria) {
        List<LivroDetailsResponseDTO> livros = livroService.listAll();
        if (titulo != null) {
            livros.retainAll(livroService.findByTitulo(titulo));
        }
        if (autorNome != null) {
            livros.retainAll(livroService.findByAutorNome(autorNome));
        }
        if (editoraNome != null) {
            livros.retainAll(livroService.findByEditoraNome(editoraNome));
        }
        if (disponivel != null) {
            livros.retainAll(livroService.findByDisponivel(disponivel));
        }
        if (categoria != null) {
            livros.retainAll(livroService.findByCategoria(categoria));
        }
        return ResponseEntity.ok(livros);
    }

    @Operation(summary = "Procura livro por Isbn", description = "Procura livro por Isbn 13 exato.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Operação realizada com sucesso"),
            @ApiResponse(responseCode = "404", description = "Isbn não encontrado")
    })
    @GetMapping("/{isbn13}")
    public ResponseEntity<LivroDetailsResponseDTO> findByIsbn13(@PathVariable String isbn13) {
        var livro = livroService.findByIsbn13(isbn13);
        return ResponseEntity.ok(livro);
    }

    @Operation(summary = "Cria um livro", description = "Cria um livro, com isbn13, título, páginas, descrição, autor e editora associados")
    @ApiResponse(responseCode = "204", description = "Livro criado com sucesso")
    @PostMapping
    public ResponseEntity<?> createLivro(@RequestBody LivroRequestDTO livroRequestDTO){
        livroService.create(livroRequestDTO);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @Operation(summary = "Atualiza um livro por ISBN", description = "Atualiza todas as informações de um livro, dado seu ISBN")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "Livro atualizado com sucesso"),
            @ApiResponse(responseCode = "404", description = "Livro não encontrado")
    })
    @PutMapping("/{isbn13}")
    public ResponseEntity<?> updateLivro(@PathVariable String isbn13, @RequestBody LivroRequestDTO livroRequestDTO) {
        livroService.updateAll(isbn13, livroRequestDTO);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }

    @Operation(summary = "Deleta um livro por ISBN", description = "Deleta um livro por ISBN13")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "Livro deletado com sucesso"),
            @ApiResponse(responseCode = "404", description = "Livro não encontrado")
    })
    @DeleteMapping("/{isbn13}")
    public ResponseEntity<?> deleteLivro(@PathVariable String isbn13) {
        livroService.deleteByIsbn13(isbn13);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }
}
