package com.ada.grupo3.dto.response;

import java.util.List;

public record AutorDetailsResponseDTO(Long id,
                                      String nome,
                                      String dataNascimento,
                                      String email,
                                      List<String> nomesLivros) {
}
