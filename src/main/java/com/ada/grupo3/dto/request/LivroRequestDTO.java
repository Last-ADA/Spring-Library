package com.ada.grupo3.dto.request;

public record LivroRequestDTO(String isbn13,
                              String nome,
                              Integer numPaginas,
                              String descricao,
                              Long autorId,
                              String editoraCnpj) {
}
