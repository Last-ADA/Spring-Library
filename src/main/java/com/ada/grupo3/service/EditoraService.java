package com.ada.grupo3.service;

import com.ada.grupo3.dto.request.EditoraRequestDTO;
import com.ada.grupo3.dto.response.EditoraDetailsResponseDTO;
import com.ada.grupo3.entity.Editora;
import com.ada.grupo3.mapper.EditoraMapper;
import com.ada.grupo3.repository.EditoraRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EditoraService {

    private final EditoraRepository editoraRepository;
    private final EditoraMapper editoraMapper;

    public EditoraService(EditoraRepository editoraRepository, EditoraMapper editoraMapper) {
        this.editoraRepository = editoraRepository;
        this.editoraMapper = editoraMapper;
    }

    public List<EditoraDetailsResponseDTO> findAll() {
        var editoras = editoraRepository.findAll();
        return editoraMapper.entityToResponseList(editoras);
    }

    public EditoraDetailsResponseDTO findByCnpj(String cnpj) {
        var editora = editoraRepository.findById(cnpj)
                .orElseThrow(() -> new RuntimeException("Editora não encontrada"));
        return editoraMapper.entityToResponse(editora);
    }

    public Editora create(EditoraRequestDTO editoraRequestDTO) {
        var editora = editoraMapper.requestToEntity(editoraRequestDTO);
        editoraRepository.save(editora);
        return editora;
    }

    public void updateAll(String cnpj, EditoraRequestDTO editoraRequestDTO) {
        editoraRepository.findById(cnpj)
                .orElseThrow(() -> new RuntimeException("Editora não encontrada"));
        var editoraUpdate = editoraMapper.requestToEntity(editoraRequestDTO);
        editoraUpdate.setCnpj(cnpj);
        editoraRepository.save(editoraUpdate);
    }

    public void deleteByCnpj(String cnpj) {
        editoraRepository.deleteById(cnpj);
    }

    public List<EditoraDetailsResponseDTO> findByNome(String nome) {
        var editoras = editoraRepository.findByNomeIgnoreCaseContaining(nome);
        return editoraMapper.entityToResponseList(editoras);
    }
}
