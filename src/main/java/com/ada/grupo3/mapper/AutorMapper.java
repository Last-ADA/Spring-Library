package com.ada.grupo3.mapper;

import com.ada.grupo3.dto.request.AutorRequestDTO;
import com.ada.grupo3.dto.response.AutorDetailsResponseDTO;
import com.ada.grupo3.entity.Autor;
import com.ada.grupo3.entity.Livro;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.springframework.stereotype.Component;

import java.util.List;


@Mapper(componentModel = "spring")
public interface AutorMapper {

    @Mapping(target = "nomesLivros", source = "livros")
    @Mapping(target = "dataNascimento", dateFormat = "dd/MM/yyyy")
    AutorDetailsResponseDTO entityToResponse(Autor autor);

    List<AutorDetailsResponseDTO> entityToResponseList(List<Autor> autores);

    @Mapping(target = "dataNascimento", dateFormat = "dd/MM/yyyy")
    Autor requestToEntity(AutorRequestDTO autorRequestDTO);

    // método gerado pelo copilot. testar depois
    default List<String> livrosToNomesLivros(List<Livro> livros) {
        return livros.stream()
                     .map(Livro::getNome)
                     .toList();
    }
}
