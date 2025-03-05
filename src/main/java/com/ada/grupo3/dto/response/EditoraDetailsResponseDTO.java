package com.ada.grupo3.dto.response;

import java.util.List;

public record EditoraDetailsResponseDTO(String cnpj,
                                        String nome,
                                        String telefone,
                                        String email,
                                        List<String> nomesLivros) {
}
