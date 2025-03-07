package com.ada.grupo3.dto.response;

import java.util.List;

public record CategoriaDetailsResponseDTO (
        Long id,
        String nome,
        String descricao,
        List<String> nomesLivros

)
{}
