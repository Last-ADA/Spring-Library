package com.ada.grupo3.dto.request;

import com.ada.grupo3.entity.enums.TipoUserEnum;

public record UsuarioRequestDTO(
        String nome,
        String senha,
        TipoUserEnum tipo
) {}
