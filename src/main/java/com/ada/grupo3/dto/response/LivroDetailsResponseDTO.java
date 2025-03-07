package com.ada.grupo3.dto.response;

import java.util.List;

public record LivroDetailsResponseDTO(String isbn13,
                                      String nome,
                                      Integer numPaginas,
                                      String descricao,
                                      Boolean disponivel,
                                      String autorNome,
                                      String editoraNome,
                                      List<String> nomesCategorias) {
}
