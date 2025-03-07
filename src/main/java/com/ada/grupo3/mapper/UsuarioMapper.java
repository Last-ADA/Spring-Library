package com.ada.grupo3.mapper;

import com.ada.grupo3.dto.request.UsuarioRequestDTO;
import com.ada.grupo3.dto.response.UsuarioDetailsResponseDTO;
import com.ada.grupo3.entity.Usuario;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface UsuarioMapper {
    UsuarioDetailsResponseDTO entityToResponse(Usuario usuario);

    List<UsuarioDetailsResponseDTO> entityToResponseList(List<Usuario> usuarios);

    Usuario requestToEntity(UsuarioRequestDTO usuarioRequestDTO);
}
