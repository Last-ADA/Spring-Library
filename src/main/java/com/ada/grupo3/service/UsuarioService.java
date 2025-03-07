package com.ada.grupo3.service;

import com.ada.grupo3.dto.request.UsuarioRequestDTO;
import com.ada.grupo3.dto.response.UsuarioDetailsResponseDTO;
import com.ada.grupo3.entity.Usuario;
import com.ada.grupo3.mapper.UsuarioMapper;
import com.ada.grupo3.repository.UsuarioRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UsuarioService {

    private final UsuarioRepository usuarioRepository;
    private final UsuarioMapper usuarioMapper;


    public UsuarioService(UsuarioRepository usuarioRepository, UsuarioMapper usuarioMapper) {
        this.usuarioRepository = usuarioRepository;
        this.usuarioMapper = usuarioMapper;
    }

    public List<UsuarioDetailsResponseDTO> findAll() {
        var usuarios = usuarioRepository.findAll();
        return usuarioMapper.entityToResponseList(usuarios);
    }

    public UsuarioDetailsResponseDTO findById(Long usuarioId) {
        var usuario = usuarioRepository.findById(usuarioId)
                .orElseThrow(() -> new RuntimeException("Usuário não encontrado"));
        return usuarioMapper.entityToResponse(usuario);
    }

    public Usuario create(UsuarioRequestDTO usuarioRequestDTO) {
        var usuario = usuarioMapper.requestToEntity(usuarioRequestDTO);
        usuarioRepository.save(usuario);
        return usuario;
    }

    public void updateAll(Long id, UsuarioRequestDTO usuarioRequestDTO) {
        usuarioRepository.findById(id)
                        .orElseThrow(() -> new RuntimeException("Usuário não encontrado"));
        var usuarioUpdate = usuarioMapper.requestToEntity(usuarioRequestDTO);
        usuarioUpdate.setId(id);
        usuarioRepository.save(usuarioUpdate);
    }

    public void deleteUsuario(Long id) {usuarioRepository.deleteById(id);}
}
