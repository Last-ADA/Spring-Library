package com.ada.grupo3.mapper;

import com.ada.grupo3.dto.request.LivroRequestDTO;
import com.ada.grupo3.dto.response.LivroDetailsResponseDTO;
import com.ada.grupo3.entity.Categoria;
import com.ada.grupo3.entity.Livro;
import com.ada.grupo3.repository.CategoriaRepository;
import com.ada.grupo3.service.CategoriaService;
import jakarta.persistence.EntityNotFoundException;
import org.mapstruct.Context;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
import java.util.Optional;

@Mapper(componentModel = "spring")
public interface LivroMapper {


    @Mapping(target = "autorNome", source = "autor.nome")
    @Mapping(target = "editoraNome", source = "editora.nome")
    @Mapping(target = "nomesCategorias", source = "categorias")
    LivroDetailsResponseDTO entityToResponse(Livro livro);

    List<LivroDetailsResponseDTO> entityToResponseList(List<Livro> livros);

    @Mapping(target = "autor.id", source = "autorId")
    @Mapping(target = "editora.cnpj", source = "editoraCnpj")
    @Mapping(target = "categorias", source = "categoriasId")
    @Mapping(target = "disponivel", source = "disponivel", defaultValue = "true")
    Livro requestToEntity(LivroRequestDTO livroRequestDTO, @Context CategoriaRepository categoriaRepository);

    // método gerado pelo copilot. testar depois
    default List<String> categoriasToNomesCategorias(List<Categoria> categorias) {
        return categorias.stream()
                .map(Categoria::getNome)
                .toList();
    }

    default List<Categoria> categoriasIdToCategorias(List<Long> categoriasId, @Context CategoriaRepository categoriaRepository) {
        if (categoriasId != null) {
            return categoriasId.stream()
                    .map(id -> categoriaRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("Categoria não encontrada")))
                    .toList();

        } else {
            return null;
        }
    }
}
