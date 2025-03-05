package com.ada.grupo3.dto.response;

public record LivroDetailsResponseDTO(String isbn13,
                                      String nome,
                                      Integer numPaginas,
                                      String descricao,
                                      Boolean disponivel,
                                      String autorNome,
                                      String editoraNome) {
}
