package com.ada.grupo3.mapper;

import com.ada.grupo3.dto.request.LivroRequestDTO;
import com.ada.grupo3.dto.response.LivroDetailsResponseDTO;
import com.ada.grupo3.entity.Livro;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "spring")
public interface LivroMapper {

    @Mapping(target = "autorNome", source = "autor.nome")
    @Mapping(target = "editoraNome", source = "editora.nome")
    LivroDetailsResponseDTO entityToResponse(Livro livro);

    List<LivroDetailsResponseDTO> entityToResponseList(List<Livro> livros);

    @Mapping(target = "autor.id", source = "autorId")
    @Mapping(target = "editora.cnpj", source = "editoraCnpj")
    @Mapping(target = "disponivel", constant = "true")
    Livro requestToEntity(LivroRequestDTO livroRequestDTO);
}
