package com.ada.grupo3.mapper;

import com.ada.grupo3.dto.request.CategoriaRequestDTO;
import com.ada.grupo3.dto.request.UsuarioRequestDTO;
import com.ada.grupo3.dto.response.CategoriaDetailsResponseDTO;
import com.ada.grupo3.dto.response.UsuarioDetailsResponseDTO;
import com.ada.grupo3.entity.Categoria;
import com.ada.grupo3.entity.Livro;
import com.ada.grupo3.entity.Usuario;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "spring")
public interface CategoriaMapper {
    @Mapping(target = "nomesLivros", source = "livros")
    CategoriaDetailsResponseDTO entityToResponse(Categoria categoria);

    List<CategoriaDetailsResponseDTO> entityToResponseList(List<Categoria> categorias);

    Categoria requestToEntity(CategoriaRequestDTO categoriaRequestDTO);

    // método gerado pelo copilot. testar depois
    default List<String> livrosToNomesLivros(List<Livro> livros) {
        return livros.stream()
                .map(Livro::getNome)
                .toList();
    }
}
