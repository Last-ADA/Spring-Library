package com.ada.grupo3.dto.request;

import java.util.List;

public record LivroRequestDTO(String isbn13,
                              String nome,
                              Integer numPaginas,
                              String descricao,
                              Boolean disponivel,
                              Long autorId,
                              String editoraCnpj,
                              List<Long> categoriasId) {
}
