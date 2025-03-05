package com.ada.grupo3.mapper;

import com.ada.grupo3.dto.request.EditoraRequestDTO;
import com.ada.grupo3.dto.response.EditoraDetailsResponseDTO;
import com.ada.grupo3.entity.Editora;
import com.ada.grupo3.entity.Livro;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "spring")
public interface EditoraMapper {

    @Mapping(target = "nomesLivros", source = "livros")
    EditoraDetailsResponseDTO entityToResponse(Editora editora);

    List<EditoraDetailsResponseDTO> entityToResponseList(List<Editora> editoras);

    Editora requestToEntity(EditoraRequestDTO editoraRequestDTO);

    // método gerado pelo copilot. testar depois
    default List<String> livrosToNomesLivros(List<Livro> livros) {
        return livros.stream()
                .map(Livro::getNome)
                .toList();
    }
}
