package com.ada.grupo3.dto.response;

import com.ada.grupo3.entity.enums.TipoUserEnum;

public record UsuarioDetailsResponseDTO (
        Long id,
        String nome,
        TipoUserEnum tipo
) {}
